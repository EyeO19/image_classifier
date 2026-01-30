public class Perceptron {

    // creates variables to be used in methods
    private int n;
    // weight vector initalization
    private double[] wvector;


    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        this.n = n;
        this.wvector = new double[n];
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return n;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double wsum = 0;
        for (int i = 0; i < x.length; i++) {
            wsum += x[i] * wvector[i];
        }
        return wsum;
    }

    // Predicts the binary label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        double wsum = weightedSum(x);
        if (wsum > 0) {
            return +1;
        }
        else {
            return -1;
        }
    }

    // Trains this perceptron on the binary labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int binaryLabel) {
        int predict1 = predict(x);

        if (binaryLabel == 1 && predict1 == -1) {
            // Increase weights in the direction of x to match positive label
            for (int i = 0; i < x.length; i++) {
                wvector[i] += x[i];
            }
        }
        else if (binaryLabel == -1 && predict1 == 1) {
            // Decrease weights in the direction of x to correct positive prediction
            for (int i = 0; i < x.length; i++) {
                wvector[i] -= x[i];
            }
        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String result = "";
        for (int i = 0; i < wvector.length - 1; i++) {
            result += wvector[i] + ", ";
        }
        return "(" + result + wvector[wvector.length - 1] + ")";

    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        int n = 5;
        double[] x = { 2, 1, 4, 7, 8 };
        Perceptron perceptron = new Perceptron(n);
        System.out.println(perceptron.numberOfInputs());
        System.out.println(perceptron.weightedSum(x));
        System.out.println(perceptron.predict(x));
        perceptron.train(x, -1);
        System.out.println(perceptron.toString());

    }
}

