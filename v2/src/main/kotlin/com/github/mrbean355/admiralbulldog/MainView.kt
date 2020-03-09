package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.resources.WINDOW_HEIGHT
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import tornadofx.View
import tornadofx.borderpane
import tornadofx.left
import tornadofx.listmenu

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
