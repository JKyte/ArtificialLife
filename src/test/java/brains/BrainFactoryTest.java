package brains;

import brains.neural.BrainFactory;
import brains.neural.NeuralBrain;
import brains.refactoredNeural.RefactoredNeuralBrain;
import org.junit.Test;

/**
 * Created by JKyte on 5/13/2016.
 */
public class BrainFactoryTest {

    //@Test
    public void testFactoryAndPrint(){
        NeuralBrain brain = BrainFactory.createSimpleBrain();

        double[][] inputs = new double[2][2];
        inputs[0][0] = 1.0;
        inputs[0][1] = 0.0;
        inputs[1][0] = 1.0;
        inputs[1][1] = 3.0;

        brain.setInputs(inputs);
        brain.fire();

        brain.printBrain();
    }

    @Test
    public void testRefactoredBrainAndPrint(){
        RefactoredNeuralBrain brain = BrainFactory.createSimpleRefactoredBrain();
        double[] inputs = new double[2];
        inputs[0] = 3.0;
        inputs[1] = 0.0;

        brain.setInputs(inputs);
        brain.fire();
        brain.printBrain();
    }
}
