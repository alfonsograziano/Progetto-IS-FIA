package it.unisa.di.smartblog.dora.genetic;

import it.unisa.di.smartblog.spec.Spec;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class GEHelper {

    private Mutation mutation;
    private Crossover crossover;
    private Selection selection;
    private FitnessHelper fitnessHelper;

    private  ArrayList<Spec> specs;

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Crossover getCrossover() {
        return crossover;
    }

    public void setCrossover(Crossover crossover) {
        this.crossover = crossover;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public FitnessHelper getFitnessHelper() {
        return fitnessHelper;
    }

    public void setFitnessHelper(FitnessHelper fitnessHelper) {
        this.fitnessHelper = fitnessHelper;
    }

    public GEHelper(ArrayList<Spec> specs){
        this.specs = specs;
    }

    private Population generateNewPopulation(Population oldPopulation){
        oldPopulation.shuffle();
        Population newPopulation = new Population();

        for(SpecGene s: oldPopulation.getPopulation()) newPopulation.addGene(s);

        for(int j = 0; j < oldPopulation.getPopulation().size(); j++) {
            SpecGene son = null;
            while(son == null){
                SpecGene s1 = oldPopulation.getRandomSpecGene();
                SpecGene s2 = oldPopulation.getRandomSpecGene();
                son = crossover.cross(s1,s2);
            }

            SpecGene mutated = null;
            while(mutated == null) mutated = mutation.mutate(son, generateRandomSpec());

            newPopulation.addGene(mutated);
        }
        return newPopulation;
    }

    private Spec generateRandomSpec() {
        Random rand = new Random();
        return specs.get(rand.nextInt(specs.size()));
    }


    public SpecGene run(Population population){
        SpecGene bestChoice = new SpecGene(new ArrayList<Spec>());
        double localMin = 1000000;
        double localMax = -1;

        boolean terminated = false;
        Date startTime = new Date();
        long endTime = 200; //Tempo in cui il sistema deve girare
        int count = 0;
        Population newPopulation = population;


        while(!terminated) {
            //Computo il valore di fit per tutta la popolazione attuale
            for(SpecGene gene: newPopulation.getPopulation())  gene.setFit(fitnessHelper.computeFit(gene));

            //Seleziono i primi x individui
            Population populationToReproduce = selection.select(newPopulation,50);

            //Creo la nuova popolazione
            newPopulation =  generateNewPopulation(populationToReproduce);

            for(SpecGene gene: newPopulation.getPopulation()) {
                //Calolo il fit di ogni individuo nella nuova popolazione
                gene.setFit(fitnessHelper.computeFit(gene));

                //Salvo il minimo locale per benchmarking
                //Salvo la scelta migliore in maniera ridondante
                if(localMin > gene.getFit()) {
                    localMin = gene.getFit();
                    bestChoice = gene;
                }

                //Salvo il massimo locale per benchmarking
                if(localMax < gene.getFit())  localMax = gene.getFit();
            }

            count++;
            Date now = new Date();
            if( now.getTime() > startTime.getTime()+ endTime ) {
                terminated = true;
            }
        }

        System.out.println("Max/min fit "
                +String.format("%.2f", localMin)+"/"+String.format("%.2f", localMax)
                +" - iterations => "+ count);

        return bestChoice;
    }

    public static Population generatePopulation(ArrayList<Spec> genes, int geneLength ){
        //Creo la prima popolazione formata da x inidividui di lunghezza geneLength
        Population population = new Population();

        for(int i = 0; i < genes.size(); i+=geneLength) {
            try {
                ArrayList<Spec> data = new ArrayList<>();
                for(int j = 0; j < geneLength; j++) {
                    data.add(genes.get(i+j));
                }
                population.addGene(new SpecGene(data));
            }catch(Exception e) {
                //System.out.println("Sono arrivato alla fine dell'array, non posso creare il nuovo individuo");
            }
        }
        return population;
    }

}
