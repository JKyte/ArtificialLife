package sim;

import brains.ComplexBrain;
import critters.genes.GeneType;
import critters.geneticcritter.GeneticCritter;
import population.GeneticPopulation;
import utils.ActionTarget;
import utils.CritterAction;
import world.WorldMap;

import java.util.UUID;

/**
 * Created by JKyte on 6/2/2016.
 */
public class ComplexSimulation implements Simulation {

    private GeneticPopulation population;

    protected WorldMap worldMap;

    private ComplexBrain brain;

    protected int totalTurns;
    protected int currentTurn = 0;

    public void setPopulation(GeneticPopulation population) {
        this.population = population;
    }

    public void setWorld(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public WorldMap getWorld(){ return worldMap;    }

    public void setTotalTurns(int totalTurns){
        this.totalTurns = totalTurns;
    }

    public void runSimulation() {

        brain = new ComplexBrain();

        while( currentTurn < totalTurns ){

            simulateTurn();
            currentTurn++;

        }

        System.out.println("Simulation concluded.");
    }

    public void simulateTurn() {

        for( UUID uuid : population.keySet() ){
            if( population.getCritter(uuid).isAlive()){
                simulateCritter(uuid);
            }
        }

    }

    public void simulateCritter(UUID uuid) {
        GeneticCritter critter = population.getCritter(uuid);

        brain.setCritter(critter);

        ActionTarget actionTarget = getActionTargetForCritter(critter);

        //  Handle the action target
        handleActionTarget(actionTarget, critter);
        //  Do operations, maybe break them out into an interface

        brain.clearCritter();
        population.replaceCritter(critter);
    }

    public ActionTarget getActionTargetForCritter( GeneticCritter critter ){
        return brain.processVisionMap(worldMap.getVisionMap(critter.getGeneValue(GeneType.VISION), critter.getLocation()));
    }

    public void handleActionTarget(ActionTarget actionTarget, GeneticCritter critter) {

        //  Implicit do-nothing on a CritterAction.WAIT

        if( actionTarget.getAction().equals(CritterAction.MOVE) ){

            worldMap.moveObject(critter.getLocation(), actionTarget.getTarget());
            critter.setLocation(actionTarget.getTarget());

        }else if( actionTarget.getAction().equals(CritterAction.EAT) ){

            //  Remove the food
            worldMap.removeObject(actionTarget.getTarget());
            critter.updateGeneValue(GeneType.VITALITY, 10);

        }else{
            System.err.println("Unhandled ActionTarget: " + actionTarget.toString());
        }
    }

    public GeneticPopulation getPopulation() {
        return population;
    }
}
