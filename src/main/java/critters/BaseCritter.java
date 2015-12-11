package critters;

import utils.Coord;

public class BaseCritter {
	
	private Coord worldLocation;	//	X,Y coords of the little critter on the world map
	
	private int speed;		//	how many tiles this little guy can move per simulated turn
	private int vision;		//	how many tiles this little guy can see
	
	private int generationID;	//	what generation this little critter was created
	private int critterID;		//	which critter within the generation
	private String parentUUID;	//	UUID of parent

	private int currentLifeLen;		//	how many simulated turns this little guy has survived

	public BaseCritter(){
		this.currentLifeLen = 0;
		this.parentUUID = "-1";
	}
	
	public BaseCritter(String parentUUID){
		this.currentLifeLen = 0;
		this.parentUUID = parentUUID;
	}
	
	public int calculateFitness(){
		return currentLifeLen;
	}

	public Coord getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(Coord worldLocation) {
		this.worldLocation = worldLocation;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getVision() {
		return vision;
	}

	public void setVision(int vision) {
		this.vision = vision;
	}

	public int getGenerationID() {
		return generationID;
	}

	public void setGenerationID(int generationID) {
		this.generationID = generationID;
	}

	public int getCritterID() {
		return critterID;
	}

	public void setCritterID(int critterID) {
		this.critterID = critterID;
	}

	/**
	 * Typically the parentUUID is generationID + critterID
	 * @return
	 */
	public String getParentUUID() {
		return parentUUID;
	}

	public void setParentUUID(String parentUUID) {
		this.parentUUID = parentUUID;
	}

	public int getCurrentLifeLen() {
		return currentLifeLen;
	}

	public void setCurrentLifeLen(int currentLifeLen) {
		this.currentLifeLen = currentLifeLen;
	}

}
