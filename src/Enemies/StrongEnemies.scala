package Enemies
import java.awt.{Color, Graphics, Image}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import Map._

class StrongEnemies( Map : Map, counter: Int) extends Enemies (20,60, Map,36,24,1, counter){
  private val image1 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_000.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image2 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_001.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image3 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_002.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image4 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_003.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image5 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_004.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image6 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_005.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image7 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_006.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image8 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_007.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image9 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_008.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image10 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_009.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image11 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_010.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image12 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_011.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image13 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_012.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image14 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_013.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image15 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_014.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image16 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_015.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image17 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_016.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val image18 = ImageIO.read(new File("src/Graphic/Golem_01/Golem_02_Walking_017.png")).getScaledInstance(36,24,BufferedImage.TYPE_BYTE_BINARY)
  private val Images: Array[Image] =  Array(image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16,image17,image18)

  override def render(g: Graphics) = {
  if(counter < limit && isAlive){
  if(imageCounter < 340) {imageCounter+= 1} else {imageCounter= 0}
  g.drawImage(Images(imageCounter/20),currentX-18,currentY-12 ,null)
  g.setColor(Color.RED)
  g.fillRect(currentX - 11 ,currentY -16 ,25,2)
  g.setColor(Color.GREEN)
  g.fillRect(currentX - 11 ,currentY -16, 25 * curentHealth/ heath, 2)
}
}
}
