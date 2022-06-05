package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.pow
import kotlin.math.sqrt

class Energy {
    private lateinit var image: BufferedImage
    private lateinit var energyMatrix: Array<DoubleArray>

    fun calcEnergy(image: BufferedImage): Array<DoubleArray> {
        this.image = image
        energyMatrix = Array(image.width) { DoubleArray(image.height) { 0.0 } }
        for (x in 0 until image.width)
            for (y in 0 until image.height) {
                val energy = calcPixelEnergy(x, y)
                energyMatrix[x][y] = energy
//
//                if(energy > maxEnergyValue)
//                    maxEnergyValue = energy
            }
        return energyMatrix
    }

    fun calcEnergyT(image: BufferedImage): Array<DoubleArray> {
        val energy = calcEnergy(image)
        val width = energy.size
        val height = energy[0].size

        val energyT = Array(height) { DoubleArray(width) { 0.0 } }
        for (x in 0 until width)
            for (y in 0 until height) {
                energyT[y][x] = energy[x][y]
            }
        return energyT
    }


        private fun calcPixelEnergy(x: Int, y: Int): Double {
        val deltaX = calcDeltaX(x, y)
        val deltaY = calcDeltaY(x, y)
        return sqrt(deltaX + deltaY)
    }

    private fun calcDeltaX(x: Int, y: Int): Double {
        val x0 = if (x == 0) x + 1 else if (x == image.width - 1) x - 1 else x
        val colorXLeft = Color(image.getRGB(x0 - 1, y))
        val colorXRight = Color(image.getRGB(x0 + 1, y))

        val Rx = colorXRight.red - colorXLeft.red
        val Gx = colorXRight.green - colorXLeft.green
        val Bx = colorXRight.blue - colorXLeft.blue
        return Rx.toDouble().pow(2) + Gx.toDouble().pow(2) + Bx.toDouble().pow(2)
    }

    private fun calcDeltaY(x: Int, y: Int): Double {
        val y0 = if (y == 0) y + 1 else if (y == image.height - 1) y - 1 else y
        val colorYTop = Color(image.getRGB(x, y0 - 1))
        val colorYBottom = Color(image.getRGB(x, y0 + 1))

        val Ry = colorYBottom.red - colorYTop.red
        val Gy = colorYBottom.green - colorYTop.green
        val By = colorYBottom.blue - colorYTop.blue
        return Ry.toDouble().pow(2) + Gy.toDouble().pow(2) + By.toDouble().pow(2)
    }
}