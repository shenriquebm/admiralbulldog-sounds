package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.home.HomeScreen
import com.github.mrbean355.admiralbulldog.resources.WINDOW_HEIGHT
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import com.github.mrbean355.admiralbulldog.settings.SettingsScreen
import com.github.mrbean355.admiralbulldog.sounds.ViewSoundEventsScreen
import javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE
import tornadofx.View
import tornadofx.tab
import tornadofx.tabpane

class MainScreen : View(getString("main_title")) {

    override val root = tabpane {
        tabClosingPolicy = UNAVAILABLE
        prefWidth = WINDOW_WIDTH
        prefHeight = WINDOW_HEIGHT
        primaryStage.isResizable = false
        tab<HomeScreen>()
        tab<ViewSoundEventsScreen>()
        tab(getString("main_tab_discord_bot"))
        tab(getString("main_tab_dota_mod"))
        tab<SettingsScreen>()
    }
}
