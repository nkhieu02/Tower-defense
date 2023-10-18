package Map


import java.awt.image.BufferedImage
import scala.collection.mutable.Buffer


abstract class Map( )  {
  var image : BufferedImage
  var TowerLocation:  Array[(Int,Int)]
  var enemiesPath :  Array[(Int,Int)]
  var fullPath : Buffer[(Int,Int)]
       def length( start: (Int,Int), end: (Int,Int)) ={
  math.sqrt(math.pow((end._1.toDouble-start._1.toDouble),2)+ math.pow((end._2.toDouble-start._2.toDouble),2))
  }
     def projection( x: (Int, Int), start: (Int,Int), end: (Int,Int)) ={
     val length1 = length(start,x)
     val length2 = length(x,end)
     val length3 = length(start,end)
     val length4 = (length1+length2+length3)/2
     (length4*(length4-length1)*(length4-length2)*(length4-length3))/(length3)

}



}
