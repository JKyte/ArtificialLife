package world;

import utils.Coord;
import utils.VisionCritCoord;

/**
 * @author JKyte
 * 
 * The map class encompasses a 2-dimensional int array and a GUI. It should be noted that in Java the 2D array 
 * can seem backwards. 
 * 
 * [x][y] coordinate pairs are actually [y][x] for a 2d int array
 * 
 * See below for more information
 * http://stackoverflow.com/questions/2203525/are-the-x-y-and-row-col-attributes-of-a-two-dimensional-array-backwards
 * 
 * Given this information and how Java stores a 2D array as [y][x] vs the [x][y] real-world representation, we shall
 * use the following terms
 *  - Logical = [x][y]
 *  - Actual = [y][x]
 */
public class Map {

	private int grid[][];

	public int mapWidth;
	public int mapHeight;

	/**
	 * Single arg constructor, generates a square world
	 * @param len
	 */
	public Map(int len){
		mapWidth = len;
		mapHeight = len;
		grid = new int[mapHeight][mapWidth];
	}

	/**
	 * Two arg constructor, for generating rectangles
	 * @param width
	 * @param height
	 */
	public Map(int width, int height){
		mapWidth = width;
		mapHeight = height;
		grid = new int[mapHeight][mapWidth];
	}

	/**
	 * 
	 * @param objectID - ID of object to be placed
	 * @param target - X,Y coordinates for target location
	 * @param print - T/F variable for printing to console
	 * @return success, currently defaults to TRUE as this method assumes a clear landing site
	 */
	public boolean placeObjectAtCoord(int objectID, Coord target, boolean print){
		if( print ){
			System.out.println( "Replacing object '" + grid[target.getY()][target.getX()] + 
					"' at " + target.toString() + "...");
		}

		grid[target.getY()][target.getX()] = objectID;
		return true;	//	by default
	}

	/**
	 * This class gets an object at the target coord
	 * @param target -- A coordinate for an object on the map
	 * @returns an int representing the object at the target coord
	 */
	public int getObjectAtCoord( Coord target ){
		return grid[target.getY()][target.getX()];
	}

	/**
	 * @param objectID - ID of object to be placed
	 * @param target - X,Y coordinates for target location
	 * @return success, currently defaults to TRUE as this method assumes a clear landing site
	 */
	public boolean placeObjectAtCoord(int objectID, Coord target){
		grid[target.getY()][target.getX()] = objectID;
		return true;	//	by default
	}

	/**
	 * Lightweight version of placeObjectAtCoord, such that
	 * 
	 * <br> [X][Y] = object
	 * @param x
	 * @param y
	 * @param object
	 */
	public boolean setGrid( int x, int y, int object ){
		//	System.out.println("Setting grid at ["+x+"]["+y+"] to be " + object);
		//	Ensure we aren't going out of bounds
		if( x < 0 || x > grid[0].length ||
				y < 0 || y > grid.length ){
			return false;
		}
		grid[y][x] = object;
		return true;
	}

	public int getGrid( int x, int y ){
		//	System.out.println("Get grid at ["+x+"]["+y+"]");
		//	Ensure we aren't going out of bounds
		if( x < 0 || x >= grid[0].length ||
				y < 0 || y >= grid.length ){
			return -1;
		}

		return grid[y][x];
	}

	/**
	 * This method assumes valid start/destination Coordinate pairs in an effort to reduce
	 * world-side processing.
	 * @param startX
	 * @param startY
	 * @param targetX
	 * @param targetY
	 */
	public Coord moveObject( Coord start, Coord dest ){
		System.out.println("Move from " + start.toString() + " to " + dest.toString());
		grid[dest.getY()][dest.getX()] = grid[start.getY()][start.getX()];
		removeObject(start);
		return dest;
	}

	/**
	 * @param target - X,Y Coord set to zero
	 */
	public void removeObject( Coord target ){
		System.out.println("removing from " + target.toString());
		grid[target.getY()][target.getX()] = 0;
	}

