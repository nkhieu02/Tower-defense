package Towers
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class FireBall(start : Option[(Int,Int)], target : Option[(Int,Int)]) extends Projection(12,16,start,target, 8) {

  private val movingImagesFile = new File("src/Graphic/FireBall/FireBallMove.png")
  private val explodingImagesFile0 = new File("src/Graphic/FireBall/FireBallBroken.png")
  private val explodingImagesFile1 = new File("src/Graphic/FireBall/FireBallBroken1.png")
  private val explodingImagesFile2 = new File("src/Graphic/FireBall/FireBallBroken2.png")
  private val explodingImagesFile3 = new File("src/Graphic/FireBall/FireBallBroken3.png")
  private val movingImages = ImageIO.read(movingImagesFile).getScaledInstance(12,16,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding0 = ImageIO.read(explodingImagesFile0).getScaledInstance(12,16,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding1 = ImageIO.read(explodingImagesFile1).getScaledInstance(12,16,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding2 = ImageIO.read(explodingImagesFile2).getScaledInstance(12,16,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding3 = ImageIO.read(explodingImagesFile3).getScaledInstance(12,16,BufferedImage.TYPE_BYTE_BINARY)
  def burningTime(): Int ={
    if(this.tower.isDefined) 2+ this.tower.get.level else 2
  }
  override var images: Array[Image] = Array(movingImages,exploding0,exploding1,exploding2,exploding3)

 
}
