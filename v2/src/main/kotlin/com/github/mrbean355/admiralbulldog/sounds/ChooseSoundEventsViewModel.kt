package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import javafx.beans.property.BooleanProperty
import tornadofx.ViewModel
import tornadofx.toObservable

class ChooseSoundEventsViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()

    val soundEventTypes = soundBiteRepository.getAllSoundEventTypes().toObservable()

    fun getProperty(eventType: SoundEventType): BooleanProperty {
        return soundBiteRepository.getEventEnabled(eventType)
    }
}
