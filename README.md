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

## Datasets

The `datasets` directory contains sample data for testing the application. Each one of them is associated with a disease (cancer, diabetes etc.), contains several vectors with biomedical data and provides a classification for these vectors according to weather they indicate the presence of the disease (1) or not (0). They should be used to train the Bayes Network Classifier.

## Running the Application

To run the application, first run the `AplicacaoRede.java` file to train a Bayes Classifier.

```bash
java -cp out learning.AplicacaoRede
```
Start by introducing the `Number of graphs` you want to start the training with and the `S` parameter (default 0.5). Then, press "Read Smaple" to download a biomedical dataset corresponding to a certain disease and finally press `Execute Algorithm` to train the Bayes Classifier.

### Classification Application

After having trained a Bayes Classifier, run `AplicacaoClassificacao.java` to classify a sample. Introduce a `Vector` of biomedical data (the dimensions should match with the Sample with which you trained the Classifier) and press `Read Bayes Network` to download the Bayes Network just trained. Then press `Classify` to obtain the classification: 0 if it is negative for the diease, 1 if it is positive.

```bash
java -cp out learning.AplicacaoClassificacao
```

Run each command separately. If the application requires input files, ensure that the required files are available at the paths expected by the program.

## Author

- Eduardo Pinto

## Repository

[Bayesian Network Classifier](https://github.com/eduardonrpinto/Bayesian_Network_Classifier)
