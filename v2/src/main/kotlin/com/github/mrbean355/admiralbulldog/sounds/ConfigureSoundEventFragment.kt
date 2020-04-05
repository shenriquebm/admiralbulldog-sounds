package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.resources.SPACING_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import com.github.mrbean355.admiralbulldog.resources.TEXT_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.WINDOW_WIDTH
import com.github.mrbean355.admiralbulldog.resources.getString
import javafx.scene.text.Font
import tornadofx.Fragment
import tornadofx.Scope
import tornadofx.button
import tornadofx.checkbox
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.paddingAll
import tornadofx.slider
import tornadofx.spacer

class ConfigureSoundEventFragment : Fragment() {
    private val viewModel by inject<ConfigureSoundEventViewModel>(Scope(), params)

    override val root = form {
        paddingAll = SPACING_MEDIUM
        fieldset {
            label(viewModel.description) {
                font = Font(TEXT_MEDIUM)
                isWrapText = true
            }
        }
        fieldset {
            checkbox(getString("event_enabled"), viewModel.isEnabled)
        }
        fieldset(getString("event_chance")) {
            slider(0.0, 100.0) {
                valueProperty().bindBidirectional(viewModel.chance)
                isShowTickLabels = true
                isShowTickMarks = true
                majorTickUnit = 10.0
                minorTickCount = 1
                isSnapToTicks = true
            }
        }
        fieldset(getString("event_sound_bites")) {
            label(viewModel.soundBitesChosen)
            spacer() {
                prefHeight = SPACING_SMALL
            }
            button(getString("event_select_sound_bites"))
        }
        prefWidth = WINDOW_WIDTH
    }

    init {
        titleProperty.bind(viewModel.title)
    }
}
