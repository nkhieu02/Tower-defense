package Mode

import play.api.libs.json.{JsArray, Json}
import Main.Game.Game
import Main.{InitReader, Player, Saver, Spawner}
import Towers._

import java.awt.{Color, Font, Graphics}
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.{Files, Path}
import scala.collection.mutable.Buffer
import javax.imageio.ImageIO


class PlayMode(game: Game) extends Mode(game)  {
  private val HeartFile = new File("src/Graphic/Icon/Heart.png")
  private val CoinFile = new File("src/Graphic/Icon/Coin.png")
  private val PauseFile = new File("src/Graphic/Icon/PauseButton.png")
  private val PlayFile = new File("src/Graphic/Icon/PlayButton.png")
  private val HomeFile = new File("src/Graphic/Icon/HomeButton.png")
  private val BorderBackground = new File("src/Graphic/Icon/BorderBackground.png")
  private val BackButtonFile = new File("src/Graphic/Icon/back-button.png")
  private val SavedTower = Path.of("TowerSaved.json")
  private val BorderedBa = ImageIO.read(BorderBackground). getScaledInstance(280,40,BufferedImage.TYPE_BYTE_BINARY)
  private val HeartIcon = ImageIO.read(HeartFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  private val CoinIcon = ImageIO.read(CoinFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  private val PauseButton = ImageIO.read(PauseFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  private val PlayButton = ImageIO.read(PlayFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  private val HomeButton = ImageIO.read(HomeFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  private val BackButton = ImageIO.read(BackButtonFile).getScaledInstance(30,30,BufferedImage.TYPE_BYTE_BINARY)
  val spawner = Spawner
  private var availableTowerLocation = spawner.map.TowerLocation
   val player = new Player(game)
  var Towers = Buffer[Tower]()
   val KeyInput = new PlayModeKeyInput(this)
  var running = false
  var towerLocIsChosen = false
  var towerBeingChosen : Option[Tower] = None
  var towerLoc : Option[(Int,Int)] =None
  private val transaparentGreen = new Color(0,255,0,127)
  private val BROWN = new Color(110,60,30)
  var homePressed = false
// The game end if player have lost all lives or defeated all waves in case the spawner have loaded
  private def finish() ={
    !this.player.isAlive || (this.spawner.waves.forall(x => x.Enemies.isEmpty) && Spawner.haveInit)
  }
  def addTower(tower : Tower, x: Int,y : Int )= {
    if(player.coins >= tower.cost){
    player.coins -= tower.cost
    tower.getPos(x,y)
    Towers += tower
  }
  }
    def removeTower(tower: Tower) ={
    player.coins += tower.sellPrice
    towerBeingChosen = None
    Towers = Towers.filterNot(_ == tower)
  }


  override def init(): Unit = {

    towerLocIsChosen = false
    towerBeingChosen = None
    towerLoc = None
    Towers.clear()
    if(spawner.continue) {
    val TowerFileString = Files.readString(SavedTower)
    Towers = InitReader.TowersReader(Json.parse(TowerFileString).as[JsArray])}
    if( !spawner.haveInit ) {
      spawner.init()}
    player.init()
    spawner.addPlayer(player)
    spawner.haveInit = true
    availableTowerLocation = spawner.map.TowerLocation
    if(spawner.continue) {running = false}
    if(!spawner.continue) {
      spawner.nextWaveStart = System.nanoTime() + spawner.timeBetweenWaves * 1e9
      running = true}
  }
  override def update(): Unit = {
    if(!finish()) {
    if(running ){
    spawner.update()
    spawner.waves.foreach(_.update())
    spawner.waves.foreach(z => Towers.foreach(x => x.getTarget(z.Enemies)))
    Towers.foreach(_.update())
    spawner.waves.foreach(z => Towers.foreach(x => if(x.projection.isDefined) {x.projection.get.EnemiesBeingHit(z.Enemies)}))

  }
    else {spawner.timeupdate()}}
    else {
      game.game.EndMode.get.addPlayer(this.player)
      this.Towers.clear()
      this.running = false
      Spawner.waves.clear()
      Spawner.haveInit = false
      Spawner.continue = false
      game.game.removeMouseListener(this)
      game.game.addMouseListener(game.game.EndMode.get)
      game.game.playModeOn = false
      game.game.endModeOn = true
    }

  }

  override def render(g: Graphics): Unit = this.synchronized  {

    val Font1 = new Font("arial", 1, 20)
    val Font2 = new Font("arial", 1, 15)
    g.setFont(Font1)
    g.setColor(Color.BLACK)
    g.drawString(this.spawner.toString,20,20)
    g.drawImage(BorderedBa, 620,0,null)
    g.setColor(Color.ORANGE)
    g.drawRect(620,0,280,40)
    g.drawImage(HeartIcon, 640, 5,null)
    g.setFont(Font2)
    g.drawString(s"${player.health}",680,25)
    g.drawImage(CoinIcon, 720,5,null)
    g.drawString(s"${player.coins}", 760, 25)
    if(running) {g.drawImage(PauseButton,800,5,null)} else {g.drawImage(PlayButton,800,5,null)}
    g.drawImage(HomeButton,850,5,null)
    this.spawner.waves.foreach(_.render(g))
    for(i <- Towers.indices) {Towers(i).render(g)}
    if(towerLocIsChosen && running ) {
      g.setColor(transaparentGreen)
      g.fillOval(towerLoc.get._1 -35,towerLoc.get._2-22,70,50)}
    if(towerBeingChosen.nonEmpty && running) {
      towerBeingChosen.get.chosenRender(g)
    }
        if(homePressed) {
     g.setColor(BROWN)
     g.fillRect(300,150,300,150)
     g.setColor(Color.ORANGE)
     g.drawRect(300,150,300,150)
     g.setColor(Color.WHITE)
     g.fillRect(330,200,90,30)
     g.fillRect(480,200,90,30)
     g.setColor(Color.BLACK)
     g.drawRect(330,200,90,30)
     g.drawRect(480,200,90,30)
     g.setFont(Font1)
     g.drawString("Exit", 353, 220)
     g.drawString("Save", 503, 220 )
      g.setColor(Color.WHITE)
      g.fillRect(550,250,50,50)
      g.setColor(Color.ORANGE)
      g.drawRect(550,250,50,50)
      g.drawImage(BackButton,560,260,null)
    }

  }
  private def length( start: (Int,Int), end: (Int,Int)) ={
  math.sqrt(math.pow((end._1.toDouble-start._1.toDouble),2)+ math.pow((end._2.toDouble-start._2.toDouble),2))

  }
  private def clear() ={
    this.Towers.clear()
  this.player.clear()
  }
  override def mousePressed(e: MouseEvent): Unit = {
    val x = e.getX
    val y = e.getY
    if(mouseOver(x,y,850,5,30,30)) {
      running = false
      homePressed = true
}
    if(mouseOver(x,y, 330,200,90,30) && homePressed) {
      this.running = false
      this.clear()
      Spawner.waves.clear()
      Spawner.haveInit = false
      game.game.removeMouseListener(this)
      game.game.playModeOn = false
      game.game.menuModeOn = true
      game.game.addMouseListener(game.game.Menumode.get)
      homePressed = false
    }
    if(mouseOver(x,y, 550,250,50,50) && homePressed) {
      this.homePressed = false
      this.running = true
    }
    if(mouseOver(x,y, 480,200,90,30) && homePressed) {
      Saver.SpawnerWritor()
      Saver.WavesWritor(this.spawner.waves)
      Saver.TowersWritor(this.Towers)
      Saver.PlayerWritor(this.player)
    }
    if(mouseOver(x,y,800,5,30,30)) { running = !running}
    towerLoc = availableTowerLocation.find(z => length((x,y), z) < 25)
    towerBeingChosen = Towers.find(tower => length((x,y), (tower.x.get,tower.y.get)) < 25)
    if(towerLoc.isEmpty) {this.towerLocIsChosen = false}
    else {
      if( towerBeingChosen.isEmpty) {
        this.towerLocIsChosen = true
    }
    }


}


}