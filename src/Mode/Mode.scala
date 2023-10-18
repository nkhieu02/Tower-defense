package Mode

import Main.Game.Game

import java.awt.Graphics
import java.awt.event.{ MouseAdapter, MouseEvent}

abstract class Mode(game: Game) extends MouseAdapter  {


  def init()
  def update()
  def render(g: Graphics)
  def mouseOver(mouseX : Int, mouseY: Int, x: Int, y: Int, width: Int, height: Int ): Boolean ={
     if(mouseX > x && mouseX < x+ width){
       if(mouseY > y && mouseY < y+ height){
         true
     } else false
  }else false
  }
  override def mousePressed(e: MouseEvent): Unit = {

  }

  override def mouseReleased(e: MouseEvent): Unit = {}


}
