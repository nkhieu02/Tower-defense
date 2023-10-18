package Towers

import java.awt.{Graphics, Image}
import scala.collection.mutable.Buffer
import Enemies._

 abstract  class Projection( var width: Int, var height: Int, var start : Option[(Int,Int)], var target : Option[(Int,Int)] , var damage : Int) {
   var tower: Option[Tower] = None
   var currentPath : Array[(Int,Int)] = Array()
   var pathCounter = 0
   var currentX  : Option[Int]=  None
   var currentY :  Option[Int]=  None
   var images : Array[Image]
   var imagesCounter = 0
   // The enemy will be hitted one. The breaking effect, which keep the projection at the same spot, will not cause more damge to the enemy
   var hitted = false
   def  speed: Int = if(tower.get.level >=3) 2 else 1
   def addTower(x: Tower) ={
     this.tower = Some(x)
}

def getPath(start : (Int,Int), end : (Int,Int))= {
  val xStart = start._1
  val yStart = start._2
  val xEnd = end._1.toDouble
  val yEnd = end._2.toDouble
  val velocity = (xEnd-xStart)/10
  val noDirectionVelocity = math.abs(velocity)
  var gravity= if(velocity < 0) ((yEnd-yStart)-2 * (xEnd-xStart))/100 else ((yEnd-yStart)+ 2 * (xEnd-xStart))/100
  var x = xStart
  var y = yStart
  val path =  Buffer[(Int,Int)]((xStart,yStart))
  var time = 0
  for(i <- 0 to 9){
   time+= 1
    x = (xStart + velocity* time).toInt
    y = (yStart - (noDirectionVelocity * time * 2)  + gravity* math.pow(time,2)).toInt
    path += (x -> y)
  }
  currentPath = path.toArray
}
   def setTarget(x: (Int,Int)) ={
     if(this.target.isEmpty) this.target = Some(x)
   }
   // Define whether the projection hit the enemy.
      def canHit(enemies: Enemies)  ={
        if(currentX.isDefined && currentY.isDefined) {
    (math.abs(currentX.get - enemies.currentX) < (this.width+ enemies.width/ 2) ) && (math.abs(currentY.get - enemies.currentY) < (this.height+ enemies.height/ 2) )
} else false
      }
   // Performing hitting effect
   def EnemiesBeingHit(enemies:  Buffer[Enemies]) ={
     enemies.foreach(z => if(this.canHit(z) && (!hitted)) {
       z.beingHit(this)
       hitted = true
     })
   }


   def render(g: Graphics)={

     if(currentX.isDefined && currentY.isDefined){
     g.drawImage(images(imagesCounter/5), currentX.get,currentY.get,null)
     }
}
   def update() ={
     if(target.isDefined && start.isDefined) {
       if(pathCounter < currentPath.length -speed  ) {
       pathCounter += speed
       currentX = Some(currentPath(pathCounter)._1)
       currentY = Some(currentPath(pathCounter)._2)}
       else {
         // Breaking effect
       if(imagesCounter < images.length* 5 -1 ) {
         imagesCounter += 1
       }
       else {
       target =None
       hitted = false
       imagesCounter = 0
       currentPath = Array[(Int,Int)]()
       pathCounter = 0
       currentX =Some(start.get._1)
       currentY =Some(start.get._2)
       }
      }
  }
 }
 }