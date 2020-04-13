package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.resources.SPACING_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import com.github.mrbean355.admiralbulldog.resources.TEXT_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.getString
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.View
import tornadofx.borderpane
import tornadofx.center
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.paddingBottom
import tornadofx.scrollpane
import tornadofx.separator
import tornadofx.top
import tornadofx.vbox

private val HOVER_BACKGROUND = Background(BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))
private val INACTIVE_BACKGROUND = Background(BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))

class ViewSoundEventsScreen : View(getString("main_tab_choose_sounds")) {
    private val viewModel by inject<ViewSoundEventsViewModel>()

    override val root = borderpane {
        paddingAll = SPACING_MEDIUM
        top {
            label(getString("events_description")) {
                paddingBottom = SPACING_SMALL
                font = Font(TEXT_MEDIUM)
            }
        }
        center {
            scrollpane(fitToWidth = true) {
                vbox() {
                    viewModel.soundEventTypes.forEach { eventType ->
                        label(eventType.friendlyName) {
                            paddingAll = SPACING_SMALL
                            prefWidthProperty().bind(this@vbox.widthProperty())
                            setOnMouseEntered {
                                background = HOVER_BACKGROUND
                            }
                            setOnMouseExited {
                                background = INACTIVE_BACKGROUND
                            }
                            setOnMouseClicked {
                                find<ConfigureSoundEventScreen>("type" to eventType)
                                        .openModal(resizable = false)
                            }
                        }
                        separator()
                    }
                }
            }
        }
    }
}
