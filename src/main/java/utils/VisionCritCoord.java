package utils;

import world.BaseMap;

public class VisionCritCoord {
	private BaseMap visionMap;
	private Coord critterVisionLocation;
	private Coord critterWorldLocation;
	
	/**
	 * 
	 * @param visionMap -- a BaseMap object representing the critters view of the WorldMap
	 * @param critterVisionLocation -- the visionMap coordinates of the current critters location
	 * @param critterWorldLocation -- the world map coordinates of the current critters location
	 */
	public VisionCritCoord(BaseMap visionMap, Coord critterVisionLocation,
											Coord critterWorldLocation){
		this.setVisionMap(visionMap);
		this.setCritterVisionLocation(critterVisionLocation);
		this.setCritterWorldLocation(critterWorldLocation);
	}

	public BaseMap getVisionMap() {
		return visionMap;
	}

	public void setVisionMap(BaseMap visionMap) {
		this.visionMap = visionMap;
	}

	public Coord getCritterVisionLocation() {
		return critterVisionLocation;
	}

	public void setCritterVisionLocation(Coord critterVisionLocation) {
		this.critterVisionLocation = critterVisionLocation;
	}

	public Coord getCritterWorldLocation() {
		return critterWorldLocation;
	}

	public void setCritterWorldLocation(Coord critterWorldLocation) {
		this.critterWorldLocation = critterWorldLocation;
	}
}
