package sim;

import population.GeneticPopulation;
import world.WorldMap;

/**
 * Created by JKyte on 6/2/2016.
 */
public class ComplexSimulation implements Simulation {

    private GeneticPopulation population;

    private WorldMap worldMap;

    private int totalTurns;
    private int currentTurn = 0;

    public void setPopulation(GeneticPopulation population) {
        this.population = population;
    }

    public void setWorld(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void setTotalTurns(int totalTurns){
        this.totalTurns = totalTurns;
    }

    public void runSimulation() {

        while( currentTurn < totalTurns ){

            simulateTurn();
            currentTurn++;

        }
    }

    public void simulateTurn() {

    }

    public void simulateCritter() {

    }
}
