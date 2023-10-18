package Mode



import Towers._

import java.awt.event.{KeyAdapter, KeyEvent}

class PlayModeKeyInput(mode: PlayMode) extends KeyAdapter{

  override def keyPressed(e: KeyEvent): Unit = {
    val key = e.getKeyCode
    if(mode.towerLocIsChosen){
    if(key ==  KeyEvent.VK_1){
      val x = new HardSoilTower(None,None)
     mode.addTower(x,mode.towerLoc.get._1,mode.towerLoc.get._2)
      x.addProjection(new HardSoilProjection(x.projectionLocation, None))
    mode.towerLocIsChosen = false}
    if(key ==  KeyEvent.VK_2){
      val x = new RockTower(None,None)
     mode.addTower(x,mode.towerLoc.get._1,mode.towerLoc.get._2)
      x.addProjection(new RockProjection(x.projectionLocation, None))
      mode.towerLocIsChosen = false
    }
    if(key ==  KeyEvent.VK_3){
      val x = new FireBallTower(None,None)
     mode.addTower(x,mode.towerLoc.get._1,mode.towerLoc.get._2)
      x.addProjection(new FireBall(x.projectionLocation, None))
      mode.towerLocIsChosen = false
    }
    }
    if(mode.towerBeingChosen.nonEmpty) {
      if(key == KeyEvent.VK_Q && mode.player.coins >= mode.towerBeingChosen.get.upgradePrice()) {
        mode.player.coins -= mode.towerBeingChosen.get.upgradePrice()
        mode.towerBeingChosen.get.upgare()
    }
      if(key == KeyEvent.VK_R) {
        mode.player.coins += mode.towerBeingChosen.get.sellPrice
        mode.removeTower(mode.towerBeingChosen.get)
      }
  }

  }
}
