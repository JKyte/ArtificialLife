package brains;

import critters.SimpleCritter;

public class SimpleBrain extends Brain{

	private SimpleCritter critter;

	public SimpleBrain(){

	}

    @Override
    public void setCritter(Object critter) {
        this.critter = (SimpleCritter) critter;
        this.validFoodOptions = ((SimpleCritter) critter).getFood();
    }

    public void clearCritter(){
        this.critter = null;
        this.validFoodOptions = null;
    }

}
