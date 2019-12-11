import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors

// Minimiza los erorres mostrados 
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()

// Se importar la libreria Kmeans
import org.apache.spark.ml.clustering.KMeans

// Se genera un dataFrame a partir de un CSV 
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Wholesale customers data.csv")

// Se imprime el schema del dataFrame
data.printSchema()

// Se genera un dataFrame seleccionando algunas columnas del dataFrame original
val feature_data = (data.select($"Fresh", $"Milk",$"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen"))

// Se limpia los null o nah del dataFrame 
val feature_data_clean = feature_data.na.drop()

// Se Genera un nuevo vector que contiene las caracteristicas que seran evaluadas
val featureData = (new VectorAssembler().setInputCols(Array("Fresh","Milk", "Grocery","Frozen", "Detergents_Paper","Delicassen")).setOutputCol("features"))

// Se transforma el vectorAssembler con los el dataFrame limpio
val features = featureData.transform(feature_data_clean)

// Se ejecuta el modelo Kmeans con k = 3
val kmeans = new KMeans().setK(3).setSeed(1L).setPredictionCol("cluster")
val model = kmeans.fit(features)

// Se evalua el costo computacional
val WSSE = model.computeCost(features)
println(s"Within set sum of Squared Errors = $WSSE")

// Se muestran los clusters formados
println("Cluster Centers: ")
model.clusterCenters.foreach(println)

// Se imprime un resumen de las predicciones y las caracteristicas evaluadas
model.summary.predictions.show()