package sim;

import population.GeneticPopulation;
import world.WorldMap;

/**
 * Created by JKyte on 6/2/2016.
 */
public interface Simulation {

    void setPopulation(GeneticPopulation population);

    void setWorld(WorldMap worldMap);

    void runSimulation();

    void simulateTurn();

    void simulateCritter();
}
