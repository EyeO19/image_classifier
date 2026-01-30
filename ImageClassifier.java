import java.awt.Color;

class ImageClassifier {
    // declaration of width, height variables for constructor
    private int width, height;
    // declaration of classNames string array
    private String[] classNames;
    private MultiPerceptron multiperceptron; // creation of multiperceptron

    // Uses the provided configuration file to create an
    // ImageClassifier object.
    public ImageClassifier(String configFile) {
        In config = new In(configFile);
        this.width = config.readInt();
        this.height = config.readInt();
        int m = config.readInt();
        int n = width * height;
        this.classNames = new String[m];
        for (int i = 0; i < m; i++) {
            classNames[i] = config.readString();
        }
        this.multiperceptron = new MultiPerceptron(m, n);
    }

    // Creates a feature vector (1D array) from the given picture.
    public double[] extractFeatures(Picture picture) {
        if (picture.width() != width || picture.height() != height) {
            throw new IllegalArgumentException("Dimensions are not equal");
        }
        int height1 = picture.height();
        int width1 = picture.width();
        double[] array1 = new double[height1 * width1];
        int i = 0;
        for (int row = 0; row < height1; row++) {
            for (int col = 0; col < width1; col++) {
                Color color = picture.get(col, row);
                array1[i] = color.getBlue();
                i++;
            }
        }
        return array1;
    }

    // Trains the perceptron on the given training data file.
    public void trainClassifier(String trainFile) {
        In train1 = new In(trainFile);
        while (!train1.isEmpty()) {
            String filename1 = train1.readString();
            int classlabel = train1.readInt();
            Picture pic = new Picture(filename1);
            double[] picturearray = extractFeatures(pic);
            multiperceptron.trainMulti(picturearray, classlabel);
        }
    }

    // Returns the name of the class for the given class label.
    public String classNameOf(int classLabel) {
        if (classLabel < 0 || classLabel >= classNames.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        return classNames[classLabel];
    }

    // Returns the predicted class for the given picture.
    public int classifyImage(Picture picture) {
        double[] extractPercept = extractFeatures(picture);
        return multiperceptron.predictMulti(extractPercept);
    }

    // Returns the error rate on the given testing data file.
    // Also prints the misclassified examples - see specification.
    // if statements

    // tests the classifier on different files
    public double testClassifier(String testFile) {
        In test1 = new In(testFile);
        double error = 0;
        double total = 0;
        while (!test1.isEmpty()) {
            String filename1 = test1.readString();
            int actualClassLabel = test1.readInt();
            Picture pic = new Picture(filename1);
            double[] pictureArray = extractFeatures(pic);
            int predictedLabel = multiperceptron.predictMulti(pictureArray);

            if (predictedLabel != actualClassLabel) {
                System.out.printf("%s, label = %s, predict = %s%n",
                                  filename1,
                                  classNameOf(actualClassLabel),
                                  classNameOf(predictedLabel)
                );
                error += 1;
            }
            total += 1;
        }
        return error / total;

    }

    // Tests this class using a configuration file, training file and test file.
    // See below.
    public static void main(String[] args) {
        ImageClassifier classifier = new ImageClassifier(args[0]);
        classifier.trainClassifier(args[1]);
        double testErrorRate = classifier.testClassifier(args[2]);
        System.out.println("test error rate = " + testErrorRate);

    }
}

