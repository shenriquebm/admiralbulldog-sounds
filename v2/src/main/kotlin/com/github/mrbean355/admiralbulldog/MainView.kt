package com.github.mrbean355.admiralbulldog

import com.github.mrbean355.admiralbulldog.home.HomeView
import com.github.mrbean355.admiralbulldog.resources.WINDOW_HEIGHT
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import com.github.mrbean355.admiralbulldog.sounds.ViewSoundEventsView
import javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE
import tornadofx.View
import tornadofx.tab
import tornadofx.tabpane

class MainView : View(getString("main_title")) {

    override val root = tabpane {
        tabClosingPolicy = UNAVAILABLE
        prefWidth = WINDOW_WIDTH
        prefHeight = WINDOW_HEIGHT
        primaryStage.isResizable = false
        tab<HomeView>()
        tab<ViewSoundEventsView>()
        tab(getString("main_tab_discord_bot"))
        tab(getString("main_tab_dota_mod"))
        tab(getString("main_tab_settings"))
    }
}
