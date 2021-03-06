import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object BioWordCountSpark {
  def main(args:Array[String]){
    val sc = new SparkContext(new SparkConf().setAppName("BioWordCount-Spark"))
    val file = sc.textFile(args(0))
    val rows = file.filter(!_.startsWith("#"))
    val refVar = rows.map(_.split("\t").drop(3).take(2))
    val refVarNoIndel = refVar.filter(refvar => (refvar(0).length() == 1) && (refvar(1).length() == 1))
    val answer = refVarNoIndel.map(data => (data(0)+" -> "+data(1) -> 1)).reduceByKey(_ + _)
    answer.sortByKey().saveAsTextFile("answer")
  }
}
