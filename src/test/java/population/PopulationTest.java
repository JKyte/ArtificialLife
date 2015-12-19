package population;

import java.util.HashMap;

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
	public void testGeneratePopulation(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(1, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		
		HashMap<Integer, SimpleCritter> population = pop.getPopMap();
		Assert.assertEquals(1, population.size());
		
		int key = population.keySet().iterator().next();
		SimpleCritter crit = population.get(key);
		
		Assert.assertEquals(0, crit.getGenerationID());
		Assert.assertEquals(0, crit.getCritterID());
		Assert.assertEquals(1, crit.getSpeed());
		Assert.assertEquals(1, crit.getVision());
		Assert.assertEquals(5, crit.getCurHealth());
		Assert.assertEquals(10, crit.getCurEnergy());
		Assert.assertEquals(1, crit.getFood().length);
		Assert.assertEquals(1, crit.getFood()[0]);
	}
	
	@Test
	public void testPlacePopulation(){
		StandardSimulation sim = new StandardSimulation();
		sim.initializeWorldMap(1, 1);
		
		Population pop = new Population(1, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		pop.placePopulation();

		Assert.assertEquals(2, sim.worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testEndOfSimStats(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(3, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		
		SimpleCritter ded = new SimpleCritter();
		ded.setAlive(false);
		pop.getPopMap().put(Integer.MAX_VALUE, ded);
		
		StringBuilder expected = new StringBuilder();
		expected.append("\nBest  Fitness: 5 -- 5:10:[1]:1:1\n");
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
		pop.generatePopulation();
		
		SimpleCritter ded = new SimpleCritter();
		ded.setAlive(false);
		pop.getPopMap().put(Integer.MAX_VALUE, ded);
		
		//	Trigger branch in the printSurvivalRatesByGeonome
		pop.getPopMap().put(Integer.MAX_VALUE-1, ded);
		
		StringBuilder expected = new StringBuilder();
		expected.append("2 unique geonomes.\n");
		expected.append("Survival rate for: 5:10:[1]:1:1: 100%\n");
		expected.append("Survival rate for: 0:0:[-1]:0:0: 0%\n");
		
		Assert.assertEquals(expected.toString(), pop.printSurvivalRatesByGeonome() );
	}
	
	@Test
	public void testPrintSurvivalRatesByGeonome_deadPop(){
		StandardSimulation sim = new StandardSimulation();
		Population pop = new Population(0, sim.worldMap);
		pop.setDefaultPopulationStats();
		pop.generatePopulation();
		
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
}
