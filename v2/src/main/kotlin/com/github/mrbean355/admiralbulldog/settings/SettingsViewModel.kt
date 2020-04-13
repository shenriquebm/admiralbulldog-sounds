package com.github.mrbean355.admiralbulldog.settings

import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import tornadofx.ViewModel

class SettingsViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()

    val volume = soundBiteRepository.getVolumeProperty()

}