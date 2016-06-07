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

    private final static int DEFAULT_TOTAL_TURNS = 25;

    public static ComplexSimulation createComplexSimulationForTest(){
        ComplexSimulation simulation = new ComplexSimulation();

        simulation.setPopulation(GeneticPopulationFactory.createDefaultGeneticPopulation(1));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);

        return simulation;
    }

    public static GuiComplexSimulation createGuiComplexSimulationForTest(){
        GuiComplexSimulation simulation = new GuiComplexSimulation(50);

        simulation.setPopulation(GeneticPopulationFactory.createDefaultGeneticPopulation(1));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());

        placePopulation(simulation.getWorld(), simulation.getPopulation());

        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);

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
