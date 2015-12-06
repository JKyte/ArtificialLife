package brains;

import java.util.ArrayList;
import java.util.Random;

import critters.SimpleCritter;
import utils.CritterAction;
import utils.Direction;
import world.ActionTarget;
import world.Coord;
import world.Map;
import world.VisionCritCoord;

public class SimpleBrain {

	private SimpleCritter critter;

	public SimpleBrain(){

	}

	/**
	 * THIS IS THE NEW, BETTER, AND REFACTORED VERSION<br><br>
	 * 
	 * This method accepts a VisionCritCoord object and returns a valid ActionTarget
	 * to the method that called it.
	 * 
	 * <br>
	 * <br>NOTE: The class that calls this method is responsible for updating Critters and Maps
	 * as appropriate
	 * 
	 * @param visionMap - a VisionCritCoord object
	 * @returns actionTarget - an ActionTarget containing the address-translated coordinate
	 */
	public ActionTarget processVisionMap( VisionCritCoord visionMap ){
		ActionTarget actionTarget = null;

		//	Currently unused, just groundwork for future update
		boolean foodInRange = isFoodInRange( visionMap.getVisionMap());

		if( foodInRange ){
			//	Then move towards food
			actionTarget = null;	
			
			//	Placeholder until moveTowardsClosestObject() is integrated
			actionTarget = explore(visionMap, false);
			
		}else{
			actionTarget = explore(visionMap, false);
		}
		
		actionTarget = explore(visionMap, false);
		
		return actionTarget;
	}

	/**
	 * Quickly determines if food is in range of the critter
	 * @param map - a 2 dimensional int array representing the current creature's vision
	 * @returns - a boolean indicating if food is in range
	 */
	public boolean isFoodInRange(Map map) {
		map.printLogicalMap();
		System.out.println("h: " + map.mapHeight + " w: " + map.mapWidth);
		for( int ii = 0; ii < map.mapWidth; ii++ ){
			for( int jj = 0; jj < map.mapHeight; jj++ ){
				for( int kk = 0; kk < critter.getFood().length; kk++){
					System.out.println("h: " + ii + " w: " + jj);
					if( map.getGrid(ii, jj) == critter.getFood()[kk]){	
						return true;
					}	
				}
			}
		}
		return false;
	}

	/**
	 * This method finds an open tile to move towards, currently only checks cardinal
	 * direction (NORTH, SOUTH, EAST, WEST)
	 * 
	 * @param vcc -- a VisionCritCoord object
	 * @param verbose -- a flag for debug purposes
	 * @returns an ActionTarget with coords on the WORLD_MAP
	 */
	public ActionTarget explore( VisionCritCoord vcc, boolean verbose ){

		Map vision = vcc.getVisionMap();
		Coord visionLocation = vcc.getCritterVisionLocation();
		ArrayList<Coord> validDestinations = new ArrayList<Coord>();

		vision.printLogicalMap();

		/**
		 * 	This checks each square immediately surrounding the critter's vision
		 * location for a valid path
		 */
		for(int ii = -1; ii < 2; ii++ ){
			for(int jj = -1; jj < 2; jj++ ){
				//	System.out.println(ii + " " + jj);
				if( vision.getGrid(visionLocation.getX()+ii, visionLocation.getY()+jj) == 0 ){
					validDestinations.add(new Coord(visionLocation.getX()+ii, visionLocation.getY()+jj));
				}
			}
		}

		/**
		 * If no valid directions exist, then a WAIT action will be returned. If there is more than one valid direction available
		 * then a random direction will be picked.
		 */
		if( validDestinations.size() == 0 ){
			return new ActionTarget( CritterAction.WAIT, vcc.getCritterWorldLocation());
		}else{
			Random rand = new Random();

			Coord c = validDestinations.get(rand.nextInt(validDestinations.size()));
			c.setX(c.getX()-visionLocation.getX());
			c.setY(c.getY()-visionLocation.getY());

			Coord worldDest = new Coord(vcc.getCritterWorldLocation());

			System.out.println(worldDest.getX() + " " + worldDest.getY());
			System.out.println( c.getX() + " " + c.getY());

			worldDest.setX(worldDest.getX()+c.getX());
			worldDest.setY(worldDest.getY()+c.getY());

			System.out.println(worldDest.getX() + " " + worldDest.getY());			

			return new ActionTarget( CritterAction.MOVE, worldDest);
		}
	}

