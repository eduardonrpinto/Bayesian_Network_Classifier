# Bayesian Network Classifier

This is a Java implementation of a Bayesian Network Classifier, developed as an academic project. The goal of the classifier is to classify vector of biomedical data according to if they correspond to a certain disease. A Bayesian network is a probabilistic graphical model that represents variables and their conditional dependencies using a directed acyclic graph, which can be trained to 

This project includes implementations for representing samples, graphs and Bayesian networks, as well as applications for working with the network and performing classification.

## Project Structure

The project consists of the following Java source files:

| File | Description |
|---|---|
| `Algoritmo.java` | Generates acandidate graphs and optimises them using MDL score improvements to learn the structure of a Bayesian network from a dataset.|
| `Amostra.java` | Reads a CSV file and stores its rows as samples of integer-valued variables. It provides methods to add and remove samples, access observations, count occurrences of specified variable values, and retrieve variable domains. |
| `AplicacaoRede.java` | Provides a graphical user interface that allows users to load a CSV dataset, specify the number of candidate graphs, the parameter `S`, execute the Bayesian network learning algorithm, and save the trained network to a file. |
| `AplicacaoClassificacao.java` | Provides a graphical user interface that allows users to download a trained Bayes Network Classifier, introduce a vector of biomedical observations and obtain a classification according to the trained classifier.  |
| `Classificador.java` | Explain how the classifier assigns classes to samples. |
| `Grafo.java` | Class representing a Directed Acyclic Graph. |
| `RN.java` | Class representing a Bayes Network. |

All Java classes belong to the `learning` package.
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

To run the application, first run the `AplicacaoRede.java` file to train a Bayes Classifier.

```bash
java -cp out learning.AplicacaoRede
```
Start by introducing the 'Number of graphs' you want to start the training with and the 'S' parameter (default 0.5). Then, press "Read Smaple" to download a biomedical dataset corresponding to a certain disease and finally press "Execute Algorithm" to train the Bayes Classifier.

### Classification Application

After having trained a Bayes Classifier, run `AplicacaoClassificacao.java` to classify a sample. Introduce a 'Vector' of biomedical data (the dimensions should match with the Sample with which you trained the Classifier) and press 'Read Bayes Network' to download the Bayes Network just trained. Then press 'Classify' to obtain the classification: 0 if it is negative for the diease, 1 if it is positive.

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
