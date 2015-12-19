package sim;

public class HeadlessSimulation extends BaseSimulation {
	
	private int foodRefreshRate;
	private double foodRefreshPercentage;
	
	public HeadlessSimulation(){

	}

	/**
	 * 
	 * @param mapLen - length of map
	 * @param mapWidth - width of map
	 * @param foodPercent - percentage of world taken by food
	 * @param startPopSize - number of starting critters
	 */
	public void setupWorld(int mapWidth, int mapHeight, double foodPercent, int startPopSize, int foodRefreshRate){
		this.foodRefreshRate = foodRefreshRate;
		this.foodRefreshPercentage = foodPercent;
		super.setupWorld(mapWidth, mapHeight, foodPercent, startPopSize);
	}

	/**
	 * There is a lot of work related to population management which should really go to the population
	 * 
	 * @param turn
	 * @return 
	 */
	@Override
	public boolean simulateTurn(int turn) {
		if( foodRefreshRate > 0 && turn % foodRefreshRate == 0 ){
			System.out.println("Replenishing food!");
			worldMap.replenishFood(foodRefreshPercentage);
		}
		return super.simulateTurn(turn);
	}

}
