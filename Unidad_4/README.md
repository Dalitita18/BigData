## Final Project

- [**Introduction**](#introduction)
- [**Algorithms**](#algorithms)
    - [**Multilayer Perceptron Classifier**](#multilayer-perceptron-classifier)
    - [**Decision Tree**](#decision-tree)
    - [**Logistic Regression**](#logistic-regression)
- [**Results**](#results)
    - [**Results**](#decision-tree-test)
    - [**Results**](#multilayer-perceptron-classifier-test)
    - [**Results**](#logistic-regression-test)
- [**Conclusion**](#conclusion)
## Introduction

This investigation is about the performance of classification algorithms used in big data, using a csv of approximately 50,000 records. The objective is to compare these algorithms to determine based on their results which one has better performance.


## Algorithms 

### Multilayer Perceptron Classifier

The multilayer perceptron classifier (MLPC) is a classifier based on the artificial neural network of direct feeding. MLPC consists of multiple layers of nodes. Each layer is completely connected to the next layer in the network. The nodes in the input layer represent the input data.

### Decision Tree

It is a directional graph that begins at the base with a single node and extends to many sheets or nodes that represent the categories that the tree can classify. The intermediate nodes (the branches) are associated with one of the attributes and have 2 or more branches that come out of it, each representing the possible values that the associated attribute can take.

### Logistic Regression

Logistic regression is a statistical method to analyze a set of data in which there are one or more independent variables that determine a result. The result is measured with a dichotomous variable (in which there are only two possible outcomes). It is used to predict a binary result (1/0, Yes / No, True / False) given a set of independent variables.

## Results

Ten repetitions of each algorithm were made, an average is estimated based on the results obtained and the hardware performance was measured in each repetition.

### Decision Tree Test

#### Model

| Metrics    | Result   |
|------------|----------|
| Accurancy  | 0.891692 |
| Test Error | 0.1098   |
| Nodes      | 23       | 
| Tree Depth | 5        |

#### Performance
| Metrics     | First     | Tenth     |
|-------------|-----------|-----------|
| Used Memory | 350.73 MB | 224.10 MB |
| Runtime     | 12s       | 6s        |

### Multilayer Perceptron Classifier Test

#### Model

| Metrics    | Result   |
|------------|----------|
| Accurancy  | 0.8812   |
| Test Error | 0.1187   |
| Layers     | (5,4,2,2)| 

#### Performance
| Metrics     | First     | Tenth     | The Best  |
|-------------|-----------|-----------|-----------|
| Used Memory | 264.80 MB | 555.07 MB | 343.61 MB |
| Runtime     | 20s       | 14s       | 13s       |

### Logistic Regression Test

#### Model

| Metrics    | Result   |
|------------|----------|
| Accurancy  | 0.8848   |
| Test Error | 0.1151   |


#### Performance
| Metrics     | First     | Tenth     | The Best  |
|-------------|-----------|-----------|-----------|
| Used Memory | 327.81 MB | 451.87 MB | 229.78 MB |
| Runtime     | 12s       | 6s        | 5s        |

## Conclusion

With the results obtained after submitting the proposed dataset to different classification algorithms, we can say at a glance that the one that obtained the highest precision in these working conditions is that of Decision Tree since it obtained greater precision and low percentage of error, however given the performance obtained by the other algorithms the winner of these comparison is Logistic Regression demonstrated a saving of hardware and with a quite low execution time. Better results can be obtained if the proposed algorithms are used with some other configuration and with a computer with greater available hardware. The three algorithms tried to classify the dataSet based on 2 kinds of data and with 5 data as features.
