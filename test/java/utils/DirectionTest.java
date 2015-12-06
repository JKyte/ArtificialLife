package utils;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {
	
	@Test
	public void testDirections(){
		Direction north = Direction.NORTH;
		Direction south = Direction.SOUTH;
		Direction east = Direction.EAST;
		Direction west = Direction.WEST;
		
		Assert.assertEquals(Direction.NORTH, north);
		Assert.assertEquals(Direction.SOUTH, south);
		Assert.assertEquals(Direction.EAST, east);
		Assert.assertEquals(Direction.WEST, west);
		
		//	Collection of asserts to get 100% coverage for an Enum
		Assert.assertEquals(4, Direction.values().length);
		
		Assert.assertEquals(Direction.NORTH, Direction.valueOf("NORTH"));
	}

}