	/**
	 * This will likely have to be copied and modified to handle "move towards food/range of objects"
	 * 
	 * TODO -- refactor names on this
	 * 
	 * @param visionMap
	 * @param n
	 * @return
	 */
	public ActionTarget moveTowardsClosestObject( VisionCritCoord visionMap, int n ){

		CritterAction action = null;	//	the action we will take
		Coord worldDelta = null;
		Coord worldLocation = visionMap.getCritterWorldLocation();
		Coord skipCoord = visionMap.getCritterVisionLocation();
		Direction dir = null;	//	direction to aid setting of target
		Map vision = visionMap.getVisionMap();

		int normalizedDistance = Integer.MAX_VALUE;

		/**
		 * 1. Iterate across the vision map, 
		 * 		a. For each food, get normalized distance from the delta X, delta Y
		 * 2. If normalized == 0, then eat. Else move.
		 */

		/**
		 * This will have to be edited to be dynamic for a vision length of greater than 1
		 */
		System.out.println("x:" + vision.mapWidth + " y:" + vision.mapHeight);
		for(int ii = 0; ii < vision.mapWidth; ii++ ){
			for(int jj = 0; jj < vision.mapHeight; jj++ ){
			//	if( ii != skipCoord.getX() && jj != skipCoord.getY() ){

					//for( int kk = 0; kk < critter.getFood().length; kk++){
					if( vision.getGrid(ii, jj) == 1){
						int deltaX = skipCoord.getX()-ii;
						int deltaY = skipCoord.getY()-jj;

						int tmpNormalizedDistance = getNormalizedDistance(deltaX, deltaY);
						System.out.println("TmpNormDist: " + tmpNormalizedDistance);

						if( tmpNormalizedDistance < normalizedDistance ){
							normalizedDistance = tmpNormalizedDistance;
							worldDelta = new Coord(ii,jj);
						}
					}
					//}
			//	}
			}	
		}


		if( normalizedDistance == 0 ){
			action = CritterAction.EAT;
		}
		
		System.out.println("tarX: " + worldDelta.getX() + " tarY: " + worldDelta.getY());
		
		//	Normalize world delta against vision location
		worldDelta.setX( (worldDelta.getX()-skipCoord.getX()));
		worldDelta.setY( (worldDelta.getY()-skipCoord.getY()));
		
		System.out.println("tarX: " + worldDelta.getX() + " tarY: " + worldDelta.getY());
		
		
		System.out.println("tarX: " + worldLocation.getX() + " tarY: " + worldLocation.getY());
		
		

		worldLocation.setX(worldLocation.getX()+worldDelta.getX());
		worldLocation.setY(worldLocation.getY()+worldDelta.getY());

		System.out.println("tarX: " + worldLocation.getX() + " tarY: " + worldLocation.getY());
		
		return new ActionTarget( action, worldLocation);
	}

	/**
	 * <br>This method calculates the distance until a critter is adjacent to an object such that
	 * <br>
	 * <br>(deltaX + deltaY - adjacentSpace) = normalizedDistance
	 * <br> 2 + 4 - 1 = 5
	 * 
	 * @param deltaX
	 * @param deltaY
	 * @return
	 */
	public int getNormalizedDistance( int deltaX, int deltaY ){
		if( deltaX < 0 ) deltaX *= -1;
		if( deltaY < 0 ) deltaY *= -1;
		return deltaX+deltaY-1;	//	reduce actual distance by one so adjacent food source comes out as zero
	}

	public void setCritter(SimpleCritter critter) {
		this.critter = critter;
	}

}
