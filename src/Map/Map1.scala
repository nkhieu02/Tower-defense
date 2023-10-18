package Map




import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.Buffer

object Map1 extends Map() {
private val bgFile = new File("src/Graphic/BackGround/map 1.jpg")
private val bg= ImageIO.read(bgFile)
override var image: BufferedImage = bg
override var enemiesPath: Array[(Int, Int)] =  Array[(Int,Int)]((17,323),(87,323),(185,327),(257,324),(318,309),(357,271),(375,173),(453,153),(512,177),(533,219),(579,264),(669,275),(731,293),(795,322),(884,326))
override var TowerLocation: Array[(Int, Int)] =  Array[(Int,Int)]((285,273),(448,201),(621,227),(657,318))

  var x : Int = -1000
  var y : Int = 323
  var pathCounter = 0
  override var fullPath: Buffer[(Int, Int)] = Buffer[(Int, Int)]()
  // Helping method to create all possible position for the enemy based on the enemiesPath representation points.
  private def helper() ={
    val currentPos = enemiesPath(pathCounter)
  val nextPos = enemiesPath(pathCounter+1)
  val currentYDif = (nextPos._2 -currentPos._2)
  val currentXDif = (nextPos._1 - currentPos._1)
  val currentRaito = math.abs((nextPos._2.toDouble -currentPos._2.toDouble)/(nextPos._1.toDouble -currentPos._1.toDouble))
  if(currentXDif> 0 ){
  if(currentYDif > 0   ){
    if(currentRaito !=  1){
      if(projection((x,y+1),currentPos,nextPos) >= projection((x+1,y),currentPos,nextPos)) {
        x+= 1

      }
      else y+= 1
    }
    else {
    x+= 1
    y+= 1}
  }
  else if(currentYDif < 0){
      if(currentRaito != 1){
      if(projection((x,y-1),currentPos,nextPos) >= projection((x+1,y),currentPos,nextPos)) {
        x+= 1

      }
      else y-= 1
    }
    else {x+= 1
    y-= 1}
  }
  else {x+= 1}
  }
  else if (currentXDif < 0) {
    if(currentYDif > 0   ){
    if(currentRaito !=  1){
      if(projection((x,y+1),currentPos,nextPos) >= projection((x-1,y),currentPos,nextPos)) {
        x-= 1

      }
      else y+= 1
    }
    else {
    x-= 1
    y+= 1}
  }
  else if(currentYDif < 0){
      if(currentRaito != 1){
      if(projection((x,y-1),currentPos,nextPos) >= projection((x-1,y),currentPos,nextPos)) {
        x-= 1

      }
      else y-= 1
    }
    else {x-= 1
    y-= 1}
  }
  else {x-= 1}

  }
  else {
    if(currentYDif>0) y+=1 else y-= 1
  }
    if(x == enemiesPath(pathCounter+1)._1)  {pathCounter+= 1}
  }
while(pathCounter < enemiesPath.length - 1) {
  helper()
  fullPath += x -> y
}

  override def toString: String = "Map1"
}
