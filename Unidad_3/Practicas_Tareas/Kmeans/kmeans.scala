import org.apache.spark.sql.SparkSession

// Minimiza los erorres mostrados 
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()

// Se importar la libreria Kmeans
import org.apache.spark.ml.clustering.KMeans

// Se genera un dataFrame a partir de un txt
val dataset = spark.read.format("libsvm").load("sample_kmeans_data.txt")

// Se ejecuta el modelo Kmeans con k = 2
val kmeans = new KMeans().setK(2).setSeed(1L)
val model = kmeans.fit(dataset)

// Se evalua el costo computacional
val WSSE = model.computeCost(dataset)
println(s"Within set sum of Squared Errors = $WSSE")

// Se muestran los clusters formados
println("Cluster Centers: ")
model.clusterCenters.foreach(println)