package critters.genes;

/**
 * Created by JKyte on 5/10/2016.
 */
public class Gene implements Sequenceable, GeneType{

    private String name;
    private int geneStartValue;
    private int geneCurrentValue;
    private int geneMinValue;
    private int geneMaxValue;
    private int geneMutateStepSize;

    public Gene(){

    }

    /**
     * Fully copy another gene's values
     * @param other
     */
    public Gene(Gene other){
        this.setName(other.getName());
        this.setGeneStartValue(other.getGeneStartValue());
        this.setGeneCurrentValue(other.getGeneCurrentValue());
        this.setGeneMinValue(other.getGeneMinValue());
        this.setGeneMaxValue(other.getGeneMaxValue());
        this.setGeneMutateStepSize(other.getGeneMutateStepSize());
    }

    /**
     * Sequence all values except the current value
     * @return
     */
    public String sequence() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(".");
        sb.append(getGeneStartValue()).append(".");
        sb.append(getGeneMinValue()).append(".");
        sb.append(getGeneMaxValue()).append(".");
        sb.append(getGeneMutateStepSize());
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeneStartValue() {
        return geneStartValue;
    }

    public void setGeneStartValue(int geneStartValue) {
        this.geneStartValue = geneStartValue;
    }

    public int getGeneCurrentValue() {
        return geneCurrentValue;
    }

    public void setGeneCurrentValue(int geneCurrentValue) {
        this.geneCurrentValue = geneCurrentValue;
    }

    public int getGeneMinValue() {
        return geneMinValue;
    }

    public void setGeneMinValue(int geneMinValue) {
        this.geneMinValue = geneMinValue;
    }

    public int getGeneMaxValue() {
        return geneMaxValue;
    }

    public void setGeneMaxValue(int geneMaxValue) {
        this.geneMaxValue = geneMaxValue;
    }

    public int getGeneMutateStepSize() {
        return geneMutateStepSize;
    }

    public void setGeneMutateStepSize(int geneMutateStepSize) {
        this.geneMutateStepSize = geneMutateStepSize;
    }

}
