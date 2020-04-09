package com.github.mrbean355.admiralbulldog.model

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

class SoundBiteRepository {

    fun getAllSoundEventTypes(): List<SoundEventType> {
        return listOf(
                OnBountyRunesSpawn::class,
                OnDeath::class,
                OnDefeat::class,
                OnHeal::class,
                OnKill::class,
                OnMatchStart::class,
                OnMidasReady::class,
                OnRespawn::class,
                OnSmoked::class,
                OnVictory::class,
                Periodically::class
        )
    }

    fun getSoundEventEnabledProperty(eventType: SoundEventType): BooleanProperty {
        return SimpleBooleanProperty(SoundBiteCache.isEventEnabled(eventType)).apply {
            addListener { _, _, newValue -> SoundBiteCache.setEventEnabled(eventType, newValue) }
        }
    }

    fun getSoundEventChanceProperty(eventType: SoundEventType): DoubleProperty {
        return SimpleDoubleProperty(SoundBiteCache.getEventChance(eventType)).apply {
            addListener { _, _, newValue -> SoundBiteCache.setEventChance(eventType, newValue.toDouble()) }
        }
    }

    fun getAllSoundBites(): Collection<SoundBite> {
        return SoundBiteCache.getAllSoundBites()
    }

    fun getSoundBiteEnabledProperty(eventType: SoundEventType, soundBite: SoundBite): BooleanProperty {
        return SimpleBooleanProperty(SoundBiteCache.isSoundBiteEnabled(eventType, soundBite)).apply {
            addListener { _, _, newValue -> SoundBiteCache.setSoundBiteEnabled(eventType, soundBite, newValue) }
        }
    }

    fun isSoundBiteEnabled(eventType: SoundEventType, soundBite: SoundBite): Boolean {
        return SoundBiteCache.isSoundBiteEnabled(eventType, soundBite)
    }
}
