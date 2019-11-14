## Multilayer Perceptron Classifier

The multilayer perceptron classifier (MLPC) is a classifier based on the artificial neural network of direct feeding. MLPC consists of multiple layers of nodes. Each layer is completely connected to the next layer in the network. The nodes in the input layer represent the input data.

## Architecture

- Input layer
    It is only responsible for receiving the input signals and propagating it to the next layer.
- Output layer
    Provides the network response to the outside for each input pattern.
- Hidden layers
    Perform a non-linear processing of the input data.

![MLPC](https://raw.githubusercontent.com/ledell/sldm4-h2o/master/mlp_network.png)

It is capable of acting as a universal approximator of functions: a backpropagation network containing at least one hidden layer with sufficient non-linear units can approximate any type of function or continuous relationship between a group of input and output variables. This property makes multilayer perceptron networks general, flexible and non-linear tools.

#### Base Function (Network Function)
The base function has two typical forms:
Hyperplane linear function: The network value is a linear combination of the Inputs.

#### Activation Function (Neuron Function)

The network value, expressed by the base function,is transformed by a non-linear activation function. The most common activation functions are sigmoidal

![sigmoidal](https://ml4a.github.io/images/figures/sigmoid.png)

