public class MultiPerceptron {

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    private int n, m;
    // calls on to the individual perceptron
    private Perceptron[] perceptrons1;

    // constructor of multiperceptron
    public MultiPerceptron(int m, int n) {
        this.m = m;
        this.n = n;
        perceptrons1 = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptrons1[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted class label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double value = Double.NEGATIVE_INFINITY;
        int index = 0;
        for (int i = 0; i < m; i++) {
            if (perceptrons1[i].weightedSum(x) > value) {
                value = perceptrons1[i].weightedSum(x);
                index = i;
            }
        }
        return index;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int classLabel) {
        for (int i = 0; i < m; i++) {
            if (classLabel != i) {
                perceptrons1[i].train(x, -1);
            }
            else {
                perceptrons1[i].train(x, 1);
            }
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String multiresult = "(";
        for (int i = 0; i < m - 1; i++) {
            multiresult += perceptrons1[i].toString() + ", ";
        }
        return multiresult + perceptrons1[perceptrons1.length - 1] + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        double[] x = { 3, 2, 4, 5, 6 };
        MultiPerceptron multiperceptron = new MultiPerceptron(3, 2);
        System.out.println(multiperceptron.numberOfClasses());
        System.out.println(multiperceptron.numberOfInputs());
        System.out.println(multiperceptron.predictMulti(x));
        System.out.println(multiperceptron.predictMulti(x));
        multiperceptron.trainMulti(x, 2);

    }
}
