import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

// Minimiza los erorres mostrados 
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()

// Se genera un dataFrame a partir de un CSV 
val data  = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("bank-full.csv")

// Se imprime el schema del dataFrame
data.printSchema()

// Se genera un dataFrame seleccionando algunas columnas del dataFrame original
val logregdataall = (data.select($"age", $"job",$"marital", $"education", $"default", $"balance", 
                    $"housing", $"loan",$"contact",$"day",$"month",$"duration",$"campaign",$"pdays",$"previous",
                    $"poutcome",$"y"))

// Se limpia los null o nah del dataFrame 
val logregdata = logregdataall.na.drop()

//Convertimos los valores de la columna y a valores numericos
val logData = logregdata.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val logData2 = logData.withColumn("y",when(col("y").equalTo("no"),0).otherwise(col("y")))
val newDF = logData2.withColumn("y",'y.cast("Int"))

// Se importar unas librerias 
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors

// (label, features)
// Se Genera un nuevo vector que contiene las caracteristicas que seran evaluadas
val assembler = (new VectorAssembler()
                  .setInputCols(Array("balance","day","duration","pdays","previous"))
                  .setOutputCol("features"))

// Se transforman los features usando el dataframe
val features = vectorFeatures.transform(newDF)

//Se renombre la columna y por label
val featuresLabel = features.withColumnRenamed("y", "label")

//Se seleccionan las columnas features y label como datos indexados 
val dataIndexed = featuresLabel.select("label","features")

// Se separan los datos de entrenamiento de los de pruebas
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 12345)

// Se inicia un modelo LogisticRegression
val logisticAlgorithm = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8).setFamily("multinomial")

// Fit del modelo
val logisticModel = logisticAlgorithm.fit(training)

// Se procesa el modelo con los datos de pruebas
val results = model.transform(test)

// Se importa una libreria para probar el modelo
import org.apache.spark.mllib.evaluation.MulticlassMetrics

// Se seleccionan las predicciones de los resultados dados por el modelo
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)

// Se imprime la matriz de confusion
println("Confusion matrix:")
println(metrics.confusionMatrix)

// Calcula el porcentaje de presicion del modelo
metrics.accuracy

