package genetic;

import java.util.ArrayList;
import java.util.Collections;

public class Population {
    private ArrayList<SpecGene> population;

    public Population(){
        this.population = new ArrayList<>();
    }

    public Population(ArrayList<SpecGene> population){
        this.population = population;
    }

    public void addGene(SpecGene g1){
        this.population.add(g1);
    }

    public ArrayList<SpecGene> getPopulation(){
        return this.population;
    }

    public String toString(){
        String string = "";
        for(SpecGene spec:population){
            string += "NEW GENE\n"+spec.toString()+"\n";
        }
        return string;
    }

    public void shuffle(){
        Collections.shuffle(this.population);
    }
}
