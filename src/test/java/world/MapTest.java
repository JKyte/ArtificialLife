package world;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This class will test many of the possible cases possible when getting a vision map
 * from the world map. Notable cases are listed below
 * 
 * - when a vision map's boundaries align with the map (i.e., a perfect fit in a corner)
 * - when a vision map's boundaries go outside the map (and must return a smaller map, which
 * 		causes offsets to occur)
 * 
 */
public class MapTest {

	private static Map map;

	@BeforeClass
	public static void setup(){

		/**
		 * LOGICAL map [x][y]
		 * +-----------+
		 * | 6 7 8 9 0 |
		 * | 9 0 1 2 1 |
		 * | 4 5 6 3 2 |
		 * | 1 2 7 4 3 |
		 * | 0 3 8 5 4 |
		 * +-----------+
		 * 
		 * ACTUAL map [y][x]
		 * +-----------+
		 * | 0 1 4 9 6 |
		 * | 3 2 5 0 7 |
		 * | 8 7 6 1 8 |
		 * | 5 4 3 2 9 |
		 * | 4 3 2 1 0 |
		 * +-----------+
		 */
		map = new Map(5);

		map.setGrid(0, 0, 0);
		map.setGrid(1, 0, 3);
		map.setGrid(2, 0, 8);
		map.setGrid(3, 0, 5);
		map.setGrid(4, 0, 4);

		map.setGrid(0, 1, 1);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 7);
		map.setGrid(3, 1, 4);
		map.setGrid(4, 1, 3);

		map.setGrid(0, 2, 4);
		map.setGrid(1, 2, 5);
		map.setGrid(2, 2, 6);
		map.setGrid(3, 2, 3);
		map.setGrid(4, 2, 2);

		map.setGrid(0, 3, 9);
		map.setGrid(1, 3, 0);
		map.setGrid(2, 3, 1);
		map.setGrid(3, 3, 2);
		map.setGrid(4, 3, 1);