	/**
	 * Convenience wrapper method to avoid passing a 'verbose' flag every time
	 * @param visionLen
	 * @param critterWorldLocation
	 * @return
	 */
	public VisionCritCoord getVisionMap( int visionLen, Coord critterWorldLocation){
		return getVisionMap(visionLen, critterWorldLocation, false);
	}

	/**
	 * Perhaps the single most important method in this entire project
	 * 
	 * This method takes a world map coordinate and returns a VisionCritCoord object containing 
	 * <br>- a NxN Map object representing the vision of a critter, where N is the critter's vision length
	 * <br>- a Coord with the location of the critter on the visionMap
	 * <br>- a Coord with the location of the critter on the world map
	 * 
	 * <br>
	 * <br> This method is able to handle cases where the visionMap is not a perfect NxN, i.e., cases 
	 * where the vision length extends beyond the bounds of the 2 dimensional worldMap, possibly resulting 
	 * in a rectangle
	 * 
	 * @param visionLen -- the critter's vision length
	 * @param critterWorldLocation -- a Coord containing the X,Y world location of the critter
	 * @param verbose -- a flag used for debugging purposes
	 * @returns a VisionCritCoord
	 */
	public VisionCritCoord getVisionMap( int visionLen, Coord critterWorldLocation, boolean verbose ){

		//	Initialize cardinal directions
		boolean north = false, 
				south = false, 
				east = false, 
				west = false;

		//	Initialize default values for the X and Y width of the visionMap
		int xWidth = 1+visionLen*2;
		int yWidth = 1+visionLen*2;

		//	Initialize default values for the X and Y coordinates of the critter on the visionMap
		int critX = visionLen;
		int critY = visionLen;

		//	Initialize default values for the X and Y starting coordinates for the upper-left corner of the vision map
		int xMin = critterWorldLocation.getX()-visionLen;
		int yMin = critterWorldLocation.getY()-visionLen;

		//	Initialize default values for the X and Y starting coordinates for the lower-right corner of the vision map
		int xMax = critterWorldLocation.getX()+visionLen;
		int yMax = critterWorldLocation.getY()+visionLen;

		//	Initialize default values for the X and Y deltas that could be introduced by an out of bounds vision map
		int deltaX = 0;
		int deltaY = 0;

		/**
		 * Determine if any deltas exist for X and Y
		 */
		if( xMin < 0 ){
			if(verbose) System.out.println("X < 0");
			west = true;
			deltaX = 0-xMin;


		} else if ( xMax == grid.length ){
			if(verbose) System.out.println("X == map.len");
			east = true;
			deltaX = xMax - grid.length;
			xWidth--;

		}else if ( xMax > grid.length ){
			// Not exercised by unit tests. Will need a vision length of 2+
			if(verbose) System.out.println("X > map.len");
			east = true;
			deltaX = xMax - grid.length;

		}

		if ( yMin < 0 ){
			if(verbose) System.out.println("Y < 0");
			south = true;
			deltaY = 0-yMin;


		} else if ( yMax == grid[0].length ){
			if(verbose) System.out.println("Y == map.len");
			north = true;
			deltaY = yMax - grid[0].length;
			yWidth--;

		}else if ( yMax > grid[0].length ){
			// Not exercised by unit tests. Will need a vision length of 2+
			if(verbose) System.out.println("Y > map.len");
			north = true;
			deltaY = yMax - grid[0].length;

		}


		//	Some stats for us
		if(verbose)	System.out.println("xMin: " + xMin + " yMin: " + yMin);
		if(verbose)	System.out.println("xMax: " + xMax + " yMax: " + yMax);
		if(verbose)	System.out.println("deltaX: " + deltaX + " deltaY: " + deltaY);		
		if(verbose) System.out.println("N:"+north+" S:"+south+" W:"+west+" E:"+east);


		/**
		 * Apply deltas to X and Y
		 */
		if( south ){
			yMin += deltaY;
			yWidth -= deltaY;
			critY -= deltaY;
		}

		if( west ){
			xMin += deltaX;
			xWidth -= deltaX;
			critX -= deltaX;
		}

		/**
		 * Copy the world map into a visionMap
		 */
		int mapX = xMin;	//	Minimum is the starting position
		int mapY = yMin;
		
		/**
		 * Previously was	*/
		Map visionMap = new Map(xWidth, yWidth);

		for( int ii = 0; ii < xWidth; ii++ ){
			for( int jj = 0; jj < yWidth; jj++ ){
				visionMap.setGrid(ii, jj, grid[mapY++][mapX]);
			}
			mapY = yMin;	//	Reset yMin for next row
			mapX++;		//	Increment row
		}

		if(verbose)	System.out.println("Coord: " + critterWorldLocation.toString() );
		if(verbose)	System.out.println("xWidth: " + xWidth + " yWidth: " + yWidth);
		if(verbose) System.out.println("visionX: " + critX + " visionY: " + critY);

		return new VisionCritCoord(visionMap, new Coord(critX, critY), critterWorldLocation);
	}

