package com.github.mrbean355.admiralbulldog.home

import com.github.mrbean355.admiralbulldog.resources.SPACING_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import com.github.mrbean355.admiralbulldog.resources.TEXT_LARGE
import com.github.mrbean355.admiralbulldog.resources.URL_DISCORD_SERVER_INVITE
import com.github.mrbean355.admiralbulldog.resources.URL_PROJECT_WEBSITE
import com.github.mrbean355.admiralbulldog.resources.getString
import javafx.geometry.Orientation.VERTICAL
import javafx.geometry.Pos.CENTER
import javafx.scene.text.Font
import tornadofx.View
import tornadofx.hbox
import tornadofx.hyperlink
import tornadofx.imageview
import tornadofx.label
import tornadofx.managedWhen
import tornadofx.paddingAll
import tornadofx.progressbar
import tornadofx.separator
import tornadofx.vbox
import tornadofx.visibleWhen

class HomeScreen : View(getString("main_tab_home")) {
    private val viewModel by inject<HomeViewModel>()

    override val root = vbox(spacing = SPACING_SMALL, alignment = CENTER) {
        paddingAll = SPACING_MEDIUM
        imageview(viewModel.icon)
        label(viewModel.title) {
            font = Font(TEXT_LARGE)
        }
        progressbar {
            visibleWhen(viewModel.isLoading)
            managedWhen(viewModel.isLoading)
            prefWidthProperty().bind(this@vbox.widthProperty())
        }
        label(viewModel.description)
        hbox(spacing = SPACING_SMALL, alignment = CENTER) {
            hyperlink(getString("home_link_discord_community")) {
                setOnAction {
                    hostServices.showDocument(URL_DISCORD_SERVER_INVITE)
                }
            }
            separator(orientation = VERTICAL)
            hyperlink(getString("home_link_project_website")) {
                setOnAction {
                    hostServices.showDocument(URL_PROJECT_WEBSITE)
                }
            }
        }
    }
}
