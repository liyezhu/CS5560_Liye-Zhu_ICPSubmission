package openie

import java.io.PrintStream

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object Schema {
  def main(args: Array[String]) {

    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")


    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)



    // Turn off Info Logger for Console
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val words = sc.textFile("MedicalWords.csv").map(line => {
      val l= line.replaceAll(" ", "").replaceAll("-", "").replaceAll("/", "").replaceAll("'","")
      val terms = l.split(",")
      terms(1)
    }).distinct().collect()


    val out = new PrintStream("Classes")
    words.foreach(f=>{
      out.println(f)
    })
    out.close()
  }
}
