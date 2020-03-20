package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import tornadofx.View
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.scrollpane
import tornadofx.separator
import tornadofx.vbox

private val HOVER_BACKGROUND = Background(BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))
private val INACTIVE_BACKGROUND = Background(BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))

class ChooseSoundEventsView : View() {
    private val viewModel by inject<ChooseSoundEventsViewModel>()

    override val root = scrollpane(fitToWidth = true) {
        vbox {
            paddingAll = SPACING_SMALL
            viewModel.soundEventTypes.forEach {
                label(viewModel.getName(it)) {
                    paddingAll = SPACING_SMALL
                    prefWidthProperty().bind(this@vbox.widthProperty())
                    setOnMouseEntered {
                        background = HOVER_BACKGROUND
                    }
                    setOnMouseExited {
                        background = INACTIVE_BACKGROUND
                    }
                }
                separator()
            }
        }
    }
}
