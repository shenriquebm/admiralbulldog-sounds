package com.github.mrbean355.admiralbulldog.settings

import com.github.mrbean355.admiralbulldog.components.slider
import com.github.mrbean355.admiralbulldog.resources.SPACING_MEDIUM
import com.github.mrbean355.admiralbulldog.resources.getString
import tornadofx.View
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.paddingAll

class SettingsScreen : View(getString("settings_title")) {
    private val viewModel by inject<SettingsViewModel>()

    override val root = form {
        paddingAll = SPACING_MEDIUM
        fieldset(getString("settings_volume")) {
            slider(viewModel.volume)
            label(getString("settings_volume_note"))
        }
    }
}