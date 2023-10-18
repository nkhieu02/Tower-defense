package Main

import play.api.libs.json.Json
import Main.Game.Game

import java.nio.file.{Files, Path}

class Player (game: Game) {
private val SavedPlayer = Path.of("PlayerSaved.json")
var health = 10
var coins = 170
def isAlive = {health > 0}
  def init() = {
    if(!Spawner.continue ) {
    health = 10
    coins = 170
  }
    else {
      val PlayerString = Files.readString(SavedPlayer)
      val values = InitReader.PlayerReader(Json.parse(PlayerString))
      health = values._1
      coins = values._2
    }
  }
  def clear()= {
    health = 10
    coins = 170
  }
  def addCoins(x: Int) ={this.coins += x}
  def reducehealth(x: Int) ={this.health -= x}
}
