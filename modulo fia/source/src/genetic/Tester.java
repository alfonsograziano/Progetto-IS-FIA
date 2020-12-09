package genetic;

import scraper.ScraperHelper;
import scraper.Spec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Tester {
    static ArrayList<Spec> specs;

    final static double PERFORMANCE = 8.5;
    final static double CAMERA = 7.5;
    final static double DISPLAY = 7.0;

    final static int MIN_BATTERY = 800;
    final static int MAX_BATTERY = 7600;

    public static void main(String[] args) throws Exception {
        tester();
    }

    public static void tester()  throws Exception{
        ScraperHelper sp = new ScraperHelper();
        specs = sp.getSpecs();


        GEHelper ge = new GEHelper(specs);
        ge.setCrossover(new SinglePointCrossover());
        ge.setMutation(new RandomMutation());
        ge.setSelection(new RankSelection());
        ge.setFitnessHelper(new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY, new Date(), MIN_BATTERY, MAX_BATTERY));


        //Faccio lo shuffle della collezione
        Collections.shuffle(specs);

        Population population = GEHelper.generatePopulation(specs,6);
        //System.out.println(population);

        ge.run(population);
    }



    public static void singleTest()  throws Exception{
        ScraperHelper sp = new ScraperHelper();
        specs = sp.getSpecs();

        final double PERFORMANCE = 8.5;
        final double CAMERA = 7.5;
        final double DISPLAY = 7.0;

        FitnessHelperSpec fp = new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY, new Date(), MIN_BATTERY, MAX_BATTERY);
        System.out.println(fp.computeSpecFit(specs.get(0)));

    }
}