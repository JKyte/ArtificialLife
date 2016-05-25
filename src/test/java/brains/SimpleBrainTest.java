package brains;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import critters.SimpleCritter;
import utils.ActionTarget;
import utils.Coord;
import utils.CritterAction;
import utils.VisionCritCoord;
import world.BaseMap;

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
		BaseMap vision = new BaseMap(5);
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

		BaseMap map = new BaseMap(5);

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

		BaseMap map = new BaseMap(5);

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

		BaseMap map = new BaseMap(5);

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

		BaseMap map = new BaseMap(5);

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

		BaseMap map = new BaseMap(5);

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
	public void testMoveTowardClosestObject(){

		BaseMap map = new BaseMap(9);
		map.setGrid(8, 7, 1);	//	Set food at [1][1]
		map.setGrid(8, 6, 2);	//	Set critter at [0][1]
		map.printLogicalMap();

		int[] validOpts = new int[]{1};	
		Coord visionLocation = new Coord(8,6);
		VisionCritCoord vcc = map.getVisionMap(1, visionLocation);
		vcc.getVisionMap().printLogicalMap();

		ActionTarget at = brain.moveTowardsClosestFood(vcc, validOpts);

		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();

		Assert.assertEquals(CritterAction.EAT, critterAction);

		Assert.assertEquals(8, target.getX());
		Assert.assertEquals(7, target.getY());
	}

	@Test
	public void testProcessVisionMap_surrounded(){

		BaseMap map = new BaseMap(5);	//	0	0	0	0	0
								//	0	7	7	7	0
		map.setGrid(1, 1, 7);	//	0	7	2	7	0
		map.setGrid(2, 1, 7);	//	0	7	7	7	0
		map.setGrid(3, 1, 7);	//	0	0	0	0	0

		map.setGrid(1, 2, 7);
		map.setGrid(2, 2, 2);
		map.setGrid(3, 2, 7);

		map.setGrid(1, 3, 7);
		map.setGrid(2, 3, 7);
		map.setGrid(3, 3, 7);

		Coord visionLocation = new Coord(2,2);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);

		ActionTarget at = brain.processVisionMap(vcc);
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();

		Assert.assertEquals(CritterAction.WAIT, critterAction);
		Assert.assertEquals(worldLocation, target);
	}

	@Test
	public void testProcessVisionMap_emptySpace(){

		BaseMap map = new BaseMap(5);	//	0	0	0	0	0
								//	0	0	0	0	0
		map.setGrid(2, 2, 2);	//	0	0	2	0	0
								//	0	0	0	0	0
								//	0	0	0	0	0

		Coord visionLocation = new Coord(2,2);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);

		ActionTarget at = brain.processVisionMap(vcc);
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();

		Assert.assertEquals(CritterAction.MOVE, critterAction);
		Assert.assertNotEquals(worldLocation, target);
	}

	@Test
	public void testProcessVisionMap_eatSomeFood(){

		BaseMap map = new BaseMap(5);	//	0	0	0	0	0
								//	0	0	1	0	0
		map.setGrid(2, 2, 2);	//	0	0	2	0	0
								//	0	0	0	0	0
		map.setGrid(2, 3, 1);	//	0	0	0	0	0

		Coord visionLocation = new Coord(2,2);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);

		ActionTarget at = brain.processVisionMap(vcc);
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();

		Assert.assertEquals(CritterAction.EAT, critterAction);
		Assert.assertEquals(90, target.getX());
		Assert.assertEquals(91, target.getY());
	}

	@Test
	public void testProcessVisionMap_moveTowardsFood(){

		BaseMap map = new BaseMap(7);	//	0	0	0	0	0
								//	0	0	0	1	0
		map.setGrid(1, 1, 2);	//	0	0	0	0	0
								//	0	2	0	0	0
		map.setGrid(3, 3, 1);	//	0	0	0	0	0

		Coord visionLocation = new Coord(1,1);
		Coord worldLocation = new Coord(90,90);
		VisionCritCoord vcc = new VisionCritCoord(map, visionLocation, worldLocation);

		ActionTarget at = brain.processVisionMap(vcc);
		CritterAction critterAction = at.getAction();
		Coord target = at.getTarget();

		Assert.assertEquals(CritterAction.MOVE, critterAction);
		Assert.assertEquals(91, target.getX());
		Assert.assertEquals(91, target.getY());
	}
	
	//	TODO will eventually have issue where a critter will be surrounded, but can still
	//		see food. This could lead to a situation where critters move on top of each other. 
}
