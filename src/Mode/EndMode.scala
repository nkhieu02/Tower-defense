package Mode

import Main.Game.Game
import Main.Player

import java.awt.event.MouseEvent
import java.awt.{Color, Font, Graphics}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class EndMode (game: Game) extends Mode(game) {
  private val BackGroundFile = new File("src/Graphic/BackGround/MenuModeBackground.jpg")
  private val BackGround = ImageIO.read(BackGroundFile).getScaledInstance(game.size.width,game.size.height,BufferedImage.TYPE_BYTE_BINARY)
  var player: Option[Player] = None
  def addPlayer(x: Player) {this.player = Some(x)}
  override def init(): Unit = {}

  override def update(): Unit = {}

  override def render(g: Graphics): Unit = {
    val Font1 = new Font("arial", 1, 50)
    val Font2 = new Font("arial", 1, 30)
    g.setColor(Color.WHITE)
    g.setFont(Font1)
    g.drawImage(BackGround,0,0,null)
    if(player.get.isAlive) {
      g.drawString("VICTORY !", 310,200)
    }
    else{g.drawString("GAME OVER.", 300,200)}
    g.drawRect(700, 400,150,40)
    g.setFont(Font2)
    g.drawString("Menu",740, 430)
  }

  override def mousePressed(e: MouseEvent): Unit = {
    val mouseX = e.getX
    val mouseY = e.getY
    if(mouseOver(mouseX,mouseY,700, 400,150,40)){
      this.game.game.removeMouseListener(this)
      this.game.game.addMouseListener(this.game.game.Menumode.get)
      this.game.game.endModeOn = false
      this.game.game.menuModeOn = true
    }
    }

}
