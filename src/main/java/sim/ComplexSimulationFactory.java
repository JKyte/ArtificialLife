package sim;

import population.GeneticPopulationFactory;
import world.WorldMapFactory;

/**
 * Created by JKyte on 6/2/2016.
 */
public class ComplexSimulationFactory {

    private final static int DEFAULT_TOTAL_TURNS = 25;

    public static ComplexSimulation createComplexSimulationForTest(){
        ComplexSimulation simulation = new ComplexSimulation();

        simulation.setPopulation(GeneticPopulationFactory.createDefaultGeneticPopulation(10));
        simulation.setWorld(WorldMapFactory.createStandardMapForTest());
        simulation.setTotalTurns(DEFAULT_TOTAL_TURNS);

        return simulation;
    }
}
