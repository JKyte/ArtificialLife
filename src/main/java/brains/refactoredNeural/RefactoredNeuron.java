package brains.refactoredNeural;

import java.util.ArrayList;

/**
 * Created by JKyte on 5/15/2016.
 */
public class RefactoredNeuron {

    private String neuronID;
    private ArrayList<RefactoredNeuron> inputs;
    private double weight;
    private double threshold;
    private boolean fired;

    public RefactoredNeuron( double threshold ){
        setThreshold(threshold);
        inputs = new ArrayList<RefactoredNeuron>();
    }

    public void connect( RefactoredNeuron ... neurons ){
        for( RefactoredNeuron neuron : neurons ){
            inputs.add(neuron);
        }
    }

    public double fire(){
        if( inputs.size() > 0 ){
            for( RefactoredNeuron neuron : inputs ){
                weight += (neuron.isFired()) ? neuron.getWeight() : 0.0;
            }
            weight = Math.tanh(weight);
            fired = weight > getThreshold();
            return weight;
        }else if( weight != 0.0 ){
            fired = weight > getThreshold();
            return weight;
        }else{
            return 0.0;
        }
    }

    @Override
    public String toString(){
        return getNeuronID() + " Weight: " + getWeight() + " Thresh: " + getThreshold() +
                " Fired: " + isFired();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public String getNeuronID() {
        return neuronID;
    }

    public void setNeuronID(String neuronID) {
        this.neuronID = neuronID;
    }
}
