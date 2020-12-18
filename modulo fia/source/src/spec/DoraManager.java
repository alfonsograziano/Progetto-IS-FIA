package spec;

import spec.genetic.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DoraManager {

    public ArrayList<Spec> findSpectByParams(double battery, double performance, double camera, double display, double maxBudget) throws Exception{

        //Creiamo uno specDao
        //Nel dao richiamo getSpecsWithMaxBudget
        ArrayList<Spec> specs = new ArrayList<>();

        final int MIN_BATTERY = 800;// getMinBattery()
        final int MAX_BATTERY = 7600;// getMaxBattery()

        FitnessHelperSpec f1 = new FitnessHelperSpec(performance, camera, display, new Date(), battery,MIN_BATTERY, MAX_BATTERY);
        ArrayList<SpecGene> podium = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            podium.add(singleRun(f1,specs));
        }
        SpecGene winner = podium.get(0);
        for(SpecGene s: podium){
            if(f1.computeVariance(s) <= f1.computeVariance(winner)){
                winner = s;
            }
        }

        return winner.getGene();
    }


    private SpecGene singleRun(FitnessHelper fp,ArrayList<Spec> specs)  throws Exception{
        GEHelper ge = new GEHelper(specs);
        ge.setCrossover(new SinglePointCrossover());
        ge.setMutation(new RandomMutation());
        ge.setSelection(new TruncationSelection());
        ge.setFitnessHelper(fp);

        //Faccio lo shuffle della collezione
        Collections.shuffle(specs);

        Population population = GEHelper.generatePopulation(specs,6);
        //System.out.println(population);

        return ge.run(population);
    }

}
