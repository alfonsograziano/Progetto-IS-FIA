package it.unisa.di.smartblog.dora.genetic;

import java.util.ArrayList;
import static java.lang.Math.min;

public class TruncationSelection implements Selection {

    @Override
    public Population select(Population population, int maxPopulationSize) {
        //Faccio la copia per evitare casini quando faccio il sorting
        ArrayList<SpecGene> copiedPopulation = new ArrayList(population.getPopulation());
        sortByFit(copiedPopulation);

        //Tronco l'array in base alla size massima della popolazione
        copiedPopulation = truncateArray(copiedPopulation, maxPopulationSize);

        return new Population(copiedPopulation);
    }

    @Override
    public Population select(Population population) {
        return select(population, population.getPopulation().size());
    }

    public void sortByFit(ArrayList<SpecGene> p){
        for (int i = 0; i < p.size()-1; i++) {
            for(int j = 0; j < p.size()-i-1; j++)  {
                if( p.get(j).getFit() > p.get(j + 1).getFit() ) {

                    SpecGene tempVar = p.get(j + 1);
                    p.set(j + 1,p.get(j));
                    p.set(j,tempVar);

                }
            }
        }
    }

    public ArrayList<SpecGene> truncateArray(ArrayList<SpecGene> array, int maxSize){
       return new ArrayList(array.subList(0, min(array.size(), maxSize)));
    }
}
