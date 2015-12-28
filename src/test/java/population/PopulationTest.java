package population;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import sim.StandardSimulation;
import critters.SimpleCritter;

public class PopulationTest {

	@Test
	public void testInit(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(1, sim.worldMap);
		
		Assert.assertEquals(1, pop.getPopSize());
	}
	
	@Test
	public void testGenerateDefaultPopulation(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(1, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		
		HashMap<Integer, SimpleCritter> population = pop.getPopMap();
		Assert.assertEquals(1, population.size());
		
		int key = population.keySet().iterator().next();
		SimpleCritter crit = population.get(key);
		
		Assert.assertEquals(0, crit.getGenerationID());
		Assert.assertEquals(0, crit.getCritterID());
		Assert.assertEquals(1, crit.getSpeed());
		Assert.assertEquals(1, crit.getVision());
		Assert.assertEquals(10, crit.getCurHealth());
		Assert.assertEquals(10, crit.getCurEnergy());
		Assert.assertEquals(1, crit.getFood().length);
		Assert.assertEquals(1, crit.getFood()[0]);
	}
	
	@Test
	public void testGenerateDiversePopulation(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(15, sim.worldMap);
		pop.setDiversePopulationStats();
		pop.generateDiversePopulation();
		
		HashMap<Integer, SimpleCritter> population = pop.getPopMap();	
		HashSet<String> geonomes = new HashSet<String>();
		for( Integer key : population.keySet() ){
			geonomes.add( population.get(key).toGeonome() );
		}
		
		Assert.assertNotEquals(1, geonomes.size());
	}
	
	@Test
	public void testPlacePopulation(){
		StandardSimulation sim = new StandardSimulation();
		sim.initializeWorldMap(1, 1);
		
		Population pop = new Population(1, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		pop.placePopulation();

		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testEndOfSimStats(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(3, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		
		SimpleCritter ded = new SimpleCritter();
		ded.setAlive(false);
		pop.getPopMap().put(Integer.MAX_VALUE, ded);
		
		StringBuilder expected = new StringBuilder();
		expected.append("\nBest  Fitness: 5 -- 10:10:[1]:1:1\n");
		expected.append("Worst Fitness: 0 -- 0:0:[-1]:0:0\n");
		expected.append("Alive: 3\nDead: 1\nTotal: 4\n");
		expected.append("Overall survival rate: 75%");

		
		Assert.assertEquals(expected.toString(), pop.endOfSimStats() );
	}
	
	@Test
	public void testPrintSurvivalRatesByGeonome_alivePop(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(3, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		
		SimpleCritter ded = new SimpleCritter();
		ded.setAlive(false);
		pop.getPopMap().put(Integer.MAX_VALUE, ded);
		
		//	Trigger branch in the printSurvivalRatesByGeonome
		pop.getPopMap().put(Integer.MAX_VALUE-1, ded);
		
		StringBuilder expected = new StringBuilder();
		expected.append("2 unique geonomes.\n");
		expected.append("Survival rate for: 10:10:[1]:1:1: 100%\n");
		expected.append("Survival rate for: 0:0:[-1]:0:0: 0%\n");
		
		Assert.assertEquals(expected.toString(), pop.printSurvivalRatesByGeonome() );
	}
	
	@Test
	public void testPrintSurvivalRatesByGeonome_deadPop(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(0, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generateDefaultPopulation();
		
		SimpleCritter g1_c1 = new SimpleCritter();
		g1_c1.setAlive(true);
		pop.getPopMap().put(1, g1_c1);
		
		SimpleCritter g1_c2 = new SimpleCritter();
		g1_c2.setAlive(false);
		pop.getPopMap().put(2, g1_c2);
		
		SimpleCritter g2_c1 = new SimpleCritter();
		g2_c1.setAlive(true);
		g2_c1.setMaxHealth(55);
		pop.getPopMap().put(3, g2_c1);
		
		SimpleCritter g2_c2 = new SimpleCritter();
		g2_c2.setAlive(false);
		g2_c2.setMaxHealth(55);
		pop.getPopMap().put(4, g2_c2);
		
		StringBuilder expected = new StringBuilder();
		expected.append("2 unique geonomes.\n");
		expected.append("Survival rate for: 55:0:[-1]:0:0: 50%\n");
		expected.append("Survival rate for: 0:0:[-1]:0:0: 50%\n");
		
		Assert.assertEquals(4, pop.getPopMap().size());
		Assert.assertEquals(expected.toString(), pop.printSurvivalRatesByGeonome() );
	}
	
	@Test
	public void testGetRandomStat(){
		int low = 1;
		int high = 1000000;
		
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(0, sim.worldMap);
		
		int result = pop.getRandomStat(low, high);
		System.out.println( "Random result: " + result);
		Assert.assertNotEquals(1, result );
	}
}
