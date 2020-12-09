package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.sql.ResultSet;

public class Tester {
	static ArrayList<Spec> specs;

    
    public static ArrayList<GeneSpec> generatePopulation(ArrayList<Spec> specs ){
    	//Creo la prima popolazione
    	ArrayList<GeneSpec> population = new ArrayList<GeneSpec>();
    	final int GENE_LENGTH = 6;
    	
    	for(int i = 0; i < specs.size(); i+=GENE_LENGTH) {
			try {
				ArrayList<Spec> data = new ArrayList<Spec>();
				for(int j = 0; j < GENE_LENGTH; j++) {
	    			data.add(specs.get(i+j));
				}
				population.add(new GeneSpec(data));
				
			}catch(Exception e) {
				System.out.println("Sono arrivato alla fine dell'array, non posso creare il nuovo individuo");
			}    		
    	}
    	return population;
    }

    
    public static void sortPopulation(ArrayList<GeneSpec> gene){
    	for (int i = 0; i < gene.size()-1; i++) {
    	   for(int j = 0; j < gene.size()-i-1; j++)  {
	            if( gene.get(j).getFitting() > gene.get(j + 1).getFitting() ) {
                   GeneSpec tempVar = gene.get(j + 1);
                   gene.set(j + 1,gene.get(j));
                   gene.set(j,tempVar);
	            }
    	   }
    	}
    }
    
    public static ArrayList<GeneSpec> haveBungaBunga(ArrayList<GeneSpec> population){
    	//Lo shuffle della popolazione mi serve per far incrociare genitori random tra di loro
        Collections.shuffle(population); 
        ArrayList<GeneSpec> sons = new ArrayList<GeneSpec>();
        
    	final int POPULATION_SIZE = 12;

        for(int j = 0; j < POPULATION_SIZE/2; j+=2) {
        	ArrayList<GeneSpec> newSons = population.get(j).crossover(population.get(j+1));
        	for(GeneSpec son: newSons){
				son.mutate(generateRandomSpec());
				sons.add(son);

			}
		}
        
		return sons;
    }
    
    public static Spec generateRandomSpec() {
    	Random rand = new Random();
    	return specs.get(rand.nextInt(specs.size()));
    }
    
    public static void main(String[] args) throws Exception {

		//I valori che otteniamo in input vanno normalizzati su valori tra 1 e 5
		final double PERFORMANCE = 8.5;
		final double CAMERA = 8.5;
		final double DISPLAY = 7.0;
		//Battey è espresso in mah, va normalizzato
		//final double BATTERY = 8.5;
    	ScraperHelper sp = new ScraperHelper();
    	specs = sp.getSpecs();
    	
    	//Faccio lo shuffle della collezione
        Collections.shuffle(specs); 
    	
    	ArrayList<GeneSpec> population = generatePopulation(specs);
		System.out.println("New population size:" + population.size());
    	
    	for(GeneSpec gene: population) {
    		gene.calculateFitting(PERFORMANCE, CAMERA, DISPLAY);
    	}
	
    	
    	//In questo caso faccio un numero massimo di ricombinazioni
    	//Magari sarebbe meglio usare un roulette wheel
    	sortPopulation(population);
    	//Prendo i primi n 
    	final int POPULATION_SIZE = 100;
    	ArrayList<GeneSpec> populationToReproduce = new ArrayList<GeneSpec>();
    	for(int i = 0; i < POPULATION_SIZE; i++) {
    		populationToReproduce.add(population.get(i));
    	}
    	
    	GeneSpec bestChoice = new GeneSpec(new ArrayList<Spec>());
    	double localMin = 1000000;
		double localMax = -1;
		int converged = -1;

		boolean terminated = false;
		Date startTime = new Date();
		long endTime = 1*1000; //Tempo in cui il sistema deve girare
		int count = 0;
    	while(!terminated) {
    		
        	ArrayList<GeneSpec> newPopulation =  haveBungaBunga(populationToReproduce);    		

    	 	for(GeneSpec gene: newPopulation) {
        		//Calolo il fit di ogni individuo
        		gene.calculateFitting(PERFORMANCE, CAMERA, DISPLAY);
        		if(localMin >= gene.getFitting()) {
        			localMin = gene.getFitting();
        			bestChoice = gene;
					converged = count;
				}
        		if(localMax <= gene.getFitting()) {
        			localMax = gene.getFitting();
        		}
        	}
    	 	
    	 	count++;
    	 	populationToReproduce = newPopulation;	
    	 	Date now = new Date();
    	 	if( now.getTime() > startTime.getTime()+ endTime ) {
    	 		terminated = true;
    	 	}
    	}
    	
    	System.out.println("Max/min fit "
	    	+String.format("%.2f", localMin)+"/"+String.format("%.2f", localMax)
	    	+" - iterations => "+ count);
		System.out.println("Arrivato a convergenza dopo "+converged+" iterazioni");

		System.out.println(bestChoice);

    }

}
