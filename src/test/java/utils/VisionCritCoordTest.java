package utils;

import org.junit.Assert;
import org.junit.Test;

import utils.Coord;
import utils.VisionCritCoord;
import world.Map;

public class VisionCritCoordTest {

	private VisionCritCoord vcc;
	
	@Test
	public void testVisionCritCoord(){
		
		Map vision = new Map(2);
		vision.setGrid(0, 0, 0);
		vision.setGrid(0, 1, 1);
		vision.setGrid(1, 0, 2);
		vision.setGrid(1, 1, 3);
		
		Coord critVisionLoc = new Coord(1,0);
		Coord critWorldLoc = new Coord(1,0);
		
		vcc = new VisionCritCoord(vision, critVisionLoc, critWorldLoc);
		
		Map critVision = vcc.getVisionMap();
		Assert.assertEquals(0, critVision.getGrid(0, 0));
		Assert.assertEquals(1, critVision.getGrid(0, 1));
		Assert.assertEquals(2, critVision.getGrid(1, 0));
		Assert.assertEquals(3, critVision.getGrid(1, 1));
		
		Coord visionLocation = vcc.getCritterVisionLocation();
		Assert.assertEquals(1, visionLocation.getX());
		Assert.assertEquals(0, visionLocation.getY());
		
		Coord worldLocation = vcc.getCritterWorldLocation();
		Assert.assertEquals(1, worldLocation.getX());
		Assert.assertEquals(0, worldLocation.getY());

		vcc.setCritterVisionLocation(null);
		vcc.setCritterWorldLocation(null);
		vcc.setVisionMap(null);
		
		Assert.assertEquals(null, vcc.getCritterVisionLocation());
		Assert.assertEquals(null, vcc.getCritterWorldLocation());
		Assert.assertNull(vcc.getVisionMap());
	}
}
