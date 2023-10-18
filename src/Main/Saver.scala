package Main
import play.api.libs.json._
import play.api.libs.functional.syntax._
import java.io.FileWriter
import java.io.PrintWriter
import scala.collection.mutable.Buffer
import Enemies._
import Towers._

object Saver {
  private val EnemiesFileWrite = new FileWriter("EnemiesSaved.json")
  private val PlayerFileWrite = new FileWriter("PlayerSaved.json")
  private val TowerFileWrite = new FileWriter("TowerSaved.json")
  private val SpawnerSetupWrite = new FileWriter("SpawnerSetUp.json")
  case class EnemieRepresentaion(name: String,curentHealth: Int, counter: Int, Map: String,imageCounter: Int)
  case class TowerRepresentaion(name: String, sellPrice: Int, range: Int,level : Int, x: Int, y : Int)
  def EnemiesWritor(x: Enemies) : JsValue ={

  var name: Option[String] =None
  x match {
    case e: FastEnemies => name = Some("FastEnemies")
    case e: StrongEnemies => name = Some("StrongEnemies")
  }
  val represent = EnemieRepresentaion(name.get,x.curentHealth,x.counter,x.Map.toString,x.imageCounter)
  implicit val EnemiesWrtie : Writes[EnemieRepresentaion] =(
    (JsPath \ "name").write[String] and
    (JsPath \ "currentHealth" ).write[Int] and
    (JsPath \ "counter" ).write[Int] and
    (JsPath \ "Map" ).write[String] and
    (JsPath\ "imageCounter").write[Int])(unlift(EnemieRepresentaion.unapply ))
    val json = Json.toJson(represent)
    json
}
  def WaveWritor(x: Wave): JsValue ={
    val Enemies = JsArray(x.Enemies.toArray.map(EnemiesWritor(_)))
    val value : JsValue = Json.obj(
      "On" -> x.On,
      "Enemies" -> Enemies
    )
    value
  }
  def WavesWritor(x: Buffer[Wave]) ={
   val value = JsArray(x.toArray.map(WaveWritor(_)))
   val p = new PrintWriter(EnemiesFileWrite)
   p.print(Json.stringify(value))
   p.close()
    System.out.println("EnemiesDone")
  }

  def TowerWritor(x: Tower): JsValue ={
    var name: Option[String] = None
    x match {
      case e: HardSoilTower => name = Some("HardSoilTower")
      case e : RockTower => name = Some("RockTower")
      case e: FireBallTower => name = Some("FireBallTower")
    }
    val towerrepresent = TowerRepresentaion(name.get, x.sellPrice, x.range, x.level,x.x.get, x.y.get)
      implicit val TowerWrite : Writes[TowerRepresentaion] =(
        (JsPath \ "name").write[String] and
        (JsPath \ "sellPrice").write[Int] and
        (JsPath \ "range").write[Int] and
        (JsPath \ "level").write[Int] and
        (JsPath \ "x").write[Int] and
        (JsPath \ "y").write[Int])(unlift(TowerRepresentaion.unapply))
val json = Json.toJson(towerrepresent)
    json
  }
  def TowersWritor (x : Buffer[Tower]) : Unit ={
    val value = JsArray(x.toArray.map(TowerWritor(_)))
     val p = new PrintWriter(TowerFileWrite)
   p.print(Json.stringify(value))
   p.close()
    System.out.println("TowerDone")
  }
  def PlayerWritor(x : Player) ={
    val health = x.health
    val coins = x.coins
    val value = Json.obj(
      "health" -> health,
      "coins" -> coins
    )
   val p = new PrintWriter(PlayerFileWrite)
   p.print(Json.stringify(value))
   p.close()
    System.out.println("PlayerDone")
  }

  def SpawnerWritor() ={
    val value : JsValue = Json.obj(
      "waveNum"  -> Spawner.waveNum,
      "map" -> Spawner.map.toString,
      "nextWaveStart" -> Spawner.nextWaveStart,
      "currentCountDown" -> Spawner.currentCountDown
    )
   val p = new PrintWriter(SpawnerSetupWrite)
   p.print(Json.stringify(value))
   p.close()
   System.out.println("SpawnerDone")
  }

  }
