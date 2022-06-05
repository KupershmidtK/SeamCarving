package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.*

class SeamImage(val image: BufferedImage) : ImageModifier {

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
}