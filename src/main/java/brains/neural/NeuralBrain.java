package brains.neural;

/**
 * Created by JKyte on 5/13/2016.
 */
public class NeuralBrain {

    Neuron[] inputLayer;
    Neuron[] hiddenLayer;
    Neuron[] outputLayer;

    public void setInputs(double[][] surroundings){

        int inputNeuronCntr = 0;

        for(int ii = 0; ii < surroundings.length; ii++){
            for(int jj = 0; jj < surroundings[0].length; jj++){

                //  Make sure we don't load the critter into the inputs
                //if( ii != 1 && jj != 1 ){
                    System.out.println("here");
                    inputLayer[inputNeuronCntr].setWeight(surroundings[ii][jj]);
                    inputNeuronCntr++;
                //}
            }
        }
    }

    //  Fire all layers of the brain
    public void fire(){
        for( int ii = 0; ii < inputLayer.length; ii++ ){
            inputLayer[ii].fire();
        }

        for( int ii = 0; ii < hiddenLayer.length; ii++ ){
            hiddenLayer[ii].fire();
        }

        for( int ii = 0; ii < outputLayer.length; ii++ ){
            outputLayer[ii].fire();
        }
    }


    public void printBrain(){

        System.out.println("Input Layer: " + inputLayer.length + " inputs.");
        for( int ii = 0; ii < inputLayer.length; ii++ ){
//            System.out.println(inputLayer[ii].getWeight());
            System.out.println(inputLayer[ii].toString());
        }

        System.out.println("Hidden Layer: " + hiddenLayer.length + " inputs.");
        for( int ii = 0; ii < hiddenLayer.length; ii++ ){
//            System.out.println(hiddenLayer[ii].getWeight());
            System.out.println(hiddenLayer[ii].toString());
        }

        System.out.println("Output Layer: " + outputLayer.length + " inputs.");
        for( int ii = 0; ii < outputLayer.length; ii++ ){
//            System.out.println(outputLayer[ii].getWeight());
            System.out.println(outputLayer[ii].toString());
        }
    }
}
