// create an empty dataframe

scala> case class dummy(Name: String, ID :BigInt, Age: Int, Address : String)
defined class dummy

scala> val emptyDF = spark.emptyDataset[dummy].toDF
emptyDF: org.apache.spark.sql.DataFrame = [Name: string, ID: decimal(38,0) ... 2 more fields]

scala> emptyDF.show
+----+---+---+-------+
|Name| ID|Age|Address|
+----+---+---+-------+
+----+---+---+-------+


//another way to do that
scala> import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType}
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType}

scala> import org.apache.spark.sql.Row
import org.apache.spark.sql.Row

scala> val schema = StructType(
          StructField("Name", StringType, true) ::
          StructField("ID", IntegerType, false) :: 
          StructField("Age", IntegerType, true) ::
          StructField("Address", StringType, true) :: Nil)
schema: org.apache.spark.sql.types.StructType = StructType(StructField(Name,StringType,true), StructField(ID,IntegerType,false), StructField(Age,IntegerType,true), StructField(Address,StringType,true))

scala> val emptyDF = spark.createDataFrame(sc.emptyRDD[Row], schema)
emptyDF: org.apache.spark.sql.DataFrame = [Name: string, ID: int ... 2 more fields]

scala> emptyDF.show
+----+---+---+-------+
|Name| ID|Age|Address|
+----+---+---+-------+
+----+---+---+-------+


// using some operation on an existing column of a dataframe to create another column

scala> import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions._

scala> val a = sc.parallelize(List((1,20000),(2,23000),(3,34500))).toDF("ID","Salary")
a: org.apache.spark.sql.DataFrame = [ID: int, Salary: int]

scala> a.show
+---+------+
| ID|Salary|
+---+------+
|  1| 20000|
|  2| 23000|
|  3| 34500|
+---+------+


scala> val b = a.withColumn("incentive",lit(25000))
b: org.apache.spark.sql.DataFrame = [ID: int, Salary: int ... 1 more field]

scala> b.show
+---+------+---------+
| ID|Salary|incentive|
+---+------+---------+
|  1| 20000|    25000|
|  2| 23000|    25000|
|  3| 34500|    25000|
+---+------+---------+


scala> val myExpre = "12*Salary + incentive"
myExpre: String = 12*Salary + incentive

scala> val c = b.withColumn("AnnualSalary",expr(myExpre))
c: org.apache.spark.sql.DataFrame = [ID: int, Salary: int ... 2 more fields]

scala> c.show
+---+------+---------+------------+
| ID|Salary|incentive|AnnualSalary|
+---+------+---------+------------+
|  1| 20000|    25000|      265000|
|  2| 23000|    25000|      301000|
|  3| 34500|    25000|      439000|
+---+------+---------+------------+


