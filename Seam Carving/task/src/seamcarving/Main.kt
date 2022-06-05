package seamcarving

import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    if (args.size < 8 || args[0] != "-in" || args[2] != "-out")
        throw IllegalArgumentException()

    val image = ImageIO.read(File(args[1]))

//    val imageModifier: ImageModifier = NegativeImage(image)
//    val imageModifier: ImageModifier = EnergyImage(image)
//    val imageModifier: ImageModifier = SeamImage(image)
    val imageModifier = ResizeImage(image)
    ImageIO.write(imageModifier.resize(args[5].toInt(), args[7].toInt()), "png", File(args[3]))
}
