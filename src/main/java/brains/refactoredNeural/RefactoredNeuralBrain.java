package brains.refactoredNeural;

/**
 * Created by JKyte on 5/15/2016.
 */
public class RefactoredNeuralBrain {

    public RefactoredNeuron[] inputLayer;
    public RefactoredNeuron[] hiddenLayer;
    public RefactoredNeuron[] outputLayer;

    public RefactoredNeuralBrain( int numInputs, int numHidden, int numOutput){
        inputLayer = new RefactoredNeuron[numInputs];
        hiddenLayer = new RefactoredNeuron[numHidden];
        outputLayer = new RefactoredNeuron[numOutput];
    }

    public void setInputs(double[] inputs){
        for(int ii = 0; ii < inputs.length; ii++){
            inputLayer[ii].setWeight(inputs[ii]);
        }
    }

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
