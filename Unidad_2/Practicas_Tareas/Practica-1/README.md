# Basic Statistics

## Correlation

The statistical correlation constitutes a statistical technique that indicates whether two variables are related or not. If the change in one variable is accompanied by a change in the other, then the variables are said to be correlated.

In spark.ml we provide the flexibility to calculate pairwise correlations among many series. The supported correlation methods are currently Pearson’s and Spearman’s correlation.

**Correlation** computes the correlation matrix for the input Dataset of Vectors using the specified method. The output will be a DataFrame that contains the correlation matrix of the column of vectors.

## Hypothesis Testing

Hypothesis testing is a powerful tool in statistics to determine whether a result is statistically significant, whether this result occurred by chance or not. spark.ml currently supports Pearson’s Chi-squared ( χ2) tests for independence.

ChiSquareTest conducts Pearson’s independence test for every feature against the label. For each feature, the (feature, label) pairs are converted into a contingency matrix for which the Chi-squared statistic is computed. All label and feature values must be categorical.

## Summarizer

We provide vector column summary statistics for Dataframe through Summarizer. Available metrics are the column-wise max, min, mean, variance, and number of nonzeros, as well as the total count.
