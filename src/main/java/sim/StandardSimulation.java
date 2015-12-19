package sim;

import world.GUI;

/**
 * 
 * @author JKyte
 *
 */
public class StandardSimulation extends BaseSimulation {

	private  GUI gui;
	
	private int DELAY_IN_MILLIS = 1000;
	
	public StandardSimulation(){

	}
	
	public StandardSimulation(int delayInMillis){
		this.DELAY_IN_MILLIS = delayInMillis;
	}
	
	/**
	 * Higher level wrapper class for running the simulation. Default time between GUI updates is 1 second
	 * @param turns - duration of simulation
	 */
	@Override
	public void runSimulation(int turns){
		createAndDisplayFixedGUI();

		long start = System.currentTimeMillis();
		for( int ii = 0; ii < turns; ii++ ){
			if( !simulateTurn(ii) ){
				break;
			}
		}
		long total = System.currentTimeMillis()-start;
		
		System.out.println("Simulation ran in " + total + "ms.");
		System.out.println(pop.endOfSimStats());
		System.out.println(pop.printSurvivalRatesByGeonome());
		
		//	Final update
		updateGUI("Turn: " + turns);
	}

	/**
	 * There is a lot of work related to population management which should really go to the population
	 * 
	 * @param turn
	 * @return 
	 */
	@Override
	public boolean simulateTurn(int turn) {
		try {
			Thread.sleep(DELAY_IN_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		updateGUI("Turn: " + turn);
		return super.simulateTurn(turn);
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
