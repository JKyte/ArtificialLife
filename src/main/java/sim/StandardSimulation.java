package sim;

import java.util.HashMap;
import java.util.Set;

import population.Population;
import utils.ActionTarget;
import utils.CritterAction;
import utils.VisionCritCoord;
import world.GUI;
import world.WorldMap;
import brains.SimpleBrain;
import critters.SimpleCritter;

/**
 * 
 * @author JKyte
 *
 */
public class StandardSimulation implements Simulation {

	public WorldMap worldMap;

	public Population pop;

	private  GUI gui;
	
	private int foodRefreshRate;
	private double foodRefreshPercentage;
	
	public StandardSimulation(){

	}

	/**
	 * 
	 * @param mapLen - length of map
	 * @param mapWidth - width of map
	 * @param foodPercent - percentage of world taken by food
	 * @param startPopSize - number of starting critters
	 */
	public void setupWorld(int mapWidth, int mapHeight, double foodPercent, int startPopSize, int foodRefreshRate){
		initializeWorldMap(mapWidth, mapHeight);
		worldMap.placeFoodByPerc(foodPercent);
		initializePopulation(startPopSize);
		this.foodRefreshRate = foodRefreshRate;
		this.foodRefreshPercentage = foodPercent;
	}

	public void initializeWorldMap(int mapWidth, int mapHeight){
		worldMap = new WorldMap(mapWidth, mapHeight);
	}

	public void initializePopulation(int startPopSize){
		pop = new Population(startPopSize, worldMap);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		pop.placePopulation();
	}

	public void placeFoodByNum(int numFood){
		for( int ii = 0; ii < numFood; ii++ ){
			worldMap.placeObjectRandomly(1, false);
		}
	}

	/**
	 * Higher level wrapper class for running the simulation. Default time between GUI updates is 1 second
	 * @param turns - duration of simulation
	 */
	public void runSimulation(int turns){
		long start = System.currentTimeMillis();
		createAndDisplayFixedGUI();
		for( int ii = 0; ii < turns; ii++ ){
			updateGUI("Turn: " + ii);
			simulateTurn(ii);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//	Final update
		updateGUI("Turn: " + turns);
		long totalDelay = turns*1000;
		long total = System.currentTimeMillis()-start;
		System.out.println("Simulation ran in " + total + "ms.");
		System.out.println("Time in delay: " + totalDelay + "ms.");
		System.out.println("Time processing: " + (total-totalDelay) + "ms.");
		System.out.println(pop.endOfSimStats());
		
	}

	/**
	 * Higher level wrapper class for running the simulation
	 * @param turns - duration of simulation
	 * @param updateDelay - delay in milliseconds between turns
	 */
	public void runSimulation(int turns, long updateDelay){
		long start = System.currentTimeMillis();
		
		createAndDisplayFixedGUI();
		for( int ii = 0; ii < turns; ii++ ){
			updateGUI("Turn: " + ii);
			simulateTurn(ii);

			try {
				Thread.sleep(updateDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//	Final update
		updateGUI("Turn: " + turns);
		
		long total = System.currentTimeMillis()-start;
		long totalDelay = turns*updateDelay;
		System.out.println("Simulation ran in " + total + "ms.");
		System.out.println("Time in delay: " + totalDelay + "ms.");
		System.out.println("Time processing: " + (total-totalDelay) + "ms.");
		System.out.println(pop.endOfSimStats());
		
	}

	/**
	 * There is a lot of work related to population management which should really go to the population
	 * 
	 * @param turn
	 */
	public void simulateTurn(int turn) {
		System.out.println("Turn: " + turn);

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
					critter.setAlive(false);	//	Kill 'em ded
					worldMap.placeObjectAtCoord(3, critter.getWorldLocation());
				}
				
				population.put(key, critter);	//	Put updated Critter back into the Population
			}
			
			critter = null;					//	Set Critter to null
			brain.setCritter(null);			//	Set the Brain's Critter pointer to null
			visionCritCoord = null;			//	Set the visionCritCoord to null
			actionTarget = null;			//	Set the ActionTarget to null
		}

		if( foodRefreshRate > 0 && turn % foodRefreshRate == 0 ){
			worldMap.replenishFood(foodRefreshPercentage);
		}
	}
	
	public void createAndDisplayDynamicGUI(){
		gui = new GUI(worldMap.mapWidth, worldMap.mapHeight);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void createAndDisplayFixedGUI(){
		gui = new GUI();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void updateGUI(){
		gui.clearAndSetText(worldMap.getMapForGUI());
	}

	public void updateGUI( String stats ){
		System.out.println("Update GUI.");
		gui.clearAndSetText(worldMap.getMapForGUI(stats));
	}

}
