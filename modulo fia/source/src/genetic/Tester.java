package genetic;

import scraper.ScraperHelper;
import scraper.Spec;

import java.util.ArrayList;
import java.util.Collections;

public class Tester {
    static ArrayList<Spec> specs;

    public static void main(String[] args) throws Exception {

        ScraperHelper sp = new ScraperHelper();
        specs = sp.getSpecs();

        final double PERFORMANCE = 8.5;
        final double CAMERA = 7.5;
        final double DISPLAY = 7.0;

        GEHelper ge = new GEHelper(specs);
        ge.setCrossover(new SinglePointCrossover());
        ge.setMutation(new RandomMutation());
        ge.setSelection(new RankSelection());
        ge.setFitnessHelper(new FitnessHelperSpec(PERFORMANCE, CAMERA, DISPLAY));



        //Faccio lo shuffle della collezione
        Collections.shuffle(specs);

        Population population = GEHelper.generatePopulation(specs,6);
        //System.out.println(population);

        ge.run(population);

    }
}