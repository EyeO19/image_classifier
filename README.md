# ImageClassifier

A command-line Java program that classifies handwritten digits (0–9) using a multiclass perceptron built from scratch. Intended as a portfolio piece for reviewers who want to see a full ML pipeline (feature extraction, training, evaluation) without a deep learning framework.

## Key Features

- **Binary perceptron** (`Perceptron.java`): weighted sum, binary prediction (+1 / −1), and online weight updates via the perceptron learning rule
- **Multiclass extension** (`MultiPerceptron.java`): one-vs-all training with 10 perceptrons; prediction picks the class whose perceptron has the highest weighted sum
- **End-to-end pipeline** (`ImageClassifier.java`): loads a config file, converts 28×28 images into 784-dimensional feature vectors, trains on a labeled manifest, and evaluates on a test manifest
- **Misclassification logging**: prints each wrong prediction (file path, actual label, predicted label) during testing
- **Documented results**: 60,000 training examples, ~86% test accuracy (14% error rate) on a 1,000-image test set

## Tech Stack

| Component | Details |
|-----------|---------|
| Language | Java (standard JDK; no version pinned in this repo) |
| JDK API | `java.awt.Color` for pixel values |
| Dataset | MNIST-style handwritten digits, loaded from `digits.jar` |
| Build / deps | No Maven, Gradle, or `package.json`; compile and run with `javac` / `java` and a classpath |

## Architecture

```
digits.txt                  →  Image dimensions (28×28), class count (10), class names
digits-training60K.txt      →  Training manifest (image path + label per line)
digits-testing1K.txt        →  Test manifest (image path + label per line)
         ↓
ImageClassifier
  ├── extractFeatures()     →  Flatten 28×28 image to 784 doubles (blue channel per pixel)
  ├── trainClassifier()     →  Feed each example to MultiPerceptron.trainMulti()
  └── testClassifier()      →  Predict each test image; log errors; return error rate
         ↓
MultiPerceptron (10 × Perceptron)
  ├── trainMulti()          →  One-vs-all: +1 for target class, −1 for all others
  └── predictMulti()        →  Return index of perceptron with highest weighted sum
```

**File roles**

| File | Purpose |
|------|---------|
| `Perceptron.java` | Single binary classifier |
| `MultiPerceptron.java` | Array of perceptrons for multiclass digit recognition |
| `ImageClassifier.java` | Config loading, feature extraction, training loop, evaluation |
| `digits.txt` | Config: width, height, number of classes, class name strings |
| `digits-training60K.txt` | 60,000 training entries (`jar:file:digits.jar!/training/...png` + label) |
| `digits-testing1K.txt` | 1,000 test entries (`jar:file:digits.jar!/testing/...png` + label) |

## Results

| Metric | Value |
|--------|-------|
| Training examples | 60,000 |
| Test set size | 1,000 |
| Test accuracy | ~86% |
| Test error rate | ~14% |
| Error rate before training | ~90.4% |

## Demo

<!-- TODO: Replace with your own assets -->
[SCREENSHOT: Sample MNIST digit images used as input]

[SCREENSHOT OR GIF: Terminal output showing misclassified examples and final line `test error rate = 0.14`]

[OPTIONAL: Link to screen recording or live demo — [YOUR DEMO URL]]

## Setup and Run

### Prerequisites

1. **JDK 8+** installed (`java` and `javac` on your PATH)
2. **`introcs.jar`** from Princeton's introcs library ([download page](https://introcs.cs.princeton.edu/java/code/))
3. **`digits.jar`** in the project root (same directory as the `.java` files). The training and test manifest files reference images inside this JAR. [YOUR NOTE: where you obtained digits.jar, e.g. course materials]

### Compile

From the project root:

```bash
javac -cp introcs.jar:. Perceptron.java MultiPerceptron.java ImageClassifier.java
```

On Windows, use `;` instead of `:` in the classpath:

```bash
javac -cp introcs.jar;. Perceptron.java MultiPerceptron.java ImageClassifier.java
```

### Run

```bash
java -cp introcs.jar:. ImageClassifier digits.txt digits-training60K.txt digits-testing1K.txt
```

If you have the Princeton `java-introcs` alias configured (common in COS126 setups), this is equivalent:

```bash
java-introcs ImageClassifier digits.txt digits-training60K.txt digits-testing1K.txt
```

Training on 60,000 images takes several minutes depending on your machine.

### Expected output

The program prints one line per misclassified test image, then a summary:

```
jar:file:digits.jar!/testing/..., label = nine, predict = seven
...
test error rate = 0.14
```

A error rate of `0.14` corresponds to 86% accuracy.

### Arguments

| Position | File | Description |
|----------|------|-------------|
| `args[0]` | `digits.txt` | Classifier configuration |
| `args[1]` | `digits-training60K.txt` | Training manifest |
| `args[2]` | `digits-testing1K.txt` | Test manifest |

## Design Notes

- **One-vs-all multiclass**: Each digit class gets its own binary perceptron. During training, the correct class receives label +1 and every other class receives −1 for the same feature vector. At prediction time, the class with the largest weighted sum wins.
- **Pixel features, no preprocessing**: Feature extraction reads the blue channel of each pixel in row-major order into a 784-element vector. There is no normalization, PCA, or convolution.
- **Linear model limits**: A single-layer perceptron on raw pixels reaches roughly 86% on this task. That is expected for a linear classifier on MNIST; the value is in implementing the learning algorithm directly rather than maximizing benchmark score.
- **Observed error pattern**: 9s were the most frequently misclassified digit, often confused with 7s, likely due to similar angular strokes in handwritten samples.

## Project Context

[YOUR CONTEXT: e.g. personal project / coursework for COS126 at Princeton, completed Spring 2025. What you implemented vs. what came from course starter files.]

## License

[YOUR LICENSE: e.g. MIT — add a LICENSE file if you choose one]
