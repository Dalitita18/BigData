# Multilayer Perceptron Classifier

A multilayer perceptron (MLP) is a predictive artificial neural network model that maps the input data sets into a set of appropriate outputs. The multilayer perceptron is composed of an input layer, an output layer and n hidden layers in between.
It is characterized by having disjoint but related outputs, so that the output of one neuron is the input of the next.

In the multilayer perceptron a 2 phases can be distinguished:

- Propagation in which the network output result is calculated from the input values forward.
- Learning in which the errors obtained at the exit of the perceptron are propagated backwards (backpropagation) with the objective of modifying the weights of the connections so that the estimated value of the network is increasingly similar to the real one, this approach is made by the gradient error function.

## Code

Split the data into train and test

```scala
    val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
```

Create the trainer and set its parameters

```scala
    val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128)
    .setSeed(1234L).setMaxIter(100)
```

Train the model

```scala
    val model = trainer.fit(train)
```

## Output
![multilayer-perceptron](mlp.png)