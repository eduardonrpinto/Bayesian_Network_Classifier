# Bayesian Network Classifier

A Java implementation of a Bayesian Network Classifier, developed as an academic project. The application provides functionality for representing Bayesian networks and performing classification using probabilistic relationships between variables.

## Overview

A Bayesian network is a probabilistic graphical model that represents variables and their conditional dependencies using a directed acyclic graph.

This project includes implementations for representing samples, graphs and Bayesian networks, as well as applications for working with the network and performing classification.

## Project Structure

The main Java classes are:

| Class | Description |
|---|---|
| `Algoritmo` | Algorithm-related functionality |
| `Amostra` | Represents a sample |
| `AplicacaoRede` | Entry point for the network application |
| `AplicacaoClassificacao` | Entry point for the classification application |
| `Classificador` | Classification logic |
| `Grafo` | Graph representation |
| `RN` | Bayesian network representation |

All Java classes use the `learning` package.

## Requirements

- Java Development Kit (JDK)
- Git (optional, for cloning the repository)

Verify your Java installation:

```bash
java -version
javac -version
```

## Getting Started

Clone the repository:

```bash
git clone https://github.com/eduardonrpinto/Bayesian_Network_Classifier.git
```

Navigate to the project directory:

```bash
cd Bayesian_Network_Classifier
```

## Compilation

From the directory containing the Java source files, compile the project:

```bash
mkdir -p out
javac -d out *.java
```

The compiled classes will be placed in the `out` directory.

## Running the Application

### Bayesian Network Application

```bash
java -cp out learning.AplicacaoRede
```

### Classification Application

```bash
java -cp out learning.AplicacaoClassificacao
```

Run each command separately. If the application requires input files, ensure that the required files are available at the paths expected by the program.

## Datasets and Testing

The `datasets/` directory is intended to contain sample data for testing the application.

Suggested datasets include:

- **Weather Classification:** A small categorical dataset for predicting whether an activity is recommended based on weather conditions.
- **Iris Dataset:** A standard flower classification dataset. Numerical measurements may need to be discretised if the classifier supports only categorical variables.
- **Car Evaluation Dataset:** A categorical dataset for predicting car acceptability.

The datasets used must follow the input format expected by the application. Check the source code for the required file format, column structure and file paths before using external datasets.

The Car Evaluation dataset is available from the [UCI Machine Learning Repository](https://archive.ics.uci.edu/dataset/19/car+evaluation).

## Troubleshooting

### Main class not found

Ensure the project has been compiled and use the fully qualified class name, including the lowercase package name `learning`.

### Java compiler not found

Install a JDK and ensure that `java` and `javac` are available in your terminal.

### Dataset file not found

Check that the required input files exist and that their paths match those expected by the application. Relative paths may depend on the directory from which the program is launched.

## Authors

- Eduardo
- Mateus
- Ana

## Repository

[Bayesian Network Classifier](https://github.com/eduardonrpinto/Bayesian_Network_Classifier)
