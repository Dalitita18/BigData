import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer}
import org.apache.spark.ml.linalg.Vectors

// Se carga el dataFrame a partir de un csv
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")

// Se imprime el schema del dataFrame data
data.printSchema()

// Se eliminan los campos null 
val dataClean = data.na.drop()

// Se genera un vector que contine las caracteristicas que se van a evaluar
// y se guardan en la columna features
val vectorFeatures = (new VectorAssembler().setInputCols(Array("sepal_length","sepal_width", "petal_length","petal_width")).setOutputCol("features"))

// Se transforman los features usando el dataframe
val features = vectorFeatures.transform(dataClean)

// Se transforman los datos categoricos de species a datos numericos con la columna label
val speciesIndexer = new StringIndexer().setInputCol("species").setOutputCol("label")

// Ajustamos las especies indexadas con el vector features
val dataIndexed = speciesIndexer.fit(features).transform(features)

// Se separan los datos de entrenamiento y los datos de testing usando los datos indexados
val splits = dataIndexed.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

// Se establece la configuracion de las capas para el modelo 
val layers = Array[Int](4, 5, 4, 3)

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
