# ImageClassifier

Perceptron-based machine learning system for handwritten digit recognition built from scratch in Java. Specifically, the program usees the perceptron algorithm for image classification using supervised learning. Trained on 60,000 labeled images from the MNIST dataset, achieving **86% test accuracy**.

## Architecture

- **Perceptron.java** - Single perceptron with weighted sum computation and binary classification (+1/-1)
- **MultiPerceptron.java** - One-vs-all decomposition for multiclass prediction across 10 digit classes
- **ImageClassifier.java** - Full ML pipeline: feature extraction, training, and evaluation

## Key Results

| Metric | Value |
|--------|-------|
| Training examples | 60,000 |
| Test accuracy | 86% |
| Error rate (before training) | 90.4% |
| Error rate (after training) | 14% |

## How It Works

1. **Feature Extraction**: Converts 28x28 grayscale images into 784-dimensional vectors for manipulation
2. **Training**: Updates weight vectors based on prediction errors using the perceptron learning rule / perceptron algorithm
3. **Prediction**: One-vs-all elimination strategy - each perceptron votes, and the highest weighted sum wins and dictates the entirety of the prediction

## Usage
```bash
java-introcs ImageClassifier digits.txt digits-training60K.txt digits-testing1K.txt
```

## Insights

We were able to find that 9s are most frequently misclassified, mainly because the visual ambiguity of the angular are mixed up with 7.
