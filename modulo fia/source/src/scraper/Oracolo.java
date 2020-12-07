package scraper;

import java.util.ArrayList;
import java.util.Collections;

public class Oracolo {
    static ArrayList<Spec> specs;

    public static void sortSpecs(ArrayList<Spec> specs,double peformance, double camera, double display){
        for (int i = 0; i < specs.size()-1; i++) {
            for(int j = 0; j < specs.size()-i-1; j++)  {
                if( specs.get(j).getFit(peformance,camera,display) >
                        specs.get(j + 1).getFit(peformance,camera,display) ) {
                    Spec tempVar = specs.get(j + 1);
                    specs.set(j + 1,specs.get(j));
                    specs.set(j,tempVar);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Test oracolo...");

        ScraperHelper sp = new ScraperHelper();
        //I valori che otteniamo in input vanno normalizzati su valori tra 1 e 5
        final double PERFORMANCE = 8.5;
        final double CAMERA = 7.5;
        final double DISPLAY = 8.0;
        //Battey è espresso in mah, va normalizzato
        //final double BATTERY = 8.5;

        specs = sp.getSpecs();
        Collections.shuffle(specs);

        sortSpecs(specs,PERFORMANCE,CAMERA,DISPLAY);
        /*
        for(Spec spec:specs){
            System.out.println(spec);
        }
        */
        final int POPULATION_SIZE = 6;
        double oracleFit = 0;
        for(int i = 0; i < POPULATION_SIZE; i++){
            oracleFit += specs.get(i).getFit(PERFORMANCE,CAMERA,DISPLAY);
            System.out.println(specs.get(i));
        }

        System.out.println("Total fit => "+ oracleFit);


    }

}
