package seamcarving

import java.awt.Color
import java.awt.image.BufferedImage

class NegativeImage(val image: BufferedImage): ImageModifier {
    override fun modify(): BufferedImage {
        val negativeImage = BufferedImage(image.width, image.height, image.type)
        for(x in 0 until image.width)
            for (y in 0 until image.height) {
                val pixel = Color(image.getRGB(x, y))
                negativeImage.setRGB(x, y, Color(255 - pixel.red, 255 - pixel.green, 255 - pixel.blue).rgb)
            }
        return negativeImage
    }
}