package population;

import critters.geneticcritter.GeneticCritterFactory;

/**
 * Created by JKyte on 5/25/2016.
 */
public class GeneticPopulationFactory {

    private static final int DEFAULT_POP_SIZE = 20;

    public static GeneticPopulation createSimpleGeneticPopulation(){
        return createDefaultGeneticPopulation(DEFAULT_POP_SIZE);
    }

    public static GeneticPopulation createDefaultGeneticPopulation(int size){
        GeneticPopulation population = new GeneticPopulation();
        for( int ii = 0; ii < size; ii++ ){
            population.addCritter(GeneticCritterFactory.createSimpleCritter());
        }
        return population;
    }

    public static GeneticPopulation createSimpleGeneticPopulation(int size){
        GeneticPopulation population = new GeneticPopulation();
        for( int ii = 0; ii < size; ii++ ){
            population.addCritter(GeneticCritterFactory.createSimpleCritter());
        }
        return population;
    }

    public static GeneticPopulation createComplexGeneticCritter(int size){
        GeneticPopulation population = new GeneticPopulation();
        for( int ii = 0; ii < size; ii++ ){
            population.addCritter(GeneticCritterFactory.createComplexCritter());
        }
        return population;
    }
}
