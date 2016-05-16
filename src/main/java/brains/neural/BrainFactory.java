package brains.neural;

import brains.refactoredNeural.RefactoredNeuralBrain;
import brains.refactoredNeural.RefactoredNeuron;

/**
 * Created by JKyte on 5/13/2016.
 */
public class BrainFactory {

    private static final float DEFAULT_WEIGHT = 0.5f;

    public static NeuralBrain createSimpleBrain(){
        NeuralBrain brain = new NeuralBrain();

        //  Set up Input Layer of FOUR Neurons
        brain.inputLayer = new Neuron[4];
        for( int ii = 0; ii < brain.inputLayer.length; ii++ ){
            brain.inputLayer[ii] = new Neuron(DEFAULT_WEIGHT);
            brain.inputLayer[ii].setNid("i"+ii);
        }

        //  Set up Hidden Layer of TWO Neurons
        brain.hiddenLayer = new Neuron[2];
        for( int ii = 0; ii < brain.hiddenLayer.length; ii++ ){
            brain.hiddenLayer[ii] = new Neuron(DEFAULT_WEIGHT);
            brain.hiddenLayer[ii].setNid("h"+ii);

        }

        //  Set up Output Layer of TWO Neurons
        brain.outputLayer = new Neuron[2];
        for( int ii = 0; ii < brain.outputLayer.length; ii++ ){
            brain.outputLayer[ii] = new Neuron(DEFAULT_WEIGHT);
            brain.outputLayer[ii].setNid("o"+ii);

        }

        //  Connect inputs to hidden layer
        for( int ii = 0; ii < brain.hiddenLayer.length; ii++ ){
            brain.hiddenLayer[ii].connect(brain.inputLayer);
        }

        //  Connect hidden layer to outputs
        for( int ii = 0; ii < brain.outputLayer.length; ii++ ){
            brain.outputLayer[ii].connect(brain.hiddenLayer);
        }

        return brain;
    }


    /**
     *  Creates a brain with two inputs, two hidden layer neurons, and two outputs
     *
     * @return
     */
    public static RefactoredNeuralBrain createSimpleRefactoredBrain(){
        RefactoredNeuralBrain brain = new RefactoredNeuralBrain(2,2,2);

        for( int ii = 0; ii < brain.inputLayer.length; ii++ ){
            brain.inputLayer[ii] = new RefactoredNeuron(DEFAULT_WEIGHT);
            brain.inputLayer[ii].setNeuronID("i" + ii);
        }

        for( int ii = 0; ii < brain.hiddenLayer.length; ii++ ){
            brain.hiddenLayer[ii] = new RefactoredNeuron(DEFAULT_WEIGHT);
            brain.hiddenLayer[ii].setNeuronID("h"+ii);
        }

        for( int ii = 0; ii < brain.outputLayer.length; ii++ ){
            brain.outputLayer[ii] = new RefactoredNeuron(DEFAULT_WEIGHT);
            brain.outputLayer[ii].setNeuronID("o"+ii);
        }

        //  Connect inputs to hidden layer
        for( int ii = 0; ii < brain.hiddenLayer.length; ii++ ){
            brain.hiddenLayer[ii].connect(brain.inputLayer);
        }

        //  Connect hidden layer to outputs
        for( int ii = 0; ii < brain.outputLayer.length; ii++ ){
            brain.outputLayer[ii].connect(brain.hiddenLayer);
        }

        return brain;
    }
}
