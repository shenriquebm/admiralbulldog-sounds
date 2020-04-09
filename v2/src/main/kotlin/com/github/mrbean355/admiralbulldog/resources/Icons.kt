@file:Suppress("FunctionName")

package com.github.mrbean355.admiralbulldog.resources

import com.github.mrbean355.admiralbulldog.SoundsApplication
import javafx.scene.image.Image

fun BulldogIcon() = "bulldog.jpg".toImage()
fun WeirdChampIcon() = "weird_champ.png".toImage()
fun PoggiesIcon() = "poggies.png".toImage()
fun PlayIcon() = "play.png".toImage()

private fun String.toImage(): Image {
    return Image(SoundsApplication::class.java.classLoader.getResourceAsStream("icons/$this"))
}
