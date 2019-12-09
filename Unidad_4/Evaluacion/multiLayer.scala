import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

// Minimiza los erorres mostrados 
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

//Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()

// Se carga el dataFrame a partir de un csv
val data  = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("bank-full.csv")

// Se imprime el schema del dataFrame dataq
data.printSchema()

//Convertimos los valores de la columna y a valores numericos
val dataColumn = data.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val dataColumn2 = dataColumn.withColumn("y",when(col("y").equalTo("no"),0).otherwise(col("y")))
val newcolumn = dataColumn2.withColumn("y",'y.cast("Int"))

// Se genera un vector que contine las caracteristicas que se van a evaluar
// y se guardan en la columna features
val vectorFeatures = (new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features"))

// Se transforman los features usando el dataframe
val features = vectorFeatures.transform(newcolumn)

//Se renombre la columna y por label
val featuresLabel = features.withColumnRenamed("y", "label")

//Se seleccionan las columnas features y label como datos indexados 
val dataIndexed = featuresLabel.select("label","features")

// Se separan los datos de entrenamiento y los datos de testing usando los datos indexados
val splits = dataIndexed.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

// Se establece la configuracion de las capas para el modelo 
val layers = Array[Int](5,4,2,2)

// Se configura el entrenador del algoritmo Multilayer
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

// Se entrena el modelo usando los datos de entrenamiento
val model = trainer.fit(train)

// Se  ejecuta el modelo con los datos de pruebas
val result = model.transform(test)

// Se selecciona la prediccion y la etiqueta
val predictionAndLabels = result.select("prediction", "label")

// Se ejecuta la estimacion de la precision del modelo
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

// Se imprime el resultado de la presicion
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")