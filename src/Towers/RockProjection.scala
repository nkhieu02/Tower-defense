package Towers

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class RockProjection(start : Option[(Int,Int)], target : Option[(Int,Int)] ) extends Projection(6,6,start,target, 8) {

  private val movingImagesFile = new File("src/Graphic/Rock/RockMove.png")
  private val explodingImagesFile1 = new File("src/Graphic/Rock/RockBroken.png")
  private val explodingImagesFile2 = new File("src/Graphic/Rock/RockBroken1.png")
  private val explodingImagesFile3 = new File("src/Graphic/Rock/RockBroken2.png")
  private val explodingImagesFile4 = new File("src/Graphic/Rock/RockBroken3.png")
  private val movingImages = ImageIO.read(movingImagesFile).getScaledInstance(10,10,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding1 = ImageIO.read(explodingImagesFile1).getScaledInstance(10,10,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding2 = ImageIO.read(explodingImagesFile2).getScaledInstance(10,10,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding3 = ImageIO.read(explodingImagesFile3).getScaledInstance(10,10,BufferedImage.TYPE_BYTE_BINARY)
  private val exploding4 = ImageIO.read(explodingImagesFile4).getScaledInstance(10,10,BufferedImage.TYPE_BYTE_BINARY)
  override var images: Array[Image] = Array(movingImages,exploding1,exploding2,exploding3,exploding4)


  }

