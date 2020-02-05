
import java.io._
import java.text.SimpleDateFormat

import scala.language.reflectiveCalls
import scala.collection.mutable.{ArrayBuffer, Map}

import java.time.format.DateTimeFormatter



object CsvTask extends  App {

  using(io.Source.fromFile("201608-citibike-tripdata.csv")) { source =>

  {
    var alltrevel = 0
    var allbikes = Set[String]()
    var bike = Map[String, Int ]()
    var Manth = Map[Int, Int]()

    var x = 1
    val datetime_format = DateTimeFormatter.ofPattern("\"MM/dd/yyyy HH:mm:ss\"")
    var date = 0




    var mail  = 0
    var femail = 0



    val allstring  = source.getLines().toArray

    for (line <- 1 to allstring.size-1) {
      val split_string = allstring(line).split(",")


      val format = new SimpleDateFormat("\"MM/dd/yyyy hh:mm:ss\"")
      val date1 = (format.parse(split_string(1)))

      val date2 = (format.parse(split_string(2)))


      if (date<(((date2.getMinutes - date1.getMinutes)*60)+(date2.getSeconds - date1.getSeconds))){
        date = (((date2.getMinutes - date1.getMinutes)*60)+(date2.getSeconds - date1.getSeconds))
      }

      if (!Manth.contains(date1.getMonth)) {
        Manth += (date1.getMonth -> (x))
      }

      else{
        Manth(date1.getMonth) += 1
      }



      if (!bike.contains(split_string(11))){
        bike += (split_string(11)-> x)
      }

      else

      {
        bike(split_string(11)) += 1

      }

      if (split_string(14) == "\"1\"" ){
        mail+=1
      }
      if (split_string(14) == "\"2\""){
        femail +=1
      }


      allbikes += split_string(11)
      alltrevel+=1

    }
    //println(string_to_date )

    //println(date)
    println(allbikes.size)
    println(bike)
    println(Manth)

    println(date)




    println(alltrevel)
    println(mail * 100 / alltrevel + "%")
    println(femail * 100 / alltrevel + "%")





  }
  }

  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B =
    try {
      f(param)
    } finally {
      param.close()
    }
  def writeFile (fileName:String ,lines : ArrayBuffer[String]):Unit = {
    val fail = new File(fileName)
    //val bw = BufferedWriter(new FileWriter(fileName))
    for (line <- lines){
    //  bw.write(line + "\n")
    }
    //bw.cloase()
  }
}


