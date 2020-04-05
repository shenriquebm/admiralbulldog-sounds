package com.github.mrbean355.admiralbulldog.model

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.google.gson.GsonBuilder
import java.io.File

object SoundBiteCache {
    private const val CONFIG_FILE = "config2.json"

    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val config: Config

    init {
        val json = File(CONFIG_FILE).readText()
        config = if (json.isEmpty()) {
            Config(mutableMapOf())
        } else {
            gson.fromJson(json, Config::class.java)
        }
        sync()
    }

    fun isEventEnabled(eventType: SoundEventType): Boolean {
        return getEvent(eventType).enabled
    }

    fun setEventEnabled(eventType: SoundEventType, enabled: Boolean) {
        getEvent(eventType).enabled = enabled
        sync()
    }

    fun getEventChance(eventType: SoundEventType): Double {
        return getEvent(eventType).chance
    }

    fun setEventChance(eventType: SoundEventType, chance: Double) {
        getEvent(eventType).chance = chance
        sync()
    }

    private fun getEvent(eventType: SoundEventType): Event {
        return config.events.getOrPut(eventType.key) { Event(false, 50.0) }
    }

    private fun sync() {
        File(CONFIG_FILE).writeText(gson.toJson(config))
    }

    private class Config(
            val events: MutableMap<String, Event>
    )

    private class Event(
            var enabled: Boolean,
            var chance: Double
    )

    private inline val SoundEventType.key: String
        get() = simpleName!!
}