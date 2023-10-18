package Main

import play.api.libs.json.{JsArray, JsValue, Json}
import java.nio.file.{Files, Path}
import scala.collection.mutable.Buffer
import Map._
import Enemies.Wave



object Spawner {
private val Map1fileName = Path.of("EnemiesInitMap1.json")
private val Map2fileName = Path.of("EnemiesInitMap2.json")
private val savedSetup = Path.of("SpawnerSetUp.json")
private val savedEnemies = Path.of("EnemiesSaved.json")
var waveNum = -1
var timeBetweenWaves = 10.0
var map: Map = Map1
var nextWaveStart = System.nanoTime() + timeBetweenWaves*1e9
var currentCountDown = 10.0
var waves = Buffer[Wave]()
  // Define whether to load from init files or saved files
var continue = false
var haveInit = false
def setMap (x : Map)=  {this.map = x}
 def addPlayer(x: Player) ={this.waves.foreach(_.addPlayer(x))}

 def init() = {
   if(!continue) {
    val fileName = if(map == Map1) Map1fileName else Map2fileName
    val file = Files.readString(fileName)
    val json: JsValue = Json.parse(file)
    val WavesRead = json.asInstanceOf[JsArray]
    waves = InitReader.WavesReader(WavesRead)
    waveNum = -1
    nextWaveStart = System.nanoTime() + timeBetweenWaves*1e9}
   else {
     val setupFileString = Files.readString(savedSetup)
     val enemiesFileString = Files.readString(savedEnemies)
     InitReader.SpawnerSetUp(Json.parse(setupFileString))
     waves = InitReader.WavesReader(Json.parse(enemiesFileString).as[JsArray])
   }

  }
  def clear() ={
   this.waves.clear()
   this.waveNum = -1
  }
   def timeUntilNextWave = {
      var curTime = System.nanoTime()
      currentCountDown = (nextWaveStart - curTime)/1e9
     currentCountDown
    }
   def update() = {
           if(timeUntilNextWave <= 0) {
             if(waveNum < waves.size-1){
        waveNum += 1
        waves(waveNum).switchOn()
        nextWaveStart =System.nanoTime() + timeBetweenWaves*1e9
}

           }
    }
  // Compensate the passing time of pausing the game
  def timeupdate() ={
  nextWaveStart =  System.nanoTime()+ currentCountDown* 1e9}

  override def toString: String = if(waveNum< 0) "Loading..." else "Wave #"+ (waveNum+1)




}

