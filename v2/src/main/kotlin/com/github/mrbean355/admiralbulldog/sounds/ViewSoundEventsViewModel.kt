package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import tornadofx.ViewModel

class ViewSoundEventsViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()

    val soundEventTypes = soundBiteRepository.getAllSoundEventTypes()
}
