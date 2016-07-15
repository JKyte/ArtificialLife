package sim;

import critters.geneticcritter.GeneticCritter;
import population.GeneticPopulation;
import population.GeneticPopulationFactory;
import world.WorldMap;
import world.WorldMapFactory;

import java.util.UUID;

/**
 * Created by JKyte on 6/2/2016.
 */
public class ComplexSimulationFactory {

    private final static int DEFAULT_TOTAL_GENERATIONS = 0;
    private final static int DEFAULT_TOTAL_TURNS = 25;

    public static ComplexSimulation createComplexSimulationForTest(){
        ComplexSimulation simulation = new ComplexSimulation();

        simulation.setPopulation(GeneticPopulationFactory.createDefaultGeneticPopulation(1));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);
        simulation.setTotalGenerations(DEFAULT_TOTAL_GENERATIONS);

        return simulation;
    }

    public static ComplexSimulation createComplexSimulation_generational_ForTest(){
        ComplexSimulation simulation = new ComplexSimulation();

        simulation.setPopulation(GeneticPopulationFactory.createComplexGeneticCritter(1));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);
        simulation.setTotalGenerations(10);

        return simulation;
    }

    /**
     * Note: The concept of what a "Default" is will change as the project continues to evolve
     * @return
     */
    public static GuiComplexSimulation createGuiComplexSimulationDefaultPopForTest(){
        GuiComplexSimulation simulation = new GuiComplexSimulation(50);

        simulation.setPopulation(GeneticPopulationFactory.createDefaultGeneticPopulation(1));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);
        simulation.setTotalGenerations(DEFAULT_TOTAL_GENERATIONS);

        return simulation;
    }

    /**
     * Run a simulation with a simple population
     * @return
     */
    public static GuiComplexSimulation createGuiComplexSimulationSimplePopulationForTest(){
        GuiComplexSimulation simulation = new GuiComplexSimulation(50);

        simulation.setPopulation(GeneticPopulationFactory.createSimpleGeneticPopulation(20));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);
        simulation.setTotalGenerations(DEFAULT_TOTAL_GENERATIONS);

        return simulation;
    }

    public static GuiComplexSimulation createGuiComplexSimulationComplexPopulationForTest(){
        GuiComplexSimulation simulation = new GuiComplexSimulation(50);

        simulation.setPopulation(GeneticPopulationFactory.createComplexGeneticCritter(20));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(200);
        simulation.setTotalGenerations(DEFAULT_TOTAL_GENERATIONS);

        return simulation;
    }

    private static void placePopulation(WorldMap worldMap, GeneticPopulation population){

        for( UUID uuid : population.keySet() ){

            GeneticCritter critter = population.getCritter(uuid);
            critter.setLocation( worldMap.placeObjectRandomly(2) );
            population.replaceCritter(critter);

        }

    }
}
