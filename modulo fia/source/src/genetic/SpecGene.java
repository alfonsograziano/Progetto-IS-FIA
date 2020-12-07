package genetic;

import scraper.Spec;

import java.util.ArrayList;

public class SpecGene {

    private ArrayList gene;
    private double fit;

    public SpecGene(ArrayList g){
        this.gene = g;
        this.fit = 0;
    }

    public ArrayList getGene() {
        return gene;
    }

    public void setGene( ArrayList gene) {
        this.gene = gene;
    }

    public double getFit() {
        return this.fit;
    }

    public void setFit(double fit) {
        this.fit = fit;
    }

    public String toString(){
        String string = "";
        for(Object spec:gene){
            string += spec.toString()+"\n";
        }
        return string;
    }
}
