package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.model.SoundBite
import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.transformation.SortedList
import tornadofx.ViewModel

class ChooseSoundBitesViewModel : ViewModel() {
    private val soundBiteRepository = SoundBiteRepository()
    private val type by param<SoundEventType>()
    private val allSoundBites = soundBiteRepository.getAllSoundBites()
    private val searchResults = FXCollections.observableArrayList(allSoundBites)

    val searchQuery = SimpleStringProperty()
    val soundBites = SortedList(searchResults, Comparator { lhs, rhs ->
        val lhsEnabled = soundBiteRepository.isSoundBiteEnabled(type, lhs)
        val rhsEnabled = soundBiteRepository.isSoundBiteEnabled(type, rhs)
        if (lhsEnabled == rhsEnabled) {
            lhs.name.compareTo(rhs.name)
        } else {
            if (lhsEnabled) -1 else 1
        }
    })

    init {
        searchQuery.addListener { _, _, newValue ->
            filter(newValue)
        }
    }

    fun getSoundBiteEnabled(soundBite: SoundBite): BooleanProperty {
        return soundBiteRepository.getSoundBiteEnabledProperty(type, soundBite)
    }

    fun getVolume(): Double = soundBiteRepository.getVolume()

    private fun filter(query: String) {
        searchResults.clear()
        if (query.isEmpty()) {
            searchResults.addAll(allSoundBites)
        } else {
            searchResults.addAll(allSoundBites.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            })
        }
    }
}