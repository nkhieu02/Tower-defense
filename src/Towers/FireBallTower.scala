package Towers
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class FireBallTower(x: Option[Int], y : Option[Int])extends Tower( 125, 100,100,x,y) {
  private val imageFile1 = new File("src/Graphic/FireRockTower/FireRockTower1.png")
  private val imageFile2 = new File("src/Graphic/FireRockTower/FireRockTower2.png")
  private val imageFile3 = new File("src/Graphic/FireRockTower/FireRockTower3.png")
  private val image1 = ImageIO.read(imageFile1).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)
  private val image2 = ImageIO.read(imageFile2).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)
  private val image3 = ImageIO.read(imageFile3).getScaledInstance(width,heigth,BufferedImage.TYPE_BYTE_BINARY)

  override var images: Array[Image] = Array(image1,image2,image3)

}
