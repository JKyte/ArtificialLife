package sim.old;

import java.util.HashMap;
import java.util.Set;

import brains.SimpleBrain;
import critters.SimpleCritter;
import population.Population;
import utils.ActionTarget;
import utils.CritterAction;
import utils.VisionCritCoord;
import world.WorldMap;

public class BaseSimulation implements OldSimulation {
	
	public WorldMap worldMap;

	public Population pop;
	
	public BaseSimulation(){
		
	}

	public void setupWorld(int mapWidth, int mapHeight, double foodPercent) {
		initializeWorldMap(mapWidth, mapHeight);
		placeFoodByPerc(foodPercent);
	}
	
	public void initializeWorldMap(int mapWidth, int mapHeight){
		worldMap = new WorldMap(mapWidth, mapHeight);
	}
	
	/**
	 * @param targetPerc -- a double between 0.0 and 1.0
	 */
	public void placeFoodByPerc(double targetPerc){
		double totalSpaces = worldMap.mapArea();
		double preTarget = totalSpaces * targetPerc;
		int foodSpaces = (int) Math.round(preTarget);
		for( int ii = 0; ii < foodSpaces; ii++ ){
			worldMap.placeObjectRandomly(1, false);
		}
	}
	
	/**
	 * An alternative to placeFoodByPerc()
	 * @param numFood
	 */
	public void placeFoodByNum(int numFood){
		for( int ii = 0; ii < numFood; ii++ ){
			worldMap.placeObjectRandomly(1, false);
		}
	}
	
	public void initializePopulation(int startPopSize){
		pop = new Population(startPopSize, worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		pop.placePopulation();
	}

	public void runSimulation(int turns) {
		long start = System.currentTimeMillis();
		for( int ii = 0; ii < turns; ii++ ){
			if( !simulateTurn(ii) ){
				break;
			}
		}
		long total = System.currentTimeMillis()-start;
		
		System.out.println("OldSimulation ran in " + total + "ms.");
		System.out.println(pop.endOfSimStats());
		System.out.println(pop.printSurvivalRatesByGenome());
	}

	public boolean simulateTurn(int turn) {
		if( turn % 250 == 0 ) {
            System.out.println("Turn: " + turn);
        }

		//	Variable for early exit check
		boolean critterStillAlive = false;

		//	Initialize some variables prior to the loop
		SimpleCritter critter = null;
		ActionTarget actionTarget = null;
		VisionCritCoord visionCritCoord = null;

		SimpleBrain brain = new SimpleBrain();
		HashMap<Integer, SimpleCritter> population = pop.getPopMap();
		Set<Integer> popKeys = population.keySet();

		for( Integer key : popKeys ){
			critter = population.get(key);
			if( critter.isAlive() ){
				critterStillAlive = true;
				
				critter.setCurrentLifeLen(critter.getCurrentLifeLen()+1);
				brain.setCritter(critter);

				visionCritCoord = worldMap.getVisionMap(critter.getVision(), critter.getWorldLocation(), false);
				actionTarget = brain.processVisionMap(visionCritCoord);

				/**
				 * Handle ActionTarget
				 */
				//System.out.println(critter.getCritterID() + ": " + actionTarget.toString());
				if( actionTarget.getAction().equals(CritterAction.WAIT) ){
					//	Do nothing
				}else if( actionTarget.getAction().equals(CritterAction.MOVE) ){

					worldMap.moveObject(critter.getWorldLocation(), actionTarget.getTarget());
					critter.setWorldLocation(actionTarget.getTarget());

				}else if( actionTarget.getAction().equals(CritterAction.EAT) ){
					
					worldMap.removeObject(actionTarget.getTarget());	//	Remove the food to be eaten
					critter.setCurEnergy(critter.getCurEnergy() + 10 );	//	Increment critter's engery
					critter.setFoodEaten(critter.getFoodEaten()+1);
					
				}else{
					System.err.println("Unhandled ActionTarget:" + actionTarget.toString());
				}

				//	Update Energy. If energy reaches zero, kill the critter.
				critter.setCurEnergy( critter.getCurEnergy()-1 );
				if( critter.getCurEnergy() == 0 ){
					critter.setAlive(false);	//	Kill 'em ded. If this is last critter we run for another turn
												//	but it beats loop through the population again.
					worldMap.placeObjectAtCoord(3, critter.getWorldLocation());
				}
				
				population.put(key, critter);	//	Put updated Critter back into the Population
			}
			
			critter = null;					//	Set Critter to null
			brain.setCritter(null);			//	Set the Brain's Critter pointer to null
			visionCritCoord = null;			//	Set the visionCritCoord to null
			actionTarget = null;			//	Set the ActionTarget to null
		}
		
		return critterStillAlive;
	}

}
