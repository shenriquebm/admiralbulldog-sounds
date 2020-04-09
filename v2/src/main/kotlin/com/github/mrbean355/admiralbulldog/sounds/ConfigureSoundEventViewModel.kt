package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import com.github.mrbean355.admiralbulldog.resources.getString
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import tornadofx.ViewModel

class ConfigureSoundEventViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()
    private val type by param<SoundEventType>()

    val title: StringProperty = SimpleStringProperty(type.friendlyName)
    val description: StringProperty = SimpleStringProperty(type.description)
    val isEnabled = soundBiteRepository.getSoundEventEnabledProperty(type)
    val chance = soundBiteRepository.getSoundEventChanceProperty(type)
    val soundBitesChosen = SimpleStringProperty(getString("event_sound_bites_chosen_many", 0))
}