package critters;

public class SimpleCritter extends BaseCritter {
	
	private int curHealth;		//	current hit points
	private int maxHealth;		//	max hit points
	private int curEnergy;		//	current critter energy
	private int maxEnergy;		//	max critter energy
	private int[] food;			//	int[] array of allowed food types
	
	private boolean alive = false;	//	Flag for quick dead checking
	
	public SimpleCritter(){
		super();
		this.alive = true;
	}
	
	public SimpleCritter(String parentUUID){
		super(parentUUID);
		this.alive = true;
	}

	public int getCurHealth() {
		return curHealth;
	}

	public void setCurHealth(int curHealth) {
		this.curHealth = curHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurEnergy() {
		return curEnergy;
	}

	public void setCurEnergy(int curEnergy) {
		this.curEnergy = curEnergy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public int[] getFood() {
		return food;
	}

	public void setFood(int[] food) {
		this.food = food;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