	/**
	 * <br> This method prints the two-dimensional grid array as is
	 * <br> for( int i...)
	 * <br>    for( int j...)
	 * <br>        int[i][j]
	 */
	public void printActualMap( ){
		System.out.println("Printing actual map");
		int xLen = grid.length;
		int yLen = grid[0].length;
		for( int jj = 0; jj < yLen; jj++ ){
			for( int ii = 0; ii < xLen; ii++ ){
				System.out.print( grid[ii][jj] + " " );
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * <br> Correct way to print our 2D array LOGICALLY such that [x][y]
	 * <br> 1. for( ii = X.len-1; ii >= 0; ii-- )
	 * <br> 2. for( jj = 0; jj < Y.len; jj++ )
	 * <br> 3. print [ii][jj]
	 */
	public void printLogicalMap(){
		System.out.println( "Printing logical map" );
		System.out.println( getLogicalMap().toString() );
	}

	/**
	 * @return - a string representation of the map
	 */
	public String getMapForGUI(){
		return getLogicalMap().toString();
	}

	/**
	 * Overloaded method to append stats underneath the map
	 * @param stats -- a pre-formatted string of interesting stats.
	 * @return
	 */
	public String getMapForGUI(String stats){
		int numFood = 0;
		int numCritters = 0;
		int numCorpses = 0;

		StringBuilder sb = new StringBuilder();
		int xLen = grid.length-1;	//	Compute once
		int yLen = grid[0].length;	//	Compute once

		for( int ii = xLen; ii >= 0 ; ii-- ){
			for( int jj = 0; jj < yLen; jj++ ){
				sb.append( grid[ii][jj] + "  " );

				if( grid[ii][jj] == 1 ){
					numFood++;
				}else if( grid[ii][jj] == 2 ){
					numCritters++;
				}else if( grid[ii][jj] == 3 ){
					numCorpses++;
				}
			}
			sb.append("\n");
		}

		sb.append("\nNumFood: " + numFood);
		sb.append("\nNumCritters: " + numCritters);
		sb.append("\nNumCorpses: " + numCorpses + "\n");

		sb.append(stats);	//	Append any stats passed in.
		return sb.toString();
	}

	private StringBuilder getLogicalMap(){
		StringBuilder sb = new StringBuilder();
		int xLen = grid.length-1;	//	Compute once
		int yLen = grid[0].length;	//	Compute once
		for( int ii = xLen; ii >= 0 ; ii-- ){
			for( int jj = 0; jj < yLen; jj++ ){
				sb.append( grid[ii][jj] + "  " );
			}
			sb.append("\n");
		}
		return sb;
	}
	
	public int getCountForObjectType(int objectType){
		int objectCount = 0;
		int xLen = grid.length-1;	//	Compute once
		int yLen = grid[0].length;	//	Compute once

		for( int ii = xLen; ii >= 0 ; ii-- ){
			for( int jj = 0; jj < yLen; jj++ ){
				if( grid[ii][jj] == objectType ){
					objectCount++;
				}
			}
		}
		
		return objectCount;
	}

	/**
	 * Convenience method that calculates the area for the map
	 * @returns Area = Width * Height
	 */
	public int mapArea(){
		return mapWidth*mapHeight;
	}

}
