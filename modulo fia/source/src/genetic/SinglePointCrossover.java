package genetic;

import scraper.GeneSpec;
import scraper.Spec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SinglePointCrossover implements Crossover {


    @Override
    public SpecGene cross(SpecGene g1, SpecGene g2) {

        //Genero il primo figlio
        ArrayList<Object> son1 = new ArrayList<Object>();

        for(int i = 0; i < g1.getGene().size()/2; i++)
            son1.add(g1.getGene().get(i));

        for(int i = 0; i < g2.getGene().size()/2; i++)
            son1.add(g2.getGene().get(i));


        Set verificationSet = new HashSet<>(son1);
        if(son1.size() > verificationSet.size()){
            return null;
        }

        return new SpecGene(son1);
    }
}
