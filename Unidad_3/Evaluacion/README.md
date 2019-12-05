## K-means

K-means is one of the simplest unsupervised learning algorithms that solve the clustering problem. 

### Algorithm of k-means
The first thing we need to know is the process of this method. K-means needs a k value to find the clusters that it will search in the dataset. 

The K-means algorithm can be explain in the following steps:

- Partition of objects into k non-empty subsets

- Identifying the cluster centroids

- Assigning each point to a specific cluster

- Compute the distances from each point to the cluster

- After re-allotting the points, find the centroid of the new cluster formed.

### K-means on Scala

The first, we're going to use the libraries below, theses libraries constains the functions that we need to configure our k means algorithms.
```scala
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.clustering.KMeans

```
Then, we need to import the log4j package to minimalize the errors on the output.

```scala
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

```
Also we need to start a new Spark session using the defult function.
```scala
// Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()
```
We need to create a new dataframe using an csv files that contains the data we will use in k-means

```scala
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Wholesale customers data.csv")
```
Before we have our dataframe created we need create other dataframe again but this time selecting a few columns from the orginal dataframe and we'll call it as feature_data.

```scala
val feature_data = (data.select($"Fresh", $"Milk",$"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen"))
```
We cleaned the feature_data before using, this feature data contains already all the data without nah or null values. 

```scala
val feature_data_clean = feature_data.na.drop()

```

Then, we create a new Vector Assembler that it will contain all the features inside of a column called features
```scala
val featureData = (new VectorAssembler().setInputCols(Array("Fresh","Milk", "Grocery","Frozen", "Detergents_Paper","Delicassen")).setOutputCol("features"))
```
We adjust the feature_data_clean (DataFrame) into our featureData (vector assembler) 
```scala
val features = featureData.transform(feature_data_clean)
```
It's time to use the KMeans function, we declare the configuration of our model, we use the K = 3 to search 3 cluster. Then, we fit the features that we created before and save them in a new model
```scala
val kmeans = new KMeans().setK(3).setSeed(1L).setPredictionCol("cluster")
val model = kmeans.fit(features)
```
The model has a computeCost function it's used to calculate the  sum of squared distance of points to their nearest center
```scala
val WSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSE")
```
Well, once we set all before we print the clusters that Kmeans find.
```scala
println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```