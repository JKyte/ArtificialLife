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

}
