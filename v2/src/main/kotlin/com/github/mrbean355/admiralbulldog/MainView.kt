package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.home.HomeView
import com.github.mrbean355.admiralbulldog.resources.WINDOW_HEIGHT
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import com.github.mrbean355.admiralbulldog.sounds.ChooseSoundEventsView
import tornadofx.View
import tornadofx.borderpane
import tornadofx.left
import tornadofx.listmenu

class MainView : View(getString("main_title")) {

    override val root = borderpane {
        left {
            listmenu(theme = "blue") {
                item(getString("main_tab_home")) {
                    whenSelected { center<HomeView>() }
                    activeItem = this
                }
                item(getString("main_tab_choose_sounds")) {
                    whenSelected { center<ChooseSoundEventsView>() }
                }
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