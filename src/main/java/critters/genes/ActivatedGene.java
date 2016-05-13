package critters.genes;

/**
 * Created by JKyte on 5/10/2016.
 */
public class ActivatedGene extends BaseGene {

    private boolean activated;

    @Override
    public String sequenceGene() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.sequenceGene()).append(".");
        sb.append(isActivated());
        return sb.toString();   //  TODO this should work
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
