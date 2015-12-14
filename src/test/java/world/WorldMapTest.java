package world;

import org.junit.Assert;
import org.junit.Test;

import utils.Coord;

public class WorldMapTest {
	
	/**
	 * This test places two different objects
	 */
	@Test
	public void testPlaceObjectRandomly(){
		//	Instantiate a 1x1 map for random placement test
		WorldMap worldMap = new WorldMap(1);
		//	worldMap.printLogicalMap();

		worldMap.placeObjectRandomly(7, false);
		//	worldMap.printLogicalMap();
		Assert.assertEquals(7, worldMap.getGrid(0, 0));

		//	Clear map
		worldMap.removeObject(new Coord(0,0));
		//	worldMap.printLogicalMap();

		worldMap.placeObjectRandomly(4, false);
		Assert.assertEquals(4, worldMap.getGrid(0, 0));
	}
	
	@Test
	public void testRectangleInit(){
		WorldMap worldMap = new WorldMap(2,5);
		//	worldMap.printLogicalMap();
		Assert.assertEquals(10, worldMap.mapArea());
	}

	@Test
	public void testPlaceFoodByPerc(){
		WorldMap worldMap = new WorldMap(2,2);

		worldMap.placeFoodByPerc(0.0);
		Assert.assertEquals(0, worldMap.getGrid(0, 0));
		Assert.assertEquals(0, worldMap.getGrid(1, 1));
		Assert.assertEquals(0, worldMap.getGrid(1, 0));
		Assert.assertEquals(0, worldMap.getGrid(1, 1));
		
		worldMap.placeFoodByPerc(1.0);
		Assert.assertEquals(1, worldMap.getGrid(0, 0));
		Assert.assertEquals(1, worldMap.getGrid(0, 1));
		Assert.assertEquals(1, worldMap.getGrid(1, 0));
		Assert.assertEquals(1, worldMap.getGrid(1, 1));
		Assert.assertEquals(4, worldMap.getCountForObjectType(1));
	}
	
	@Test
	public void testReplenishFood(){
		double target_percent = 0.5;
		//	Fill half the map with food.
		WorldMap worldMap = new WorldMap(2,2);
		worldMap.placeFoodByPerc(target_percent);
		Assert.assertEquals(2, worldMap.getCountForObjectType(1));
		
		//	Zero out whole map
		worldMap.setGrid(0, 0, 0);
		worldMap.setGrid(0, 1, 0);
		worldMap.setGrid(1, 0, 0);
		worldMap.setGrid(1, 1, 0);
		Assert.assertEquals(0, worldMap.getCountForObjectType(1));
	
		worldMap.replenishFood(target_percent);
		Assert.assertEquals(2, worldMap.getCountForObjectType(1));		
	}
	
}
