package critters.genes;

/**
 * Created by JKyte on 5/10/2016. A gene is broken out into two major groups of variables. The
 * first group handles everything for mutation and is what is sequenced. The second group deals
 * largely with simulation.
 *
 * Gene sequenced as
 * [name][mutateMin][mutateMax][mutateStepSize][mutateStartValue][variableDelta]
 *
 * The startValue and currentValue are set to mutateStartValue
 */
public class Gene implements Sequenceable, GeneType{

    private String name;

    private int mutateMin;      //  This collection of variables to be sequenced
    private int mutateMax;
    private int mutateStepSize;
    private int mutateStartValue;

    /*  The variableDelta allows a cap within a scenario on the currentValue. Also provides variation
        for gene creation with random values within a window (startValue +/- variableDelta)     */
    private int variableDelta;
    private int startValue;
    private int currentValue;

    public Gene(){
    }

    /**
     * Fully copy another gene's values (deep copy)
     * @param other
     */
    public static Gene deepCopyGene(Gene other){
        Gene gene = new Gene();
        gene.setName(other.getName());
        gene.setMutateMin(other.getMutateMin());
        gene.setMutateMax(other.getMutateMax());
        gene.setMutateStepSize(other.getMutateStepSize());
        gene.setMutateStartValue(other.getMutateStartValue());
        gene.setVariableDelta(other.getVariableDelta());
        gene.setStartValue(other.getStartValue());
        gene.setCurrentValue(other.getCurrentValue());
        return gene;
    }

    /**
     * Sequence all mutate values and the variableDelta.
     *
     * @returns a string in the form [name][mutateMin][mutateMax][mutateStepSize][mutateStartValue][variableDelta]
     */
    public String sequence() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(".");
        sb.append(getMutateMin()).append(".");
        sb.append(getMutateMax()).append(".");
        sb.append(getMutateStepSize()).append(".");
        sb.append(getMutateStartValue()).append(".");
        sb.append(getVariableDelta());
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMutateMin() {
        return mutateMin;
    }

    public void setMutateMin(int mutateMin) {
        this.mutateMin = mutateMin;
    }

    public int getMutateMax() {
        return mutateMax;
    }

    public void setMutateMax(int mutateMax) {
        this.mutateMax = mutateMax;
    }

    public int getMutateStepSize() {
        return mutateStepSize;
    }

    public void setMutateStepSize(int mutateStepSize) {
        this.mutateStepSize = mutateStepSize;
    }

    public int getMutateStartValue() {
        return mutateStartValue;
    }

    public void setMutateStartValue(int mutateStartValue) {
        if( mutateStartValue >= mutateMin && mutateStartValue <= mutateMax){
            this.mutateStartValue = mutateStartValue;
        }
    }

    public int getVariableDelta() {
        return variableDelta;
    }

    public void setVariableDelta(int variableDelta) {
        if( variableDelta >= mutateMin && variableDelta <= mutateMax){
            this.variableDelta = variableDelta;
        }
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        if( startValue >= mutateMin &&  startValue <= mutateMax ){
            this.startValue = startValue;
        }
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        if( currentValue >= mutateMin && currentValue <= mutateMax){
            this.currentValue = currentValue;
        }
    }

    public void decrementCurrentValue(int decrementAmount){
        this.currentValue = currentValue - decrementAmount;
        if( this.currentValue < 0 ){
            this.currentValue = 0;
        }
    }
}