		map.setGrid(0, 4, 6);
		map.setGrid(1, 4, 7);
		map.setGrid(2, 4, 8);
		map.setGrid(3, 4, 9);
		map.setGrid(4, 4, 0);
	}

	@Test
	public void testActualPrint(){
		map.printActualMap();
	}



	@Test
	public void testLogicalPrint(){
		map.printLogicalMap();
	}

	/**
	 * BEGIN tests for basic object placement and movement.
	 */

	/**
	 * This test places three different objects
	 */
	@Test
	public void testPlaceObjectAtCoord(){
		//	Instantiate a 1x1 map
		Map map = new Map(3,3);
		//	map.printLogicalMap(map.map);

		//	Place object at coord
		map.placeObjectAtCoord(7, new Coord(2,1));
		Assert.assertEquals(7, map.getGrid(2, 1));
		//	map.printLogicalMap(map.map);

		//	Clear map
		map.removeObject(new Coord(2,1));
		//	map.printLogicalMap(map.map);

		//	Place another object at coord
		map.placeObjectAtCoord(4, new Coord(1,1), true);
		Assert.assertEquals(4, map.getGrid(1, 1));
		//	map.printLogicalMap(map.map);

		//	Clear map
		map.removeObject(new Coord(1,1));
		//	map.printLogicalMap(map.map);

		//	Place third and final object
		map.placeObjectAtCoord(2, new Coord(0,1), false);
		Assert.assertEquals(2, map.getGrid(0, 1));
	}

	@Test
	public void testMoveObject(){
		//	Instantiate 2x2 map
		Map map = new Map(2,2);
		//	map.printLogicalMap(map.map);

		//	Place object at logical(x,y) = [0][0]
		map.placeObjectAtCoord(9, new Coord(0,0));
		//	map.printLogicalMap(map.map);
		Assert.assertEquals(9, map.getObjectAtCoord(new Coord(0,0)));
		Assert.assertEquals(0, map.getObjectAtCoord(new Coord(0,1)));
		Assert.assertEquals(9, map.getGrid(0, 0));
		Assert.assertEquals(0, map.getGrid(0, 1));

		//	Clear map
		map.moveObject(new Coord(0,0), new Coord(0,1));
		//	map.printLogicalMap(map.map);
		Assert.assertEquals(0, map.getObjectAtCoord(new Coord(0,0)));
		Assert.assertEquals(9, map.getObjectAtCoord(new Coord(0,1)));	
		Assert.assertEquals(9, map.getGrid(0, 1));
		Assert.assertEquals(0, map.getGrid(0, 0));
	}

	/**
	 * Verified prints by manual inspection
	 */
	@Test
	public void testRectangleInit(){
		Map map = new Map(2,4);
		map.printLogicalMap();
		map.printActualMap();

		Assert.assertTrue(true);
	}

	/**
	 * End unit tests for basic object placement and movement
	 * 
	 * Begin standard cases where the bounds for a vision map fall within the map.
	 * NOTE: These test cases only cover critters with a vision length of 1 (ONE)
	 * 
	 * First five test cases are CENTER, then nested NORTH, SOUTH, EAST, WEST
	 *
	 * This first test case also tests the non-verbose getVisionMap()
	 */
	@Test
	public void testGetVisionMap_centered(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(2,2);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;

		critterWorldLocation = vcc.getCritterWorldLocation();
		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(2, vision.getGrid(0, 0));
		Assert.assertEquals(7, vision.getGrid(1, 0));
		Assert.assertEquals(4, vision.getGrid(2, 0));

		Assert.assertEquals(5, vision.getGrid(0, 1));
		Assert.assertEquals(6, vision.getGrid(1, 1));
		Assert.assertEquals(3, vision.getGrid(2, 1));

		Assert.assertEquals(0, vision.getGrid(0, 2));
		Assert.assertEquals(1, vision.getGrid(1, 2));
		Assert.assertEquals(2, vision.getGrid(2, 2));

		Assert.assertEquals(2, critterWorldLocation.getX());
		Assert.assertEquals(2, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(6, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}



	/**
	 * Test the North-Centered, nested case
	 */
	@Test
	public void testGetVisionMap_north_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(2,3);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;

		critterWorldLocation = vcc.getCritterWorldLocation();
		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(5, vision.getGrid(0, 0));
		Assert.assertEquals(6, vision.getGrid(1, 0));
		Assert.assertEquals(3, vision.getGrid(2, 0));

		Assert.assertEquals(0, vision.getGrid(0, 1));
		Assert.assertEquals(1, vision.getGrid(1, 1));
		Assert.assertEquals(2, vision.getGrid(2, 1));

		Assert.assertEquals(7, vision.getGrid(0, 2));
		Assert.assertEquals(8, vision.getGrid(1, 2));
		Assert.assertEquals(9, vision.getGrid(2, 2));

		Assert.assertEquals(2, critterWorldLocation.getX());
		Assert.assertEquals(3, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(1, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the South, nested case
	 */
	@Test
	public void testGetVisionMap_south_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(2,1);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(3, vision.getGrid(0, 0));
		Assert.assertEquals(8, vision.getGrid(1, 0));
		Assert.assertEquals(5, vision.getGrid(2, 0));

		Assert.assertEquals(2, vision.getGrid(0, 1));
		Assert.assertEquals(7, vision.getGrid(1, 1));
		Assert.assertEquals(4, vision.getGrid(2, 1));

		Assert.assertEquals(5, vision.getGrid(0, 2));
		Assert.assertEquals(6, vision.getGrid(1, 2));
		Assert.assertEquals(3, vision.getGrid(2, 2));

		Assert.assertEquals(2, critterWorldLocation.getX());
		Assert.assertEquals(1, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(7, vision.getGrid(critterVisionLocation.getX(),critterVisionLocation.getY()));
	}

	/**
	 * Test the East, nested case
	 */
	@Test
	public void testGetVisionMap_east_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(3,2);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(7, vision.getGrid(0, 0));
		Assert.assertEquals(4, vision.getGrid(1, 0));
		Assert.assertEquals(3, vision.getGrid(2, 0));

		Assert.assertEquals(6, vision.getGrid(0, 1));
		Assert.assertEquals(3, vision.getGrid(1, 1));
		Assert.assertEquals(2, vision.getGrid(2, 1));

		Assert.assertEquals(1, vision.getGrid(0, 2));
		Assert.assertEquals(2, vision.getGrid(1, 2));
		Assert.assertEquals(1, vision.getGrid(2, 2));

		Assert.assertEquals(3, critterWorldLocation.getX());
		Assert.assertEquals(2, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(3, vision.getGrid(critterVisionLocation.getX(),critterVisionLocation.getY()));
	}

	/**
	 * Test the West, nested case
	 */
	@Test
	public void testGetVisionMap_west_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(1,2);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(1, vision.getGrid(0, 0));
		Assert.assertEquals(2, vision.getGrid(1, 0));
		Assert.assertEquals(7, vision.getGrid(2, 0));

		Assert.assertEquals(4, vision.getGrid(0, 1));
		Assert.assertEquals(5, vision.getGrid(1, 1));
		Assert.assertEquals(6, vision.getGrid(2, 1));

		Assert.assertEquals(9, vision.getGrid(0, 2));
		Assert.assertEquals(0, vision.getGrid(1, 2));
		Assert.assertEquals(1, vision.getGrid(2, 2));

		Assert.assertEquals(1, critterWorldLocation.getX());
		Assert.assertEquals(2, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(5, vision.getGrid(critterVisionLocation.getX(),critterVisionLocation.getY()));
	}

	/**
	 * End N,S,E,W nested cases, begin NE, NW, SE, SW
	 */

	/**
	 * Test the North-East, nested case
	 */
	@Test
	public void testGetVisionMap_northEast_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(3,3);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(6, vision.getGrid(0, 0));
		Assert.assertEquals(3, vision.getGrid(1, 0));
		Assert.assertEquals(2, vision.getGrid(2, 0));

		Assert.assertEquals(1, vision.getGrid(0, 1));
		Assert.assertEquals(2, vision.getGrid(1, 1));
		Assert.assertEquals(1, vision.getGrid(2, 1));

		Assert.assertEquals(8, vision.getGrid(0, 2));
		Assert.assertEquals(9, vision.getGrid(1, 2));
		Assert.assertEquals(0, vision.getGrid(2, 2));

		Assert.assertEquals(3, critterWorldLocation.getX());
		Assert.assertEquals(3, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(2, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}


	/**
	 * Test the North-West, nested case
	 */
	@Test
	public void testGetVisionMap_northWest_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(1,3);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(4, vision.getGrid(0, 0));
		Assert.assertEquals(5, vision.getGrid(1, 0));
		Assert.assertEquals(6, vision.getGrid(2, 0));

		Assert.assertEquals(9, vision.getGrid(0, 1));
		Assert.assertEquals(0, vision.getGrid(1, 1));
		Assert.assertEquals(1, vision.getGrid(2, 1));

		Assert.assertEquals(6, vision.getGrid(0, 2));
		Assert.assertEquals(7, vision.getGrid(1, 2));
		Assert.assertEquals(8, vision.getGrid(2, 2));

		Assert.assertEquals(1, critterWorldLocation.getX());
		Assert.assertEquals(3, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(0, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the South-East, nested case
	 */
	@Test
	public void testGetVisionMap_southEast_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(3,1);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(8, vision.getGrid(0, 0));
		Assert.assertEquals(5, vision.getGrid(1, 0));
		Assert.assertEquals(4, vision.getGrid(2, 0));

		Assert.assertEquals(7, vision.getGrid(0, 1));
		Assert.assertEquals(4, vision.getGrid(1, 1));
		Assert.assertEquals(3, vision.getGrid(2, 1));

		Assert.assertEquals(6, vision.getGrid(0, 2));
		Assert.assertEquals(3, vision.getGrid(1, 2));
		Assert.assertEquals(2, vision.getGrid(2, 2));

		Assert.assertEquals(3, critterWorldLocation.getX());
		Assert.assertEquals(1, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(4, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the South-East, nested case
	 */
	@Test
	public void testGetVisionMap_southWest_inBounds(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(1,1);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(0, vision.getGrid(0, 0));
		Assert.assertEquals(3, vision.getGrid(1, 0));
		Assert.assertEquals(8, vision.getGrid(2, 0));

		Assert.assertEquals(1, vision.getGrid(0, 1));
		Assert.assertEquals(2, vision.getGrid(1, 1));
		Assert.assertEquals(7, vision.getGrid(2, 1));

		Assert.assertEquals(4, vision.getGrid(0, 2));
		Assert.assertEquals(5, vision.getGrid(1, 2));
		Assert.assertEquals(6, vision.getGrid(2, 2));

		Assert.assertEquals(1, critterWorldLocation.getX());
		Assert.assertEquals(1, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(2, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}


	/**
	 * End standard test cases, begin edge cases where the vision falls off the map. This
	 * results in a non-standard vision map.
	 * 
	 * NOTE: These test cases only cover critters with a vision length of 1 (ONE)
	 *
	 * Test the NORTH edge case.
	 */
	@Test
	public void testGetVisionMap_north_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(2,4);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(0, vision.getGrid(0, 0));
		Assert.assertEquals(1, vision.getGrid(1, 0));
		Assert.assertEquals(2, vision.getGrid(2, 0));

		Assert.assertEquals(7, vision.getGrid(0, 1));
		Assert.assertEquals(8, vision.getGrid(1, 1));
		Assert.assertEquals(9, vision.getGrid(2, 1));

		Assert.assertEquals(2, critterWorldLocation.getX());
		Assert.assertEquals(4, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(8, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the WEST edge case
	 */
	@Test
	public void testGetVisionMap_west_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(0,2);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(1, vision.getGrid(0, 0));
		Assert.assertEquals(2, vision.getGrid(1, 0));

		Assert.assertEquals(4, vision.getGrid(0, 1));
		Assert.assertEquals(5, vision.getGrid(1, 1));

		Assert.assertEquals(9, vision.getGrid(0, 2));
		Assert.assertEquals(0, vision.getGrid(1, 2));

		Assert.assertEquals(0, critterWorldLocation.getX());
		Assert.assertEquals(2, critterWorldLocation.getY());

		Assert.assertEquals(0, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(4, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the SOUTH edge case
	 */
	@Test
	public void testGetVisionMap_south_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(2,0);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(3, vision.getGrid(0, 0));
		Assert.assertEquals(8, vision.getGrid(1, 0));
		Assert.assertEquals(5, vision.getGrid(2, 0));

		Assert.assertEquals(2, vision.getGrid(0, 1));
		Assert.assertEquals(7, vision.getGrid(1, 1));
		Assert.assertEquals(4, vision.getGrid(2, 1));

		Assert.assertEquals(2, critterWorldLocation.getX());
		Assert.assertEquals(0, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(0, critterVisionLocation.getY());

		Assert.assertEquals(8, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Test the EAST edge case. Believe the test case is correct
	 */
	@Test
	public void testGetVisionMap_east_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(4,2);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(4, vision.getGrid(0, 0));
		Assert.assertEquals(3, vision.getGrid(1, 0));

		Assert.assertEquals(3, vision.getGrid(0, 1));
		Assert.assertEquals(2, vision.getGrid(1, 1));

		Assert.assertEquals(2, vision.getGrid(0, 2));
		Assert.assertEquals(1, vision.getGrid(1, 2));

		Assert.assertEquals(4, critterWorldLocation.getX());
		Assert.assertEquals(2, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(2, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * Still edge cases, now covering the corner edge cases
	 *
	 * 	NORTH-WEST corner
	 */
	@Test
	public void testGetVisionMap_northWest_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(0,4);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(9, vision.getGrid(0, 0));
		Assert.assertEquals(0, vision.getGrid(1, 0));

		Assert.assertEquals(6, vision.getGrid(0, 1));
		Assert.assertEquals(7, vision.getGrid(1, 1));

		Assert.assertEquals(0, critterWorldLocation.getX());
		Assert.assertEquals(4, critterWorldLocation.getY());

		Assert.assertEquals(0, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(6, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * NORTH-EAST corner
	 */
	@Test
	public void testGetVisionMap_northEast_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(4,4);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(2, vision.getGrid(0, 0));
		Assert.assertEquals(1, vision.getGrid(1, 0));

		Assert.assertEquals(9, vision.getGrid(0, 1));
		Assert.assertEquals(0, vision.getGrid(1, 1));

		Assert.assertEquals(4, critterWorldLocation.getX());
		Assert.assertEquals(4, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(1, critterVisionLocation.getY());

		Assert.assertEquals(0, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * SOUTH-WEST corner
	 */
	@Test
	public void testGetVisionMap_southWest_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(0,0);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(0, vision.getGrid(0, 0));
		Assert.assertEquals(3, vision.getGrid(1, 0));

		Assert.assertEquals(1, vision.getGrid(0, 1));
		Assert.assertEquals(2, vision.getGrid(1, 1));

		Assert.assertEquals(0, critterWorldLocation.getX());
		Assert.assertEquals(0, critterWorldLocation.getY());

		Assert.assertEquals(0, critterVisionLocation.getX());
		Assert.assertEquals(0, critterVisionLocation.getY());

		Assert.assertEquals(0, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * SOUTH-EAST corner
	 */
	@Test
	public void testGetVisionMap_southEast_edge(){
		//	map.printLogicalMap();
		Coord critterWorldLocation = new Coord(4,0);
		VisionCritCoord vcc = map.getVisionMap(1, critterWorldLocation, false);

		Map vision = vcc.getVisionMap();
		critterWorldLocation = null;
		critterWorldLocation = vcc.getCritterWorldLocation();

		Coord critterVisionLocation = vcc.getCritterVisionLocation();
		//	vision.printLogicalMap();

		Assert.assertEquals(5, vision.getGrid(0, 0));
		Assert.assertEquals(4, vision.getGrid(1, 0));

		Assert.assertEquals(4, vision.getGrid(0, 1));
		Assert.assertEquals(3, vision.getGrid(1, 1));

		Assert.assertEquals(4, critterWorldLocation.getX());
		Assert.assertEquals(0, critterWorldLocation.getY());

		Assert.assertEquals(1, critterVisionLocation.getX());
		Assert.assertEquals(0, critterVisionLocation.getY());

		Assert.assertEquals(4, vision.getGrid(critterVisionLocation.getX(), critterVisionLocation.getY()));
	}

	/**
	 * End the edge cases and corner edge cases for critters with a vision length of 1 (ONE).
	 * 
	 * NOTE: Will likely have to add more unit tests when vision length becomes an evolvable trait
	 */

	@Test
	public void testBadGetGrids(){
		int result = map.getGrid(0, -1);
		Assert.assertEquals(-1, result);
		
		result = map.getGrid(-1, 0);
		Assert.assertEquals(-1, result);
		
		result = map.getGrid(0, 200);
		Assert.assertEquals(-1, result);
		
		result = map.getGrid(200, 0);
		Assert.assertEquals(-1, result);
	}
	
	@Test
	public void testBadSetGrids(){
		boolean result = map.setGrid(0, -1, 7);
		Assert.assertFalse(result);
		
		result = map.setGrid(-1, 0, 7);
		Assert.assertFalse(result);
		
		result = map.setGrid(0, 200, 7);
		Assert.assertFalse(result);
		
		result = map.setGrid(200, 0, 7);
		Assert.assertFalse(result);
	}

	@Test
	public void testGetCountForObjectType(){
		Map map = new Map(5,10);
		map.setGrid(0, 0, 2);
		map.setGrid(0, 9, 2);
		map.setGrid(3, 5, 2);
		
		Assert.assertEquals(3, map.getCountForObjectType(2));
	}
	
	@Test
	public void testMapArea(){
		Map map = new Map(5,10);
		//	map.printLogicalMap();
		Assert.assertEquals(50, map.mapArea());
	}

	@Test
	public void testGetMapForGUI(){
		Map map = new Map(2,1);
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 2);

		Assert.assertEquals(1, map.getGrid(0, 0));
		Assert.assertEquals(2, map.getGrid(1, 0));

		String stringForGUI = map.getMapForGUI();

		Assert.assertEquals("1  2  \n", stringForGUI);
	}

	@Test
	public void testGetMapForGUIandStats(){
		Map map = new Map(4,1);
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 2);
		map.setGrid(2, 0, 3);

		String stats = "stats";

		Assert.assertEquals(1, map.getGrid(0, 0));
		Assert.assertEquals(2, map.getGrid(1, 0));
		Assert.assertEquals(3, map.getGrid(2, 0));
		Assert.assertEquals(0, map.getGrid(3, 0));

		String stringForGuiWithStats = map.getMapForGUI(stats);
		String expectedString = "1  2  3  0  \n\nNumFood: 1\nNumCritters: 1\nNumCorpses: 1\nstats";

		Assert.assertEquals(expectedString, stringForGuiWithStats);
	}
}
