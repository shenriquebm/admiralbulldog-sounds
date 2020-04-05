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

    fun getEventEnabled(eventType: SoundEventType): BooleanProperty {
        return eventType.booleanBinding(SoundBiteCache::isEventEnabled, SoundBiteCache::setEventEnabled)
    }

    fun getEventChance(eventType: SoundEventType): DoubleProperty {
        return eventType.doubleBinding(SoundBiteCache::getEventChance, SoundBiteCache::setEventChance)
    }

    private fun <T> T.booleanBinding(getter: (T) -> Boolean, setter: (T, Boolean) -> Unit): BooleanProperty {
        return SimpleBooleanProperty(getter(this)).apply {
            addListener { _, _, newValue -> setter(this@booleanBinding, newValue) }
        }
    }

    private fun <T> T.doubleBinding(getter: (T) -> Double, setter: (T, Double) -> Unit): DoubleProperty {
        return SimpleDoubleProperty(getter(this)).apply {
            addListener { _, _, newValue -> setter(this@doubleBinding, newValue.toDouble()) }
        }
    }
}
