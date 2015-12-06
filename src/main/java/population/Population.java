package population;

import java.util.HashMap;
import java.util.Set;

import critters.SimpleCritter;
import world.Simulation;

public class Population {
	
	private int popSize;
	private HashMap<Integer,SimpleCritter> popMap;

	//	TODO -- critter minimum stats
	private int speed_MIN;
	private int vision_MIN;
	
	private int maxHealth_MIN;
	private int curHealth_MIN;
	private int energy_MIN;
	private int memory_MIN;
	
	//	TODO -- critter max stats
	private int speed_MAX;
	private int vision_MAX;
	
	private int maxHealth_MAX;
	private int curHealth_MAX;
	private int energy_MAX;
	private int memory_MAX;
	
	//	TODO -- critter variation
	private int speed_VAR;
	private int vision_VAR;
	
	private int maxHealth_VAR;
	private int curHealth_VAR;
	private int energy_VAR;
	private int memory_VAR;
	
	private Simulation world;
	
	public Population( int popSize, Simulation world ){
		this.popSize = popSize;
		popMap = new HashMap<Integer, SimpleCritter>();
		this.world = world;
	}

	public void setDefaultPopulationStats() {
		speed_MIN = 1;
		vision_MIN = 1;	
		maxHealth_MIN = 5;
		curHealth_MIN = 5;
		energy_MIN = 10;
		memory_MIN = 5;
		
		speed_MAX =1;
		vision_MAX = 1;
		maxHealth_MAX = 5;
		curHealth_MAX = 5;
		energy_MAX = 10;
		memory_MAX = 5;
		
		speed_VAR = 0;
		vision_VAR = 0;
		maxHealth_VAR = 0;
		curHealth_VAR = 0;
		energy_VAR = 0;
		memory_MIN = 0;
	}

	/**
	 * A place to tweak new stat changes
	 */
	public void setPopulationStats(){
		speed_MIN = 1;
		vision_MIN = 1;	
		maxHealth_MIN = 5;
		curHealth_MIN = 5;
		energy_MIN = 10;
		memory_MIN = 5;
		
		speed_MAX =1;
		vision_MAX = 1;
		maxHealth_MAX = 5;
		curHealth_MAX = 5;
		energy_MAX = 10;
		memory_MAX = 5;
		
		speed_VAR = 0;
		vision_VAR = 0;
		maxHealth_VAR = 0;
		curHealth_VAR = 0;
		energy_VAR = 0;
		memory_VAR = 0;
	}
	
	/**
	 * This is really a "Generate Default Population" method
	 */
	public void generatePopulation() {
		for(int ii = 0; ii < popSize; ii++ ){
			SimpleCritter crit = new SimpleCritter();
			crit.setGenerationID(0);
			crit.setCritterID(ii);

			//	set up stats
			crit.setSpeed(speed_MIN);
			crit.setVision(vision_MIN);
			crit.setCurHealth(curHealth_MIN);
			crit.setMaxHealth(curHealth_MAX);
			crit.setCurEnergy(energy_MIN);
			crit.setFood(new int[]{1});		//	Everyone is an herbivore by default

			popMap.put(crit.getCritterID(), crit);	//	store this little critter
		}
		
	}
	
	public void placePopulation(){
		int placed = 0;
		Set<Integer> keys = popMap.keySet();
		for( Integer key : keys ){
			SimpleCritter curCrit = popMap.get(key);
			curCrit.setWorldLocation( world.worldMap.placeObjectRandomly(2, false));
			placed++;
		}
		System.out.println( "num placed: " + placed);
	}
	
	/**
	 * Future work shall include breeding critters together
	 */
	
	public int getPopSize() {
		return popSize;
	}

	/**
	 * @returns a HashMap containing the critters
	 */
	public HashMap<Integer, SimpleCritter> getPopMap() {
		return popMap;
	}
}
