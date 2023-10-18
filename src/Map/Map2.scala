package Map



import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.Buffer

object Map2 extends Map {
private val bgFile = new File("src/Graphic/BackGround/map2.jpg")
private val bg= ImageIO.read(bgFile)
  override var image: BufferedImage = bg
  override var enemiesPath: Array[(Int, Int)] =  Array[(Int,Int)]((-17,230),(17,230),(103,282),(183,295),(230,265),(270,203),(304,196),(320,200),(360,206),(388,237),(409,295),(467,328),(538,341),(608,337),(675,305),(723,264),(804,267),(893,188))
  override var TowerLocation: Array[(Int, Int)] =  Array[(Int,Int)]((161,253),(312,241),(357,159),(563,297),(766,305),(792,221))
  override def toString: String = "Map2"
  var x : Int = -1000
  var y : Int = enemiesPath.head._2
  var pathCounter = 0
  // FullPath is the an array of all possible position of the enemy in the Map
  override var fullPath: Buffer[(Int, Int)] = Buffer[(Int, Int)]()

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

}
