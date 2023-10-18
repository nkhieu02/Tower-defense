package Mode
import java.awt.{Color, Font, Graphics}
import Main.Game.{Game, PlayMode}
import Main._

import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import Map._



class MenuMode(game: Game) extends Mode(game)  {
  private val BackGroundFile = new File("src/Graphic/BackGround/MenuModeBackground.jpg")
  private var BackGround = ImageIO.read(BackGroundFile).getScaledInstance(game.size.width,game.size.height,BufferedImage.TYPE_BYTE_BINARY)
  private var settingsSelected : Boolean =false
  private var instructionsSelected : Boolean =false
  private var map1ButtonSelected: Boolean = true
  private var map2ButtonSelected: Boolean = false
  private var continueYesButton : Boolean = false
  private var continueNoButton : Boolean = true
   private def  mapSelected: Map = {if(map2ButtonSelected) Map2 else Map1}
  override def init(): Unit = {}

  override def mousePressed(e: MouseEvent): Unit = {
    val mouseX = e.getX
    val mouseY = e.getY
    if(mouseOver(mouseX,mouseY,300,280,300,50)&& !settingsSelected && !instructionsSelected) {
    this.game.game.removeMouseListener(this)
    this.game.game.addMouseListener(PlayMode.get)
    this.game.game.addKeyListener(PlayMode.get.KeyInput)
    this.game.game.PlayMode.get.init()
    this.game.game.menuModeOn = false
    this.game.game.playModeOn = true
    }
    if(mouseOver(mouseX,mouseY,350,360,200,50) && !settingsSelected && !instructionsSelected) {System.exit(1)}
    if(mouseOver(mouseX,mouseY,300,120,300,50)&& !settingsSelected && !instructionsSelected) {this.settingsSelected = true}
    if(mouseOver(mouseX,mouseY,300,200,300,50)&& !settingsSelected && !instructionsSelected) {this.instructionsSelected = true}
    if(mouseOver(mouseX,mouseY,700, 400,150,40) && this.instructionsSelected  ) {this.instructionsSelected = false
}
    if(mouseOver(mouseX,mouseY,700, 400,150,40) && this.settingsSelected ) {
      if(continueNoButton) {
        Spawner.continue = false
        Spawner.setMap(mapSelected)
        Spawner.init()
        Spawner.haveInit = true}
      if(continueYesButton) {
        Spawner.continue = true
        Spawner.init()
        Spawner.haveInit = true
      }

      this.settingsSelected = false
}
    if(mouseOver(mouseX,mouseY,400,80, 20,20) && this.settingsSelected ) {
      this.map1ButtonSelected = true
      this.map2ButtonSelected = false
      }
    if(mouseOver(mouseX,mouseY,400,200, 20,20) && this.settingsSelected) {
      this.map2ButtonSelected = true
      this.map1ButtonSelected = false
      }
    if(mouseOver(mouseX,mouseY,480,280, 20,20) && this.settingsSelected) {
      this.continueYesButton = true
      this.continueNoButton = false
    }
    if(mouseOver(mouseX,mouseY,680,280, 20,20) && this.settingsSelected) {
      this.continueNoButton = true
      this.continueYesButton = false
    }
  }
  override def update(): Unit = {}

  override def render(g: Graphics): Unit = {
    val Font1 = new Font("arial", 1, 50)
    val Font2 = new Font("arial", 1, 30)
    val Font3 = new Font("arial", 1, 15)

    g.drawImage(BackGround,0,0,null)
    if(instructionsSelected) {
      g.setColor(Color.WHITE)
      g.setFont(Font1)
      g.drawString("Help", 386,62)
      g.setFont(Font3)
      g.drawString("Using mouse to select and key to build and upgrade tower.", 100, 150)
      g.drawString("Key 1 : HardSoilTower | Key2 : RockTower | Key3: FireBallTower", 100, 180)
      g.drawString("Press key Q to upgrade after selecting Tower",100,210)
      g.drawString("Enemies run on fixed and available route. To let enemies pass the finish line is reduced to Heart.", 100, 240)
      g.drawString("There are 4 Waves per map. Win the game by eliminating all Waves.", 100, 270)
      g.drawRect(700, 400,150,40)
      g.setFont(Font2)
      g.drawString("Back",740, 430)

  }
    else if(settingsSelected) {
      g.setColor(Color.WHITE)
      g.setFont(Font1)
      g.drawString("MAP :", 100,100)
      g.drawRect(400,80, 20,20)
      g.drawRect(400,200, 20,20)
      if(map1ButtonSelected) {g.fillRect(400,80, 20,20)}
      if(map2ButtonSelected) {g.fillRect(400,200, 20,20)}
      g.setFont(Font2)
      g.drawString("Map1" , 450, 100)
      g.drawString("Map2" , 450, 220)
      g.drawRect(700, 400,150,40)
      g.setFont(Font2)
      g.drawString("Back",740, 430)
      g.setFont(Font1)
      g.drawString("Continue :", 100, 300)
      g.drawRect(480,280, 20,20)
      g.drawRect(680,280, 20,20)
      g.setFont(Font2)
      g.drawString("Yes", 400,295)
      g.drawString("No", 600,295)
      if(continueYesButton) {g.fillRect(480,280, 20,20)}
      if(continueNoButton) {g.fillRect(680,280, 20,20)}
    }
    else {
          g.setColor(Color.WHITE)
    g.setFont(Font1)
    g.drawString("Menu",386,62)
    g.setFont(Font2)
    g.drawRect(300,120,300,50)
    g.drawString("Settings",391, 155)
    g.drawRect(300,200,300,50)
    g.drawString("Instruction",374, 235 )
    g.drawRect(300,280,300,50)
    g.drawString("Start",414 , 315)
    g.drawRect(350,360,200,50)
    g.drawString("Quit", 420, 395 )
    }
  }

}
