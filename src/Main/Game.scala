package Main


import java.awt.{Canvas, Color, Dimension, Graphics}
import javax.swing.{JFrame, WindowConstants}
import Mode._




object Game extends Canvas with Runnable {

private var running = false
private var thread: Thread = null
private var frame : JFrame = null
private val title: String = "Tower defense"
var Menumode : Option[MenuMode] = None
var PlayMode : Option[PlayMode] =None
var EndMode  : Option[EndMode] = None
var menuModeOn = true
var playModeOn = false
var endModeOn = false


class Game() {

  val size: Dimension = new Dimension(Constant.width * Constant.scale,Constant.height * Constant.scale)
  setPreferredSize(size)
  val game = Game
  game.frame = new JFrame()
  game.Menumode = Some(new MenuMode(this))
  game.PlayMode = Some(new PlayMode(this))
  game.EndMode = Some(new EndMode(this))
  game.addMouseListener(Menumode.get)

}

def update() : Unit={
  if(playModeOn && PlayMode.isDefined){
 PlayMode.get.update()
   }
}
def render(): Unit={
var bs = this.getBufferStrategy
   if(bs== null){this.createBufferStrategy(3)
   bs = this.getBufferStrategy }
var g: Graphics=  bs.getDrawGraphics
  if(playModeOn && PlayMode.isDefined) {
    g.drawImage(PlayMode.get.spawner.map.image,0,0,getWidth,getHeight,null)
    PlayMode.get.render(g)}
  if(menuModeOn && Menumode.isDefined) {
    g.setColor(Color.BLACK)
    g.fillRect(0,0,getWidth,getHeight)
    Menumode.get.render(g) }
  if(endModeOn && EndMode.isDefined) { EndMode.get.render(g)}
  g.dispose()
  bs.show()
 }
def stop(): Unit ={
 try{
  thread.join()
  running = false

 }catch{
  case e :InterruptedException => e.printStackTrace()
 }
 }
def run(): Unit = {
  var lasttime: Long = System.nanoTime()
  var timer: Long = System.currentTimeMillis()
  val ns: Double = 1000000000.0/ 60
  var delta: Double = 0.0
  var frames : Int = 0
  var updates = 0
while(running){
  var now : Long = System.nanoTime()
  delta += (now - lasttime)/ns
  lasttime = now
  while(delta>=1){
  update()
    updates += 1
  delta -= 1}

  render()
  frames += 1
  if(System.currentTimeMillis() - timer > 1000){
    timer += 1000
    frame.setTitle(title + " | "+ updates+ " ups " + frames + " fps")
    updates = 0
    frames = 0
  }
}
  stop()
}
def start() = {thread = new Thread(this,"Display")
   thread.start()
running= true}


def main(array: Array[String]): Unit ={
  var game: Game = new Game()
  game.game.frame.setResizable(false)
  game.game.frame.setTitle(game.game.title)
  game.game.frame.add(game.game)
  game.game.frame.pack()
  game.game.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  game.game.frame.setLocationRelativeTo(null)
  game.game.frame.setVisible(true)
  game.game.start()



  }
}

