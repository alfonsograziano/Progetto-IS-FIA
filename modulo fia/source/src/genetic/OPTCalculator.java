package genetic;

import scraper.ScraperHelper;
import scraper.Spec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class OPTCalculator {
    static ArrayList<Spec> specs;

    final static double PERFORMANCE = 8.5;
    final static double CAMERA = 7.5;
    final static double DISPLAY = 7.0;
    final static double BATTERY = 8.0;

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

    public static void main(String[] args) throws Exception {
        ScraperHelper sp = new ScraperHelper();
        specs = sp.getSpecs();

        fp = new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY, new Date(),BATTERY, MIN_BATTERY, MAX_BATTERY);
        Collections.shuffle(specs);

        sortSpecs(specs);

        final int POPULATION_SIZE = 6;
        double oracleFit = 0;
        for(int i = 0; i < POPULATION_SIZE; i++){
            oracleFit += specs.get(i).getFitValue();
            System.out.println(specs.get(i));
        }

        System.out.println("Total fit => "+ oracleFit);
        //TODO: Testa la varianza => tendere a una media bassa e varianza tendente a 0

    }
}
