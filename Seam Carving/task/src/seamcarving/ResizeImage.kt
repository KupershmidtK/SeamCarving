package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.*

class ResizeImage(val image: BufferedImage) : ImageModifier {

    override fun modify(): BufferedImage {
//        val energyMatrix = Energy().calcEnergy(image)
//
//        val seam = Seam().seam(energyMatrix)
//
//        val seamImage = BufferedImage(image.colorModel, image.copyData(null), false, null)
//        for (i in 0 until seamImage.height) {
//            seamImage.setRGB(seam[i], i, Color(255, 0, 0).rgb)
//        }

        val energyMatrixT = Energy().calcEnergyT(image)
        val seam = Seam().seam(energyMatrixT)
        val seamImage = BufferedImage(image.colorModel, image.copyData(null), false, null)
        for (i in 0 until seamImage.width) {
            seamImage.setRGB(i, seam[i], Color(255, 0, 0).rgb)
        }

        return seamImage
    }

    fun resize(width: Int, height: Int): BufferedImage {
        var resizeImage = BufferedImage(image.colorModel, image.copyData(null), false, null)

        for (i in 0 until width) {
            val energyMatrix = Energy().calcEnergy(resizeImage)
            val seam = Seam().seam(energyMatrix)

            var newX = 0
            val tmpImage = BufferedImage(resizeImage.width - 1, resizeImage.height, resizeImage.type)
            for (y in 0 until resizeImage.height) {
                for (x in 0 until resizeImage.width) {

                    if (x != seam[y]) {
                        tmpImage.setRGB(newX++, y, resizeImage.getRGB(x, y))
                    }
                }
                newX = 0
            }
            resizeImage = tmpImage
        }

        for (i in 0 until height) {
            val energyMatrix = Energy().calcEnergyT(resizeImage)
            val seam = Seam().seam(energyMatrix)

            var newY = 0
            val tmpImage = BufferedImage(resizeImage.width, resizeImage.height - 1, resizeImage.type)
            for (x in 0 until resizeImage.width) {
                for (y in 0 until resizeImage.height) {
                    if (y != seam[x]) {
                        tmpImage.setRGB(x, newY++, resizeImage.getRGB(x, y))
                    }
                }
                newY = 0
            }
            resizeImage = tmpImage
        }

        return resizeImage
    }

}