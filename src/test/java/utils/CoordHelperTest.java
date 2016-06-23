package utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CoordHelperTest {
	
	@Test
	public void testInitOfStaticClassBecauseCodeCoverageReasons(){
		CoordHelper helper = new CoordHelper();
		Assert.assertEquals( CoordHelper.class, helper.getClass());
	}

	@Test
	public void testGetNormalizedDistance_int(){
		//	Over 2, up 4. Distance should be 4+2-2 = 4
		int expectedDistance = 4, deltaX = 2, deltaY = 4;
		int normalizedDistance = CoordHelper.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);

		//	Now test adjacent case
		expectedDistance = 0;
		deltaX = 0;
		deltaY = 1;
		normalizedDistance = CoordHelper.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);

		//	Now test negative deltaX/Y values. Should never encounter this, but it never
		//	hurts to be prepared.
		expectedDistance = 6;
		deltaX = -1;
		deltaY = -7;
		normalizedDistance = CoordHelper.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);


	}

	@Test
	public void testGetNormalizedDistance_coord(){
		
		int expectedDistance = 4, 
				normalizedDistance;
		
		//	Above case, but with coords
		Coord start = new Coord(2,2);
		Coord end = new Coord(4,6);
		normalizedDistance = CoordHelper.getNormalizedDistance(start, end);
		Assert.assertEquals(expectedDistance, normalizedDistance);

		//		Negative case, but with coords
		expectedDistance = 0;
		start = new Coord(4,4);
		end = new Coord(3,3);
		normalizedDistance = CoordHelper.getNormalizedDistance(start, end);
		Assert.assertEquals(expectedDistance, normalizedDistance);
	}
	
	@Test
	public void testGetClosestCoord(){
		Coord center = new Coord(0,0);
		
		Coord dest1 = new Coord(0,50);
		Coord dest2 = new Coord(0,25);	//	Hint, this guy is closest
		Coord dest3 = new Coord(0,35);
	
		List<Coord> destinations = new ArrayList<Coord>();
		destinations.add(dest1);
		destinations.add(dest2);
		destinations.add(dest3);
		
		Coord closest = CoordHelper.getClosestCoord(center, destinations);
		
		Assert.assertEquals(dest2.toString(), closest.toString());
	}

}
