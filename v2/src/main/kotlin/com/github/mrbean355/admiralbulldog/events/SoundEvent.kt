package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState
import kotlin.reflect.KClass

typealias SoundEventType = KClass<out SoundEvent>

interface SoundEvent {

    /**
     * Return `true` if the event has happened.
     * This does not guarantee that a sound will play. The user can customise the chance for a sound to play.
     */
    fun didOccur(previous: GameState, current: GameState): Boolean
}
