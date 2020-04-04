package com.github.mrbean355.admiralbulldog.model

import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.google.gson.GsonBuilder
import java.io.File

object SoundBiteCache {
    private const val FILE_NAME = "config2.json"

    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val config: Config

    init {
        val json = File(FILE_NAME).readText()
        config = if (json.isEmpty()) {
            Config(mutableMapOf())
        } else {
            gson.fromJson(json, Config::class.java)
        }
        sync()
    }

    fun isEventEnabled(eventType: SoundEventType): Boolean {
        return config.events[eventType.key] ?: false
    }

    fun setEventEnabled(eventType: SoundEventType, enabled: Boolean) {
        config.events[eventType.key] = enabled
        sync()
    }

    private fun sync() {
        File(FILE_NAME).writeText(gson.toJson(config))
    }

    private class Config(
            val events: MutableMap<String, Boolean>
    )

    private inline val SoundEventType.key: String
        get() = simpleName!!
}