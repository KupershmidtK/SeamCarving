package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.*

class EnergyImage(val image: BufferedImage) : ImageModifier {
//    val energyMatrix = Array(image.width) { DoubleArray(image.height) { 0.0 } }
//    var maxEnergyValue: Double = 0.0

    override fun modify(): BufferedImage {
        val energyMatrix = Energy().calcEnergy(image)
        val maxEnergyValue = energyMatrix.flatMap { it.asList() }.maxOrNull()

        val energyImage = BufferedImage(image.width, image.height, image.type)
        for (x in 0 until energyImage.width)
            for (y in 0 until energyImage.height) {
                val intensity = (255.0 * energyMatrix[x][y] / maxEnergyValue!!).toInt()
                energyImage.setRGB(x, y, Color(intensity, intensity, intensity).rgb)
            }
        return energyImage
    }
}