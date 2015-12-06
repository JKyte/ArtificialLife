package utils;


import org.junit.Assert;
import org.junit.Test;

import utils.Coord;

public class CoordTest {
	
	private Coord coord;
	
	@Test
	public void testCoord(){
		coord = new Coord(3,4);
		
		Assert.assertEquals(3, coord.getX());
		Assert.assertEquals(4, coord.getY());
		Assert.assertEquals("3 4", coord.toString());
		
		
		coord.setX(8);
		coord.setY(7);
		Assert.assertEquals(8, coord.getX());
		Assert.assertEquals(7, coord.getY());
		Assert.assertEquals("8 7", coord.toString());
	}

}
