package world;

/**
 * Simple class to keep track of X and Y coordinate pairs
 */
public class Coord {
	
	private int x;	//	X coord
	private int y;	//	Y coord
	
	public Coord( int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	public Coord( Coord coord ){
		this.setX(coord.getX());
		this.setY(coord.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString(){
		return x + " " + y;
	}

}
