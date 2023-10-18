package Enemies
import java.awt.Graphics
import scala.collection.mutable.Buffer
import Main.Player
class Wave() {
  // Enemies will be render but will not update(pause mode)
var On = false
var Enemies = Buffer[Enemies]()
var player : Option[Player]= None
  def switchOn()= {this.On= true}
def addEnemie(x : Enemies) ={
  this.Enemies += x
}
  def setEnemies(x : Buffer[Enemies]) ={
    this.Enemies = x
  }
  def setOn(x : Boolean) ={
    this.On = x
  }
  def addPlayer(pl: Player)= { this.player = Some(pl)}
 def update() : Unit ={
   if(On && player.isDefined ){
     this.Enemies.foreach(_.update(player.get))
     this.Enemies = this.Enemies.filter(x => x.inGame && x.isAlive)
}
 }
  def render( g: Graphics) : Unit ={
    for(i <- Enemies.indices) {
      this.Enemies(i).render(g)
    }
  }
}
