package brains;

import brains.neural.Neuron;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by JKyte on 5/13/2016. Shamelessly pulled from http://stackoverflow.com/questions/4719633/java-simple-neural-network-setup
 */
public class NeuronTest {

    @Test
    public void testSmallNeuralNetXOR(){
        Neuron xor = new Neuron(0.5f);
        Neuron left = new Neuron(1.5f);
        Neuron right = new Neuron(0.5f);
        left.setWeight(-1.0f);
        right.setWeight(1.0f);
        xor.connect(left, right);

        Boolean[] bools = new Boolean[]{ true, false};

        for (Boolean bool : bools) {
            Neuron op = new Neuron(0.0f);
            op.setWeight(bool);
            left.connect(op);
            right.connect(op);
        }

        xor.fire();
        System.out.println("Result: " + xor.isFired());
        Assert.assertTrue(xor.isFired());
    }
}
