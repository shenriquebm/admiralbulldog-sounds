package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.components.useCheckBoxWithButton
import com.github.mrbean355.admiralbulldog.resources.PlayIcon
import com.github.mrbean355.admiralbulldog.resources.SPACING_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import com.github.mrbean355.admiralbulldog.resources.getString
import tornadofx.Fragment
import tornadofx.Scope
import tornadofx.listview
import tornadofx.paddingAll
import tornadofx.textfield
import tornadofx.vbox

class ChooseSoundBitesScreen : Fragment(getString("bites_title")) {
    private val viewModel by inject<ChooseSoundBitesViewModel>(Scope(), params)

    override val root = vbox(spacing = SPACING_SMALL) {
        paddingAll = SPACING_MEDIUM
        textfield {
            promptText = getString("bites_search")
            textProperty().bindBidirectional(viewModel.searchQuery)
        }
        listview(viewModel.soundBites) {
            useCheckBoxWithButton(
                    buttonImage = PlayIcon(),
                    stringConverter = { it.name },
                    getSelectedProperty = { viewModel.getSoundBiteEnabled(it) },
                    onButtonClick = { it.play(viewModel.getVolume()) }
            )
        }
    }
}