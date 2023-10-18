package Main
import play.api.libs.json._
import scala.collection.mutable.Buffer
import Enemies._
import Map._
import Towers._

object InitReader {
def EnemiesReader(x : JsValue) : Enemies ={
  var Enemy: Enemies = null
  val name = (x \ "name").get.as[String]
  val currenthealth = (x \ "currentHealth").get.as[Int]
  val counter= (x \ "counter").get.as[Int]
  val mapString = (x \ "Map" ).get.as[String]
  val Map : Map =  if(mapString == "Map1") Map1 else Map2
  val imageCounter = (x \ "imageCounter").get.as[Int]
  if(name == "StrongEnemies") {Enemy = new StrongEnemies(Map, counter)}else {Enemy = new FastEnemies(Map, counter)}
  Enemy.setHealth(currenthealth)
  Enemy.imageCounter = imageCounter
  Enemy
}
  def WaveReader (x : JsValue) : Wave ={
    val Enemies = (x \ "Enemies").get.as[Seq[JsValue]]
    val On = (x \ "On").get.as[Boolean]
    var wave = new Wave
    wave.setEnemies(Enemies.map(EnemiesReader(_)).toBuffer)
    wave.setOn(On)
    wave
  }
  def TowerReader (x: JsValue): Tower ={
    var tower : Tower = null
    val name = (x \ "name").get.as[String]
    val sellPrice = (x \ "sellPrice").get.as[Int]
    val range = (x \ "range").get.as[Int]
    val level = (x \ "level").get.as[Int]
    val currentX = (x \ "x").get.as[Int]
    val currentY = (x \ "y").get.as[Int]
    if(name == "HardSoilTower") {
      tower = new HardSoilTower(Some(currentX), Some(currentY))
    tower.addProjection(new HardSoilProjection(tower.projectionLocation, None))}
    else if(name == "RockTower") {
      tower = new RockTower(Some(currentX), Some(currentY))
    tower.addProjection(new RockProjection(tower.projectionLocation, None))}
    else {
      tower = new FireBallTower(Some(currentX), Some(currentY))
    tower.addProjection(new FireBall(tower.projectionLocation, None))}
    tower.sellPrice = sellPrice
    tower.level = level
    tower

  }
  def TowersReader(x: JsArray) : Buffer[Tower] ={
    x.as[Buffer[JsValue]].map(TowerReader(_))
  }
  def WavesReader(x : JsArray) : Buffer[Wave] ={
    x.as[Buffer[JsValue]].map(WaveReader(_))
  }
  def SpawnerSetUp(x : JsValue) ={
      val waveNum = (x \ "waveNum").get.as[Int]
      val map = (x \ "map").get.as[String]
      val nextWaveStart = (x \ "waveNum").get.as[Double]
      val currentCountDown = (x \ "waveNum").get.as[Double]
      Spawner.waveNum = waveNum
      if(map == "Map2") {Spawner.map = Map2} else {Spawner.map = Map1}
      Spawner.nextWaveStart = nextWaveStart
      Spawner.currentCountDown = currentCountDown
}
  def PlayerReader(x: JsValue) ={
    val health = (x \ "health").get.as[Int]
    val coins = (x \ "coins").get.as[Int]
    (health,coins)
  }
}