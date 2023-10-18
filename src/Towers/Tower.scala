package Towers
import Enemies._
import java.awt.{Color, Graphics, Image}
import scala.collection.mutable.Buffer


abstract class Tower( var cost : Int, var sellPrice: Int, var range: Int, var x : Option[Int] ,var y : Option[Int] ) {

var images: Array[Image]
var projection: Option[Projection] =None
val width = 52
val heigth = 56
var level: Int = 1
var isChosen = false
private val transaparentGreen = new Color(0,255,0,60)

  def projectionLocation : Option[(Int,Int)] = if (x.isDefined && y.isDefined) Some(x.get-3 ,y.get -heigth ) else None
  def upgradePrice() : Int ={ 30* level}
  def addProjection(x: Projection) ={
  this.projection = Some(x)
  x.addTower(this)}

  def getPos(x: Int, y: Int): Unit ={
  this.x = Some(x)
  this.y = Some(y)
}
  def Inrange( enemy: Enemies) : Boolean ={
    if(x.isDefined && y.isDefined) {
  length((this.x.get,this.y.get), (enemy.currentX,enemy.currentY)) <= this.range}
    else false
}
  def getTarget(x: Buffer[Enemies]) ={
    if(projection.isDefined) {
    if(projection.get.target.isEmpty){
    val Enemie = x.find(z => this.Inrange(z)  && z.isAlive && z.inGame )
    if(Enemie.isDefined){
    // The target will be the position of the Enemy after 9 update if that position still in the Map.
    val target = Enemie.get.Map.fullPath(if(Enemie.get.counter+(9/ projection.get.speed) < Enemie.get.Map.fullPath.length -1 ) Enemie.get.counter+(9/ projection.get.speed) else Enemie.get.Map.fullPath.length -1)
    projection.get.setTarget(target)
    projection.get.getPath(projectionLocation.get,target)
    }


    }}
  }
 def render(g: Graphics) : Unit ={
  if(x.isDefined && y.isDefined ){
    g.drawImage(images(level-1), x.get - width/2+4,y.get- heigth*3/4,null)
    if(projection.isDefined) {projection.get.render(g)}
}
 }
 def chosenRender(g: Graphics): Unit ={
      g.setColor(transaparentGreen)
      g.fillOval(x.get - range,y.get - range,range* 2,range*2)
 }

  def update() : Unit ={
    if(this.projection.isDefined) {
    this.projection.get.update()}
  }
  def length( start: (Int,Int), end: (Int,Int)) ={
  math.sqrt(math.pow((end._1.toDouble-start._1.toDouble),2)+ math.pow((end._2.toDouble-start._2.toDouble),2))
  }
  def upgradable() : Boolean ={
    this.level < 3
  }

  def upgare() : Unit = {
  if(upgradable()){
  this.level += 1
  this.projection.get.damage += 2
  this.sellPrice += 25
  this.range+= 5
}
  }


}






