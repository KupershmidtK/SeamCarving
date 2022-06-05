package seamcarving

import java.awt.image.BufferedImage

interface ImageModifier {
    abstract fun modify(): BufferedImage
}
