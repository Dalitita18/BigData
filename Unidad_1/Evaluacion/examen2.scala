//1
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
//2
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
//3
df.columns
//4
df.printSchema()
//5
df.take(5)
//6
df.describe().show()
//7
val newDT = df.withColumn("HV Ratio", df("High")+df("Volume"))
newDT.show()

//9 Es el cierre del precio en la bolsa de valores 

//10
df.select(max("Volume"),min("Volume")).show()
//11 a
df.select($"Close").filter($"Close" < 600).count()
//11 b
val res = df.select($"High").filter($"High">500).count()
val por = (res * 1.0 )/100.00
//11 c
df.select(corr("High", "Volume")).show()
//11 d
df.groupBy(year(df("Date")).alias("Year")).max("High").show()
//11 e
df.groupBy(month(df("Date")).alias("Month")).avg("Close").sort(asc("Month")).show()


