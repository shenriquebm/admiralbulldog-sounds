package com.github.mrbean355.admiralbulldog.config

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.model.SoundBite
import com.google.gson.GsonBuilder
import java.io.File

object AppConfig {
    private val file = File("config2.json")
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val config: Config

    init {
        config = if (file.exists()) {
            gson.fromJson(file.readText(), Config::class.java)
        } else {
            Config().also {
                file.writeText(gson.toJson(it))
            }
        }
    }

    fun getVolume(): Double {
        return config.volume
    }

    fun setVolume(volume: Double) {
        config.volume = volume
        save()
    }

    fun isEventEnabled(type: SoundEventType): Boolean {
        return type.asEvent().enabled
    }

    fun setEventEnabled(type: SoundEventType, enabled: Boolean) {
        type.asEvent().enabled = enabled
        save()
    }

    fun getEventChance(type: SoundEventType): Double {
        return type.asEvent().chance
    }

    fun setEventChance(type: SoundEventType, chance: Double) {
        type.asEvent().chance = chance
        save()
    }

    fun isBiteEnabled(type: SoundEventType, bite: SoundBite): Boolean {
        return bite.name in type.asEvent().sounds
    }

    fun setBiteEnabled(type: SoundEventType, bite: SoundBite, enabled: Boolean) {
        if (enabled) {
            type.asEvent().sounds += bite.name
        } else {
            type.asEvent().sounds -= bite.name
        }
        save()
    }

    private fun SoundEventType.asEvent(): Event {
        var event = config.events[key]
        if (event == null) {
            event = Event()
            config.events[key] = event
            save()
        }
        return event
    }

    private fun save() {
        file.writeText(gson.toJson(config))
    }

    private class Config(
            var volume: Double = 20.0,
            val events: MutableMap<String, Event> = mutableMapOf()
    )

    private data class Event(
            var enabled: Boolean = false,
            var chance: Double = 50.0,
            val sounds: MutableSet<String> = mutableSetOf()
    )

    private inline val SoundEventType.key: String
        get() = simpleName!!
}