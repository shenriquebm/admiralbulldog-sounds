package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

class ChooseSoundEventsViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()
    private val names: MutableMap<SoundEventType, SimpleStringProperty> = mutableMapOf()

    val soundEventTypes = soundBiteRepository.getAllSoundEventTypes()

    fun getName(event: SoundEventType): SimpleStringProperty {
        val prop = names.getOrPut(event) { SimpleStringProperty() }
        prop.set(event.friendlyName)
        return prop
    }
}
