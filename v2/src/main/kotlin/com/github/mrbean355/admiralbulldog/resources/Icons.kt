package com.github.mrbean355.admiralbulldog.resources

import com.github.mrbean355.admiralbulldog.SoundsApplication
import javafx.scene.image.Image

@Suppress("FunctionName")
fun BulldogIcon() = Image(SoundsApplication::class.java.classLoader.getResourceAsStream("icons/bulldog.jpg"))
