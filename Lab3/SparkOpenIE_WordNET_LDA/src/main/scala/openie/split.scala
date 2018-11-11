package openie

import java.io.PrintStream

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object split {
  def main(args: Array[String]) {

    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")


    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)



    // Turn off Info Logger for Console
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val input = sc.textFile("TripletList").map(line => {
      //Getting OpenIE Form of the word using lda.CoreNLP
      val terms = line.split(",")
      CoreNLP.returnLemma(terms(1))
    }).distinct().collect()

    val out = new PrintStream("Predicate")
    input.foreach(f=>{
      out.println(f)
    })
    //println(CoreNLP.returnTriplets("The dog has a lifespan of upto 10-12 years."))
    //println(input.collect().mkString("\n"))
    out.close()


    // StopWords
    //    val stopwords=sc.textFile("data/stopwords.txt").collect()
    //    val stopwordBroadCast=sc.broadcast(stopwords)
    //
    //    val input2= sc.textFile("data/sentenceSample").map(f=>{
    //      val afterStopWordRemoval=f.split(" ").filter(!stopwordBroadCast.value.contains(_))
    //    })

  }

}
