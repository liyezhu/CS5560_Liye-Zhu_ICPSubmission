package openie

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import rita.RiWordNet

/**
  * Created by Mayanka on 27-Jun-16.
  */
object SparkOpenIE {

  def main(args: Array[String]) {

    // For Windows Users
    System.setProperty("hadoop.home.dir", "C:\\winutils")
    // Configuration
    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)



    val input = sc.textFile("data/abstracts").map(line => {
      //Getting OpenIE Form of the word using lda.CoreNLP
      val t=CoreNLP.returnTriplets(line)
      val terms = t.split(",")
      (line,terms.size/4)
    }).cache()
    //println(CoreNLP.returnTriplets("The dog has a lifespan of upto 10-12 years."))
    //println(input.collect().mkString("\n"))


    val output=input.reduceByKey(_+_)

    output.saveAsTextFile("output")

    val o=output.collect()

    var s:String="Words:\nCount \n"
    o.foreach{
      case(word,count)=>{
        s+=word+" : \n"+count+"\n"
      }}


  }

}
