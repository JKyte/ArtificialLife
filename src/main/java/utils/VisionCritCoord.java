package utils;

import world.Map;

public class VisionCritCoord {
	private Map visionMap;
	private Coord critterVisionLocation;
	private Coord critterWorldLocation;
	
	/**
	 * 
	 * @param visionMap -- a Map object representing the critters view of the WorldMap
	 * @param critterVisionLocation -- the visionMap coordinates of the current critters location
	 * @param critterWorldLocation -- the world map coordinates of the current critters location
	 */
	public VisionCritCoord(Map visionMap, Coord critterVisionLocation,
											Coord critterWorldLocation){
		this.setVisionMap(visionMap);
		this.setCritterVisionLocation(critterVisionLocation);
		this.setCritterWorldLocation(critterWorldLocation);
	}

	public Map getVisionMap() {
		return visionMap;
	}

	public void setVisionMap(Map visionMap) {
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
