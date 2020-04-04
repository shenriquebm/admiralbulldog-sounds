package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.resources.SPACING_SMALL
import com.github.mrbean355.admiralbulldog.resources.SettingIcon
import com.github.mrbean355.admiralbulldog.resources.TEXT_LARGE
import com.github.mrbean355.admiralbulldog.resources.getString
import com.github.mrbean355.admiralbulldog.useCheckBoxWithButton
import javafx.scene.text.Font
import tornadofx.View
import tornadofx.label
import tornadofx.listview
import tornadofx.paddingAll
import tornadofx.vbox

class ChooseSoundEventsView : View(getString("main_tab_choose_sounds")) {
    private val viewModel by inject<ChooseSoundEventsViewModel>()

    override val root = vbox {
        label(getString("events_description")) {
            paddingAll = SPACING_SMALL
            font = Font(TEXT_LARGE)
        }
        listview<SoundEventType> {
            items = viewModel.soundEventTypes
            useCheckBoxWithButton(
                    buttonImage = SettingIcon(),
                    stringConverter = { it.friendlyName },
                    getSelectedProperty = { viewModel.getProperty(it) },
                    onButtonClick = { /* TODO: Open next screen */ }
            )
        }
    }
}
