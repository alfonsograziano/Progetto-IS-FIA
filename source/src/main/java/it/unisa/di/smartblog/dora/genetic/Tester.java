package it.unisa.di.smartblog.dora.genetic;




import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class Tester {
    static ArrayList<Spec> specs;

    final static double PERFORMANCE = 9;
    final static double CAMERA = 7;
    final static double DISPLAY = 7;
    final static double BATTERY = 6;

    final static int MIN_BATTERY = 800;
    final static int MAX_BATTERY = 7600;

    public static void asd(String[] args) throws Exception {
        FitnessHelperSpec f1 = new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY, new Date(), BATTERY, MIN_BATTERY, MAX_BATTERY);
        //FitnessHelperSpec2 f2 = new FitnessHelperSpec2(PERFORMANCE, CAMERA, DISPLAY, new Date(), BATTERY,MIN_BATTERY, MAX_BATTERY);
        SpecsManager sp = new SpecsManager();

        ArrayList<SpecGene> trofeo = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            trofeo.add(tester(f1, (ArrayList<Spec>) sp.searchAll()));
        }
        SpecGene winner = trofeo.get(0);
        for (SpecGene s : trofeo) {
            if (f1.computeVariance(s) <= f1.computeVariance(winner)) {
                winner = s;
            }
        }

        System.out.println("Fit vincitore => " + f1.computeFit(winner));
        System.out.println(winner);
    }

    public static SpecGene tester(FitnessHelper fp, ArrayList<Spec> specs) throws Exception {

        //TODO: segna varianza
        GEHelper ge = new GEHelper(specs);
        ge.setCrossover(new SinglePointCrossover());
        ge.setMutation(new RandomMutation());
        ge.setSelection(new TruncationSelection());
        ge.setFitnessHelper(fp);


        //Faccio lo shuffle della collezione
        Collections.shuffle(specs);

        Population population = GEHelper.generatePopulation(specs, 6);
        //System.out.println(population);

        return ge.run(population);
    }

}