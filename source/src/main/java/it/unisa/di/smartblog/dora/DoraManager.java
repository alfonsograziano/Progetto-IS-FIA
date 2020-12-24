package it.unisa.di.smartblog.dora;

import it.unisa.di.smartblog.dora.genetic.*;
import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DoraManager {

    public ArrayList<Spec> findSpectByParams(double battery, double performance, double camera, double display, double maxBudget) throws Exception{

        SpecsManager sp = new SpecsManager();

        ArrayList<Spec>  specs = (ArrayList<Spec>) sp.searchByPrice(maxBudget);

        int minBattery = sp.searchMinBattery();
        int maxBattery = sp.searchMaxBattery();

        FitnessHelperSpec f1 = new FitnessHelperSpec(performance, camera, display, new Date(), battery,minBattery, maxBattery);
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


    private SpecGene singleRun(FitnessHelper fp, ArrayList<Spec> specs)  throws Exception{
        GEHelper ge = new GEHelper(specs);
        ge.setCrossover(new SinglePointCrossover());
        ge.setMutation(new RandomMutation());
        ge.setSelection(new TruncationSelection());
        ge.setFitnessHelper(fp);

        Collections.shuffle(specs);

        Population population = GEHelper.generatePopulation(specs,6);

        return ge.run(population);
    }

}
