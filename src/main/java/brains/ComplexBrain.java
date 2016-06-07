package brains;

import critters.geneticcritter.GeneticCritter;

/**
 * Created by JKyte on 6/5/2016.
 */
public class ComplexBrain extends Brain{

    private GeneticCritter critter;

    public ComplexBrain(){

    }


    @Override
    public void setCritter(Object critter) {
        this.critter = (GeneticCritter) critter;
        this.validFoodOptions = new int[]{1};   //  TODO -- default for now
    }

    @Override
    public void clearCritter() {
        this.critter = null;
        this.validFoodOptions = null;
    }
}
