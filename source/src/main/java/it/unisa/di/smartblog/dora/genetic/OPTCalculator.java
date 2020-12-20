package it.unisa.di.smartblog.dora.genetic;

import it.unisa.di.smartblog.spec.Spec;
import it.unisa.di.smartblog.spec.SpecsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class OPTCalculator {
    static ArrayList<Spec> specs;

    final static double PERFORMANCE = 9;
    final static double CAMERA = 7;
    final static double DISPLAY = 8.0;
    final static double BATTERY = 6.5;

    final static int MIN_BATTERY = 800;
    final static int MAX_BATTERY = 7600;

    static FitnessHelperSpec fp;

    public static void sortSpecs(ArrayList<Spec> spec){
        for (int i = 0; i < specs.size()-1; i++) {
            for(int j = 0; j < specs.size()-i-1; j++)  {

                specs.get(j).setFitValue(fp.computeSpecFit(specs.get(j)));
                specs.get(j+1).setFitValue(fp.computeSpecFit(specs.get(j+1)));

                if( specs.get(j).getFitValue() > specs.get(j + 1).getFitValue() ) {
                    Spec tempVar = specs.get(j + 1);
                    specs.set(j + 1,specs.get(j));
                    specs.set(j,tempVar);
                }
            }
        }
    }

    public static void asd(String[] args) throws Exception {

        SpecsManager sp = new SpecsManager();

        specs = (ArrayList<Spec>) sp.searchAll();

        fp = new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY, new Date(),BATTERY, MIN_BATTERY, MAX_BATTERY);
        Collections.shuffle(specs);

        sortSpecs(specs);

        final int GENE_SIZE = 6;
        double oracleFit = 0;
        double powedFit = 0;

        for(int i = 0; i < GENE_SIZE; i++){
            double localFit = specs.get(i).getFitValue();
            oracleFit += localFit;
            powedFit += Math.pow(localFit,2);
            System.out.println(specs.get(i));
        }

        double mean = oracleFit/GENE_SIZE;
        double variance = powedFit-Math.pow(mean,2);

        System.out.println("Total fit => "+ (variance + mean) );
    }
}
