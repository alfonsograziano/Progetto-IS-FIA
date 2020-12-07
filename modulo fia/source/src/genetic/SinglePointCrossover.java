package genetic;

import scraper.GeneSpec;
import scraper.Spec;

import java.util.ArrayList;

public class SinglePointCrossover implements Crossover {


    @Override
    public ArrayList<SpecGene> cross(SpecGene g1, SpecGene g2) {

        //Genero il primo figlio
        ArrayList<Object> son1 = new ArrayList<Object>();
        ArrayList<Object> son2 = new ArrayList<Object>();

        for(int i = 0; i < g1.getGene().size()/2; i++)
            son1.add(g1.getGene().get(i));

        for(int i = g1.getGene().size()/2; i < g1.getGene().size(); i++)
            son2.add(g1.getGene().get(i));


        for(int i = 0; i < g2.getGene().size()/2; i++)
            son1.add(g2.getGene().get(i));

        for(int i = g2.getGene().size()/2; i < g2.getGene().size(); i++)
            son2.add(g2.getGene().get(i));


        ArrayList<SpecGene> sons = new ArrayList<>();
        sons.add(new SpecGene(son1));
        sons.add(new SpecGene(son2));

        return sons;

    }
}
