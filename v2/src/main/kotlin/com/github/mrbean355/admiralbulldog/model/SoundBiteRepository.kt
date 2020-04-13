package com.github.mrbean355.admiralbulldog.model

import com.github.mrbean355.admiralbulldog.config.AppConfig
import com.github.mrbean355.admiralbulldog.events.OnBountyRunesSpawn
import com.github.mrbean355.admiralbulldog.events.OnDeath
import com.github.mrbean355.admiralbulldog.events.OnDefeat
import com.github.mrbean355.admiralbulldog.events.OnHeal
import com.github.mrbean355.admiralbulldog.events.OnKill
import com.github.mrbean355.admiralbulldog.events.OnMatchStart
import com.github.mrbean355.admiralbulldog.events.OnMidasReady
import com.github.mrbean355.admiralbulldog.events.OnRespawn
import com.github.mrbean355.admiralbulldog.events.OnSmoked
import com.github.mrbean355.admiralbulldog.events.OnVictory
import com.github.mrbean355.admiralbulldog.events.Periodically
import com.github.mrbean355.admiralbulldog.events.SoundEventType
import javafx.beans.property.BooleanProperty
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import java.io.File

private const val SOUNDS_DIR = "sounds"

class SoundBiteRepository {
    private val allEventTypes = listOf(OnBountyRunesSpawn::class, OnDeath::class, OnDefeat::class, OnHeal::class, OnKill::class, OnMatchStart::class, OnMidasReady::class, OnRespawn::class, OnSmoked::class, OnVictory::class, Periodically::class)
    private val allSoundBites = File(SOUNDS_DIR).listFiles().orEmpty().map {
        SoundBite(SOUNDS_DIR, it.name)
    }

    fun getAllSoundEventTypes(): List<SoundEventType> {
        return allEventTypes
    }

    fun getSoundEventEnabledProperty(eventType: SoundEventType): BooleanProperty {
        return SimpleBooleanProperty(AppConfig.isEventEnabled(eventType)).apply {
            addListener { _, _, newValue -> AppConfig.setEventEnabled(eventType, newValue) }
        }
    }

    fun getVolumeProperty(): DoubleProperty {
        return SimpleDoubleProperty(AppConfig.getVolume()).apply {
            addListener { _, _, newValue -> AppConfig.setVolume(newValue.toDouble()) }
        }
    }

    fun getVolume(): Double {
        return AppConfig.getVolume()
    }

    fun getSoundEventChanceProperty(eventType: SoundEventType): DoubleProperty {
        return SimpleDoubleProperty(AppConfig.getEventChance(eventType)).apply {
            addListener { _, _, newValue -> AppConfig.setEventChance(eventType, newValue.toDouble()) }
        }
    }

    fun getSoundEventChance(eventType: SoundEventType): Double {
        return AppConfig.getEventChance(eventType)
    }

    fun getAllSoundBites(): Collection<SoundBite> {
        return allSoundBites
    }

    fun getSelectedSoundBites(eventType: SoundEventType): Collection<SoundBite> {
        return allSoundBites.filter {
            isSoundBiteEnabled(eventType, it)
        }
    }

    fun getSoundBiteEnabledProperty(eventType: SoundEventType, soundBite: SoundBite): BooleanProperty {
        return SimpleBooleanProperty(AppConfig.isBiteEnabled(eventType, soundBite)).apply {
            addListener { _, _, newValue -> AppConfig.setBiteEnabled(eventType, soundBite, newValue) }
        }
    }

    fun isSoundBiteEnabled(eventType: SoundEventType, soundBite: SoundBite): Boolean {
        return AppConfig.isBiteEnabled(eventType, soundBite)
    }
}
