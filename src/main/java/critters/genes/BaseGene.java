package critters.genes;

/**
 * Created by JKyte on 5/10/2016.
 */
public class BaseGene implements GeneTemplate{

    private String geneName;
    private String shortName;
    private String geneDescription;
    private int geneStartValue;
    private int geneCurrentValue;
    private int geneMinValue;
    private int geneMaxValue;
    private int geneMutateStepSize;

    public BaseGene(){

    }

    public BaseGene( BaseGene other ){
        this.setGeneName(other.getGeneName());
        this.setShortName(other.getShortName());
        this.setGeneDescription(other.getGeneDescription());
        this.setGeneStartValue(other.getGeneStartValue());
        this.setGeneCurrentValue(other.getGeneCurrentValue());
        this.setGeneMinValue(other.getGeneMinValue());
        this.setGeneMaxValue(other.getGeneMaxValue());
        this.setGeneMutateStepSize(other.getGeneMutateStepSize());
    }

    public String sequenceGene() {
        StringBuilder sb = new StringBuilder();
        sb.append(getShortName()).append(".");
        sb.append(getGeneStartValue()).append(".");
        sb.append(getGeneMinValue()).append(".");
        sb.append(getGeneMaxValue()).append(".");
        sb.append(getGeneMutateStepSize());
        return sb.toString();
    }

    /**
     * This mutates either the upper or the lower value
     *
     * @return
     */
    public boolean mutate() {

        return false;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getGeneDescription() {
        return geneDescription;
    }

    public void setGeneDescription(String geneDescription) {
        this.geneDescription = geneDescription;
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
