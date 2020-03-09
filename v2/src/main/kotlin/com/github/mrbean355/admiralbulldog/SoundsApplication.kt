package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.resources.BulldogIcon
import com.github.mrbean355.admiralbulldog.resources.WINDOW_HEIGHT
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import tornadofx.App
import tornadofx.Stylesheet
import tornadofx.View
import tornadofx.borderpane
import tornadofx.launch
import tornadofx.left
import tornadofx.listmenu

class SoundsApplication : App(
        primaryView = MainView::class,
        icon = BulldogIcon(),
        stylesheet = *arrayOf(AppStyles::class)
)

class MainView : View(getString("main_title")) {

    override val root = borderpane {
        left {
            listmenu(theme = "blue") {
                item(getString("main_tab_home"))
                item(getString("main_tab_choose_sounds"))
                item(getString("main_tab_discord_bot"))
                item(getString("main_tab_dota_mod"))
                item(getString("main_tab_settings"))
            }
        }
        prefWidth = WINDOW_WIDTH
        prefHeight = WINDOW_HEIGHT
        primaryStage.isResizable = false
    }
}

class AppStyles : Stylesheet()

fun main() {
    launch<SoundsApplication>()
}
