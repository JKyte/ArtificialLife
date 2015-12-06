package brains;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import critters.SimpleCritter;
import utils.ActionTarget;
import utils.Coord;
import utils.CritterAction;
import utils.VisionCritCoord;
import world.Map;

public class SimpleBrainTest {

	private static SimpleBrain brain;
	private static SimpleCritter sc;
	
	@BeforeClass
	public static void beforeTests(){
		brain = new SimpleBrain();
		sc = new SimpleCritter();
		int[] food = new int[1];
		food[0] = 1;
		sc.setFood(food);
		sc.setVision(1);
		brain.setCritter(sc);
	}
	
	@Test
	public void testIsFoodInRange(){
		Map vision = new Map(5);
		int[] food = new int[1];
		food[0] = 1;
		boolean foodExists = brain.isFoodInRange(vision);
		Assert.assertEquals(false, foodExists);
		
		//	Test that organic plant food is in range
		vision.setGrid(0, 1, food[0]);
		foodExists = brain.isFoodInRange(vision);
		Assert.assertEquals(true, foodExists);
	}
	
	@Test
	public void testExploreNoOptionsSoWait(){
		
		Map map = new Map(5);
		
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 1);
		map.setGrid(2, 0, 1);
		
		map.setGrid(0, 1, 1);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 1);
		
		map.setGrid(0, 2, 1);
		map.setGrid(1, 2, 1);
		map.setGrid(2, 2, 1);
		
		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);
		
		ActionTarget at = brain.explore(vcc, true);
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.WAIT, critterAction);
		Assert.assertEquals(worldLocation, target);
	}
	
	@Test
	public void testExploreNorthOption(){
		
		Map map = new Map(5);
		
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 1);
		map.setGrid(2, 0, 1);
		
		map.setGrid(0, 1, 1);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 1);
		
		map.setGrid(0, 2, 1);
		map.setGrid(1, 2, 0);
		map.setGrid(2, 2, 1);
		
		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);
		
		ActionTarget at = brain.explore(vcc, true);
		
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.MOVE, critterAction);
		
		Assert.assertEquals(90, target.getX());
		Assert.assertEquals(91, target.getY());
	}
	
	@Test
	public void testExploreSouthOption(){
		
		Map map = new Map(5);
		
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 0);
		map.setGrid(2, 0, 1);
		
		map.setGrid(0, 1, 1);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 1);
		
		map.setGrid(0, 2, 1);
		map.setGrid(1, 2, 1);
		map.setGrid(2, 2, 1);
		
		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);
		
		ActionTarget at = brain.explore(vcc, true);
		
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.MOVE, critterAction);
		
		Assert.assertEquals(90, target.getX());
		Assert.assertEquals(89, target.getY());
	}
	
	@Test
	public void testExploreEastOption(){
		
		Map map = new Map(5);
		
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 1);
		map.setGrid(2, 0, 1);
		
		map.setGrid(0, 1, 1);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 0);
		
		map.setGrid(0, 2, 1);
		map.setGrid(1, 2, 1);
		map.setGrid(2, 2, 1);
		
		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);
		
		ActionTarget at = brain.explore(vcc, true);
		
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.MOVE, critterAction);
		
		Assert.assertEquals(91, target.getX());
		Assert.assertEquals(90, target.getY());
	}
	
	@Test
	public void testExploreWestOption(){
		
		Map map = new Map(5);
		
		map.setGrid(0, 0, 1);
		map.setGrid(1, 0, 1);
		map.setGrid(2, 0, 1);
		
		map.setGrid(0, 1, 0);
		map.setGrid(1, 1, 2);
		map.setGrid(2, 1, 1);
		
		map.setGrid(0, 2, 1);
		map.setGrid(1, 2, 1);
		map.setGrid(2, 2, 1);
		
		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);
		
		ActionTarget at = brain.explore(vcc, true);
		
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.MOVE, critterAction);
		
		Assert.assertEquals(89, target.getX());
		Assert.assertEquals(90, target.getY());
	}
	
	//	TODO -- Test cardinal directions
	
	//	TODO -- Test NE, NW, SE, SW
	
	@Test
	public void testGetNormalizedDistance(){
		//	Over 2, up 4. Distance should be 4+2-1 = 5
		int expectedDistance = 5, deltaX = 2, deltaY = 4;
		int normalizedDistance = brain.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);
		
		//	Now test adjacent case
		expectedDistance = 0;
		deltaX = 0;
		deltaY = 1;
		normalizedDistance = brain.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);
		
		//	Now test negative deltaX/Y values. Should never encounter this, but it never
		//	hurts to be prepared.
		expectedDistance = 7;
		deltaX = -1;
		deltaY = -7;
		normalizedDistance = brain.getNormalizedDistance(deltaX, deltaY);
		Assert.assertEquals(expectedDistance, normalizedDistance);
	}
	
	@Test
	public void testMoveTowardClosestObject(){
		
		Map map = new Map(9);
		map.setGrid(8, 7, 1);	//	Set food at [1][1]
		map.setGrid(8, 6, 2);	//	Set critter at [0][1]
		map.printLogicalMap();
		
		Coord visionLocation = new Coord(8,6);
		
		VisionCritCoord vcc = map.getVisionMap(1, visionLocation);
		vcc.getVisionMap().printLogicalMap();
		
		ActionTarget at = brain.moveTowardsClosestObject(vcc, 1);
		
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();
		
		Assert.assertEquals(CritterAction.EAT, critterAction);
		
		Assert.assertEquals(8, target.getX());
		Assert.assertEquals(7, target.getY());
	}
	
	//	TODO will eventually have issue where a critter will be surrounded, but can still
	//		see food. This could lead to a situation where critters move on top of each other. 
}
