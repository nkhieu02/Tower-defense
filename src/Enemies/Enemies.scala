package Enemies

import java.awt.Graphics
import Map._
import Towers._
import Main._



 abstract class Enemies ( var coins: Int,val heath : Int,var Map: Map, var width: Int, var height: Int, val speed: Int,var counter : Int)  {

var currentX = Map.fullPath.head._1
var currentY = Map.fullPath.head._2
var running = true
var curentHealth = heath
var inGame = true
var imageCounter: Int = 0
var limit = Map.fullPath.length
private var nextBurning : Double = System.currentTimeMillis() + 1.0
private var burningTimes = 0.0

   def isAlive = {curentHealth > 0}
   def setMap(x: Map) = {this.Map = x}
   def setCounter(x: Int) ={this.counter = x}
   def setHealth(x : Int) ={this.curentHealth = x }
   def setImageCounter(x: Int) = {imageCounter = x }
   def addHealth(x : Int) ={this.curentHealth += x}
   def beingHit(projection : Projection) ={
       projection match {
           // FireBall has burning effect.
    case x: FireBall => {
      addHealth(-x.damage)
      burningTimes = x.burningTime()
      nextBurning = System.currentTimeMillis() + 1000
      }
    case x: HardSoilProjection => addHealth(-x.damage)
    case x : RockProjection => addHealth(-x.damage)
  }
}
   def update(x: Player): Unit ={
   if(isAlive && running && inGame) {
    if(counter < limit  ){
    currentX = Map.fullPath(counter)._1
    currentY = Map.fullPath(counter)._2
    counter += speed
     }
    else {
      x.reducehealth(1)
      inGame = false
    }
     if(burningTimes > 0) {
       val current: Double = System.currentTimeMillis()
       if( nextBurning - current <= 0) {
       addHealth(-3)
       burningTimes -= 1
     }
     }
  }
     if(!isAlive) {x.coins += coins}

}

   def render(g: Graphics): Unit

}
