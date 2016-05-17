package population;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import world.WorldMap;
import critters.SimpleCritter;

public class Population {
	
	private int popSize;
	private HashMap<Integer,SimpleCritter> popMap;

	private int speed;
	private int vision;
	
	private int health_MAX;
	private int health_MIN;
	
	private int energy_MIN;
	private int energy_MAX;
	
	private WorldMap worldMap;
	
	private DecimalFormat format = new DecimalFormat("#.##");
	private Random rand = new Random();
	
	public Population( int popSize, WorldMap worldMap ){
		this.popSize = popSize;
		popMap = new HashMap<Integer, SimpleCritter>();
		this.worldMap = worldMap;
	}

	public void setDefaultPopulationStats() {
		speed = 1;
		vision = 1;	
		
		health_MIN = 10;
		health_MAX = 10;
		
		energy_MIN = 10;
		energy_MAX = 10;
	}

	/**
	 * A place to tweak new stat changes
	 */
	public void setDiversePopulationStats(){
		speed = 1;
		vision = 1;	
		
		health_MIN = 10;
		health_MAX = 15;
		
		energy_MIN = 20;
		energy_MAX = 30;
	}

	/**
	 * Generate default population
	 */
	public void generateDefaultPopulation() {
		
		SimpleCritter crit = null;
		for(int ii = 0; ii < popSize; ii++ ){
			crit = generateDefaultCritter(ii);
			popMap.put(crit.getCritterID(), crit);	//	store this little critter
		}
		
	}
	
	public SimpleCritter generateDefaultCritter(int currentCritterID){
		SimpleCritter crit = new SimpleCritter();
		crit.setGenerationID(0);
		crit.setCritterID(currentCritterID);
		
		crit.setMaxHealth(health_MAX);
		crit.setMaxEnergy(energy_MAX);

		//	set up stats
		crit.setSpeed(speed);
		crit.setVision(vision);
		crit.setCurHealth(health_MAX);
		crit.setCurEnergy(energy_MAX);
		crit.setFood(new int[]{1});		//	Everyone is an herbivore by default
		
		return crit;
	}
	
	/**
	 * Generate a genetically diverse population
	 */
	public void generateDiversePopulation(){
		SimpleCritter crit = null;
		for(int ii = 0; ii < popSize; ii++ ){
			crit = generateDiverseCritter(ii);
			popMap.put(crit.getCritterID(), crit);	//	store this little critter
		}
	}
	
	public SimpleCritter generateDiverseCritter(int currentCritterID) {
		SimpleCritter crit = new SimpleCritter();
		crit.setGenerationID(0);
		crit.setCritterID(currentCritterID);
		
		int tmpHealth = getRandomStat(health_MIN, health_MAX);
		int tmpEnergy = getRandomStat(energy_MIN, energy_MAX);
		
		crit.setMaxHealth(tmpHealth);
		crit.setMaxEnergy(tmpEnergy);

		//	set up stats
		crit.setSpeed(speed);
		crit.setVision(vision);
		crit.setCurHealth(tmpHealth);
		crit.setCurEnergy(tmpEnergy);
		crit.setFood(new int[]{1});		//	Everyone is an herbivore by default
		
		return crit;
	}
	
	public int getRandomStat(int low, int high){
		return (rand.nextInt(high-low) + low);
	}

	public void placePopulation(){
		int placed = 0;
		Set<Integer> keys = popMap.keySet();
		for( Integer key : keys ){
			SimpleCritter curCrit = popMap.get(key);
			curCrit.setWorldLocation( worldMap.placeObjectRandomly(2, false));
			placed++;
		}
		System.out.println( "num placed: " + placed);
	}
	
	public String endOfSimStats(){
		int alivePop = 0,
				deadPop = 0,
				totalPop = popMap.size(),
				bestFitness = 0,
				worstFitness = Integer.MAX_VALUE;
		
		SimpleCritter bestCritter = null;
		SimpleCritter worstCritter = null;
		
		SimpleCritter tmpCrit = null;
		int tmpCritFitness = 0;
		Set<Integer> keys = popMap.keySet();
		for( Integer key : keys ){
			
			tmpCrit = popMap.get(key);
			tmpCritFitness = tmpCrit.calculateFitness();
			
			if( tmpCrit.isAlive() ){
				alivePop++;
				tmpCritFitness += 5;	//	Make this configurable later
			}else{
				deadPop++;
			}

			//	Check to see if this fitness is a best/worst
			if( tmpCritFitness > bestFitness ){
				bestFitness = tmpCritFitness;
				bestCritter = tmpCrit;
			}
			if( tmpCritFitness < worstFitness ){
				worstFitness = tmpCritFitness;
				worstCritter = tmpCrit;
			}
		}
		
		double alive = (double) alivePop;
		double dead = (double) deadPop;
		double totalSurvivalRate = ( alive / (alive+dead) ) * 100;
		
		return "\nBest  Fitness: " + bestFitness + " -- " + bestCritter.toGenome()
				+ "\nWorst Fitness: " + worstFitness + " -- " + worstCritter.toGenome()
				+ "\nAlive: " + alivePop + "\nDead: " + deadPop + "\nTotal: " + totalPop
				+ "\nOverall survival rate: " + format.format(totalSurvivalRate) + "%";
	}
	
	public String printSurvivalRatesByGenome(){
		
		StringBuilder sb = new StringBuilder();
		HashSet<String> uniqueGenomes = new HashSet<String>();
		HashMap<String,Integer> aliveMap = new HashMap<String,Integer>();
		HashMap<String,Integer> deadMap = new HashMap<String,Integer>();
		
		SimpleCritter curCrit = null;
		String curGeonome = null;
		Set<Integer> keys = popMap.keySet();
		for( Integer key : keys ){
			
			curCrit = popMap.get(key);
			curGeonome = curCrit.toGenome();
			
			uniqueGenomes.add(curGeonome);
			
			if( curCrit.isAlive() ){
				if( aliveMap.containsKey( curGeonome ) ){
					aliveMap.put(curGeonome, aliveMap.get(curGeonome)+1 );
				}else{
					aliveMap.put(curGeonome, 1 );
				}
			} else {
				if( deadMap.containsKey( curGeonome ) ){
					deadMap.put(curGeonome, deadMap.get(curGeonome)+1 );
				}else{
					deadMap.put(curGeonome, 1 );
				}
			}
		}
		
		
		double alive = -1, dead = -1, rate = -1;
		sb.append( uniqueGenomes.size() + " unique geonomes.\n" );
		for( String key : uniqueGenomes ){
			
			if( aliveMap.get(key) == null ){
				sb.append( "Survival rate for: " + key + ": 0%\n" );
				continue;
			}else{
				alive = (double) aliveMap.get(key);	
			}
			
			if( deadMap.get(key) == null ){
				dead = 0.0;
			}else{
				dead = (double) deadMap.get(key);
					
			}
			
			
			rate = ( alive / (alive+dead) ) * 100;
			
			sb.append( "Survival rate for: " + key + ": " + format.format(rate) + "%\n" );
		}
		
		return sb.toString();
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
