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
      (terms(0),terms(1))
    })

    val input = sc.textFile("TripletList").map(line=>{
      val l = line.split(",")
      val sub = l(0)
      val obj = l(2)

      words.foreach(f=>{
        if (sub.contains(f._1))
          (sub,f._2)
        if (obj.contains(f._1))
          (obj,f._2)
      })
    }).collect()


    val out = new PrintStream("sample")
    input.foreach(f=>{
      out.println(f)
    })
    out.close()
  }
}
