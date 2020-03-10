package com.github.mrbean355.admiralbulldog.resources

import com.github.mrbean355.admiralbulldog.SoundsApplication
import javafx.scene.image.Image

@Suppress("FunctionName")
fun BulldogIcon() = "bulldog.jpg".toImage()

@Suppress("FunctionName")
fun WeirdChampIcon() = "weird_champ.png".toImage()

@Suppress("FunctionName")
fun PoggiesIcon() = "poggies.png".toImage()

private fun String.toImage(): Image {
    return Image(SoundsApplication::class.java.classLoader.getResourceAsStream("icons/$this"))
}
