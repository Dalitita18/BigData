import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

// Minimiza los erorres mostrados 
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Se inicia una sesion en spark
val spark = SparkSession.builder().getOrCreate()

// Se genera un dataFrame a partir de un CSV 
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("titanic.csv")

// Se imprime el schema del dataFrame
data.printSchema()
// Se imprime el primer registro del dataFrame
data.head(1)

// Imprime  el primer registro por columna
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
// Se genera un dataFrame seleccionando algunas columnas del dataFrame original
val logregdataall = (data.select(data("Survived").as("label"), $"Pclass", $"Name",
                    $"Sex", $"Age", $"SibSp", $"Parch", $"Fare", $"Embarked"))

// Se limpia los null o nah del dataFrame 
val logregdata = logregdataall.na.drop()

// Se importar unas librerias 
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors

// Convertir valores catagoricos a valores numericos
val genderIndexer = new StringIndexer().setInputCol("Sex").setOutputCol("SexIndex")
val embarkIndexer = new StringIndexer().setInputCol("Embarked").setOutputCol("EmbarkIndex")

// Convertir los valores numericos a One Hot Encoding 0 - 1
val genderEncoder = new OneHotEncoder().setInputCol("SexIndex").setOutputCol("SexVec")
val embarkEncoder = new OneHotEncoder().setInputCol("EmbarkIndex").setOutputCol("EmbarkVec")
// (label, features)
// Se Genera un nuevo vector que contiene las caracteristicas que seran evaluadas
val assembler = (new VectorAssembler()
                  .setInputCols(Array("Pclass","SexVec", "Age","SibSp","Parch","Fare","EmbarkVec"))
                  .setOutputCol("features"))

// Se separan los datos de entrenamiento de los de pruebas
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)

// Importacion de libreria
import org.apache.spark.ml.Pipeline

// Se inicia un modelo LogisticRegression
val lr = new LogisticRegression()

// Se genera un pipeline con las columnas indexadas y con el vector de features
val pipeline = new Pipeline().setStages(Array(genderIndexer,embarkIndexer,genderEncoder,embarkEncoder,assembler,lr))

// Se ajusta el pipeline con los datos de entrenamiento
val model = pipeline.fit(training)

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
