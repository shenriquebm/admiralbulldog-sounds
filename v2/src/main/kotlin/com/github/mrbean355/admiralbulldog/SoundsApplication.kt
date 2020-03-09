package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.dota.GameStateMonitor
import com.github.mrbean355.admiralbulldog.resources.BulldogIcon
import javafx.stage.Stage
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.launch

class SoundsApplication : App(
        primaryView = MainView::class,
        icon = BulldogIcon(),
        stylesheet = *arrayOf(AppStyles::class)
) {

    override fun start(stage: Stage) {
        super.start(stage)
        GameStateMonitor.start()
    }

    override fun stop() {
        GameStateMonitor.stop()
        super.stop()
    }
}

class AppStyles : Stylesheet()

fun main() {
    launch<SoundsApplication>()
}
