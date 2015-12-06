package world;

import java.util.Random;

import utils.Coord;

/**
 * @author JKyte
 * 
 * This class inherits the base map class but adds some more functionality and handles a GUI
 */
public class WorldMap extends Map{
	
	private  GUI gui;
	
	public WorldMap(int mapWidth, int mapHeight){
		super(mapWidth, mapHeight);
	}

	public WorldMap(int len) {
		super(len);
	}

	/**
	 * WARNING -- if the map is full, this method WILL put the program into an infinite loop
	 * 
	 * @param objectID - object to be placed
	 * @param print - T/F indicating whether information is logged to console
	 * @return
	 */
	public Coord placeObjectRandomly(int objectID, boolean print ){
		Random rand = new Random();
		Coord coord = null;

		boolean objectPlaced = false;
		while( !objectPlaced ){
			int randX = rand.nextInt(this.mapWidth);
			int randY = rand.nextInt(this.mapHeight);

			//	check to see if the space is clear for landing...
			if( getGrid(randX, randY) == 0 ){
				//	if clear, then place
				if( print ) System.out.println( "Placing object " + objectID + " at " + randX + " " + randY + " on " + getGrid(randX,randY));

				setGrid(randX, randY, objectID);
				coord = new Coord(randX, randY);
				objectPlaced = true;
			}	//	implicit else keeps the objectPlaced var set to false, we keep looping
		}
		return coord;
	}

	public void createAndDisplayDynamicGUI(){
		gui = new GUI(mapWidth, mapHeight);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void createAndDisplayFixedGUI(){
		gui = new GUI();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void updateGUI(){
		gui.clearAndSetText(getMapForGUI());
	}

	public void updateGUI( String stats ){
		System.out.println("Update GUI.");
		gui.clearAndSetText(getMapForGUI(stats));
	}
}
