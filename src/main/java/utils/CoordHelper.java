package utils;

import java.util.List;

public class CoordHelper {

	//	For use in an ELSE MOVE case.
	public static Coord getClosestCoord( Coord center, List<Coord> destinations ){
		int closestNormalized = Integer.MAX_VALUE;
		Coord closest = null;
		for( Coord tmpDest : destinations ){
			int tmpDistance = getNormalizedDistance( center, tmpDest );

			if( tmpDistance < closestNormalized ){
				closestNormalized = tmpDistance;
				closest = tmpDest;
			}
		}

		return closest;
	}
	
	public static int getNormalizedDistance( Coord start, Coord end){
		int deltaX = start.getX() - end.getX();
		int deltaY = start.getY() - end.getY();
		if( deltaX < 0 ) deltaX *= -1;
		if( deltaY < 0 ) deltaY *= -1;
		if( deltaX > 0 ) deltaX--;
		if( deltaY > 0 ) deltaY--;
		return deltaX+deltaY;	//	reduce actual distance by one so adjacent food source comes out as zero
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
	public static int getNormalizedDistance( int deltaX, int deltaY ){
		if( deltaX < 0 ) deltaX *= -1;
		if( deltaY < 0 ) deltaY *= -1;
		if( deltaX > 0 ) deltaX--;
		if( deltaY > 0 ) deltaY--;
		return deltaX+deltaY;	//	reduce actual distance by one so adjacent food source comes out as zero
	}
}
