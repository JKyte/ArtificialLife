package brains;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import critters.SimpleCritter;
import utils.ActionTarget;
import utils.Coord;
import utils.CoordHelper;
import utils.CritterAction;
import utils.VisionCritCoord;
import world.Map;

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

		boolean foodInRange = isFoodInRange( visionMap.getVisionMap());
		//System.out.println("FoodInRange: " + foodInRange);
		if( foodInRange ){	//	Then move towards food
			//	Default option for finding food is 0,1
			int[] validOpts = new int[]{1};	
			actionTarget = moveTowardsClosestFood(visionMap, validOpts);
		}else{
			actionTarget = explore(visionMap, false);
		}

		return actionTarget;
	}

	/**
	 * Quickly determines if food is in range of the critter
	 * @param map - a 2 dimensional int array representing the current creature's vision
	 * @returns - a boolean indicating if food is in range
	 */
	public boolean isFoodInRange(Map map) {
		//map.printLogicalMap();
		//System.out.println("h: " + map.mapHeight + " w: " + map.mapWidth);
		for( int ii = 0; ii < map.mapWidth; ii++ ){
			for( int jj = 0; jj < map.mapHeight; jj++ ){
				for( int kk = 0; kk < critter.getFood().length; kk++){
					//System.out.println("h: " + ii + " w: " + jj);
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

		int[] validOpts = new int[]{0};	//	Default option for exploring is open spaces
		ArrayList<Coord> validMoves = getValidMoves(vcc, validOpts);

		/**
		 * If no valid directions exist, then a WAIT action will be returned. If there is more than one valid direction available
		 * then a random direction will be picked.
		 */
		if( validMoves.size() == 0 ){
			return new ActionTarget( CritterAction.WAIT, vcc.getCritterWorldLocation());
		}else{
			Random rand = new Random();

			Coord c = validMoves.get(rand.nextInt(validMoves.size()));
			c.setX(c.getX()-vcc.getCritterVisionLocation().getX());
			c.setY(c.getY()-vcc.getCritterVisionLocation().getY());

			Coord worldDest = new Coord(vcc.getCritterWorldLocation());

			//System.out.println(worldDest.getX() + " " + worldDest.getY());
			//System.out.println( c.getX() + " " + c.getY());

			worldDest.setX(worldDest.getX()+c.getX());
			worldDest.setY(worldDest.getY()+c.getY());

			//System.out.println(worldDest.getX() + " " + worldDest.getY());			

			return new ActionTarget( CritterAction.MOVE, worldDest);
		}
	}

	public ArrayList<Coord> getValidMoves( VisionCritCoord vcc, int[] validOpts ){
		Map vision = vcc.getVisionMap();
		Coord visionLocation = vcc.getCritterVisionLocation();
		ArrayList<Coord> validDestinations = new ArrayList<Coord>();
		//vision.printLogicalMap();

		/**
		 * 	This checks each square immediately surrounding the critter's vision
		 * location for a valid path
		 */
		boolean validDirection = false;
		for(int ii = -1; ii < 2; ii++ ){
			for(int jj = -1; jj < 2; jj++ ){
				//	System.out.println(ii + " " + jj);
				validDirection = false;
				for( int kk = 0; kk < validOpts.length; kk++ ){
					if( vision.getGrid(visionLocation.getX()+ii, visionLocation.getY()+jj) == validOpts[kk] ){
						validDirection = true;
					}
				}

				if( validDirection ){
					validDestinations.add(new Coord(visionLocation.getX()+ii, visionLocation.getY()+jj));
				}
			}
		}
		//System.out.println("Returning " + validDestinations.size() + " valid destinations.");
		return validDestinations;
	}

	/**
	 * This is a "move towards closest object" method that is used for moving towards food.
	 * 
	 * @param visionMap
	 * @param n
	 * @return
	 */
	public ActionTarget moveTowardsClosestFood( VisionCritCoord visionMap, int[] validOpts){

		/**
		 * 1. Find all valid destinations
		 * 2. Determine normalized distance of all destinations, if any normalized value == 0 then return ActionTarget
		 * 3. Else, determine all valid moves.
		 * 4. Evaluate which move gives the smallest normalized distance. 
		 */

		CritterAction action = null;	//	the ACTION to be taken by the critter, either MOVE, EAT, or WAIT
		Coord target = null;			//	the TARGET for the critter. May be critters location (wait) or adjacent (move)

		Coord worldLocation = visionMap.getCritterWorldLocation();
		Coord critterLocation = visionMap.getCritterVisionLocation();
		Map vision = visionMap.getVisionMap();
		int normalizedDistance = Integer.MAX_VALUE;

		ArrayList<Coord> foodCoords = new ArrayList<Coord>();

		//System.out.println("x:" + vision.mapWidth + " y:" + vision.mapHeight);
		for(int ii = 0; ii < vision.mapWidth; ii++ ){
			for(int jj = 0; jj < vision.mapHeight; jj++ ){
				for( int kk = 0; kk < validOpts.length; kk++ ){
					if( vision.getGrid(ii, jj) == validOpts[kk] ){
						foodCoords.add(new Coord(ii,jj));
					}
				}
			}
		}

		//	Coord closest = getClosestCoord(critterLocation, foodCoords );	//	duplicate.

		//	Find closest food
		int tmpDistance;
		Coord closest = null;
		for( Coord food: foodCoords ){

			tmpDistance = CoordHelper.getNormalizedDistance(food, critterLocation);

			//System.out.println("Compare " + food.toString() + " with " + critterLocation.toString() + " -> " + tmpDistance);
			if( tmpDistance < normalizedDistance ){
				normalizedDistance = tmpDistance;
				closest = food;
			}
		}
		
		if( normalizedDistance == 0 ){
			action = CritterAction.EAT;
			target = closest;

		}else{
			action = CritterAction.MOVE;
			//	Default to looking for open spaces
			List<Coord> validMoves = getValidMoves(visionMap, new int[]{0});
			target = CoordHelper.getClosestCoord(closest, validMoves);
		}

		//	Compute delta between critterLocation and target. Store in target.
		target.setX( target.getX() - critterLocation.getX() );
		target.setY( target.getY() - critterLocation.getY() );
		
		//	Apply the above delta to the world location for true target coordinates
		target.setX( target.getX() + worldLocation.getX() );
		target.setY( target.getY() + worldLocation.getY() );

		return new ActionTarget( action, target);
	}

	public void setCritter(SimpleCritter critter) {
		this.critter = critter;
	}

}
