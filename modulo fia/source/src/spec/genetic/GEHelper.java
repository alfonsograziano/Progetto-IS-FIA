package spec.genetic;

import spec.Spec;

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

    public GEHelper(Mutation mutation, Crossover crossover, Selection selection, FitnessHelper fitnessHelper){
        this.mutation = mutation;
        this.crossover = crossover;
        this.selection = selection;
        this.fitnessHelper = fitnessHelper;
    }

    public GEHelper(ArrayList<Spec> specs){
        this.specs = specs;
    }

    private Population generateNewPopulation(Population oldPopulation){
        oldPopulation.shuffle();
        Population newPopulation = new Population();

        for(SpecGene s: oldPopulation.getPopulation()){
            newPopulation.addGene(s);
        }

        final int popSize = oldPopulation.getPopulation().size();

        for(int j = 0; j < popSize; j++) {
            SpecGene son = null;
            while(son == null){
                SpecGene s1 = oldPopulation.getRandomSpecGene();
                SpecGene s2 = oldPopulation.getRandomSpecGene();
                son = crossover.cross(s1,s2);
            }

            SpecGene mutated = null;
            while(mutated == null){
                mutated = mutation.mutate(son, generateRandomSpec());
            }
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
        int converged = -1;

        boolean terminated = false;
        Date startTime = new Date();
        long endTime = 200; //Tempo in cui il sistema deve girare
        int count = 0;
        Population newPopulation = population;



        while(!terminated) {
            for(SpecGene gene: newPopulation.getPopulation()) {
                gene.setFit(fitnessHelper.computeFit(gene));
            }
            Population populationToReproduce = selection.select(newPopulation,50);
            newPopulation =  generateNewPopulation(populationToReproduce);
            //100 => 50 migliori selezionati + 50 figli dei tizi
            for(SpecGene gene: newPopulation.getPopulation()) {
                //Calolo il fit di ogni individuo
                gene.setFit(fitnessHelper.computeFit(gene));
                if(localMin > gene.getFit()) {
                    localMin = gene.getFit();
                    bestChoice = gene;
                    converged = count;
                }
                if(localMax < gene.getFit()) {
                    localMax = gene.getFit();
                }
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

        /*

        System.out.println("Max/min fit "
                +String.format("%.2f", localMin)+"/"+String.format("%.2f", localMax)
                +" - iterations => "+ count);
        System.out.println("Arrivato a convergenza dopo "+converged+" iterazioni");

        //System.out.println(bestChoice);
        ArrayList<Spec> result = bestChoice.getGene();
        for(Spec s: result){
            System.out.println(s + " | " + s.getFitValue());
        }
        */

        return bestChoice;
    }

    public static Population generatePopulation(ArrayList<Spec> genes, int geneLength ){
        //Creo la prima popolazione
        Population population = new Population();

        for(int i = 0; i < genes.size(); i+=geneLength) {
            try {
                ArrayList<Spec> data = new ArrayList<Spec>();
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

    public static Population generatePopulation(ArrayList<Spec> genes ){
        return generatePopulation(genes, genes.size());
    }


}
