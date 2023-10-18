package Towers

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class RockTower(x: Option[Int], y : Option[Int]) extends Tower( 100,75,100,x,y) {

  val imageFile1 = new File("src/Graphic/RockTower/RockTower1.png")
  val imageFile2 = new File("src/Graphic/RockTower/RockTower2.png")
  val imageFile3 = new File("src/Graphic/RockTower/RockTower3.png")
  val image1 = ImageIO.read(imageFile1).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)
  val image2 = ImageIO.read(imageFile2).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)
  val image3 = ImageIO.read(imageFile3).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)

  override var images: Array[Image] = Array(image1,image2,image3)

}
