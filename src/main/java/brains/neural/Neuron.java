package brains.neural;

import java.util.ArrayList;

/**
 * Created by JKyte on 5/13/2016. Shamelessly pulled from http://stackoverflow.com/questions/4719633/java-simple-neural-network-setup.
 *
 * Original class was simplistic and pulled data forward, recalculating operations as it went. A better option is to
 * do a single calculation per Neuron and just tally the results.
 */
public class Neuron {

    private String nid;

    private ArrayList<Neuron> inputs;
    private double weight;
    private double threshhold;
    private boolean fired;

    public Neuron (double t) {
        setThreshhold(t);
        fired = false;
        inputs = new ArrayList();
    }

    public void connect (Neuron ... ns) {
        for (Neuron n : ns) inputs.add(n);
    }

    public void setWeight (double newWeight) {
        weight = newWeight;
    }

    public void setWeight (boolean newWeight) {
        weight = newWeight ? 1.0 : 0.0;
    }

    public double getWeight () {
        return weight;
    }

    public double fire () {
        if (inputs.size() > 0) {
            double totalWeight = 0.0;
            for (Neuron n : inputs) {
                n.fire();
                totalWeight += (n.isFired()) ? n.getWeight() : 0.0;
            }
            fired = totalWeight > getThreshhold();
            //return Math.tanh(totalWeight);
            return totalWeight;
        }
        else if (weight != 0.0) {
            fired = weight > getThreshhold();
            return weight;
        }
        else {
            return 0.0f;
        }
    }

    public boolean isFired () {
        return fired;
    }

    public double getThreshhold() {
        return threshhold;
    }

    public void setThreshhold(double threshhold) {
        this.threshhold = threshhold;
    }

    @Override
    public String toString(){
        return getNid() + " InputWeight: " + weight + " Thresh: " +  threshhold + " Fired: " + fired;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}
