package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState

class OnSmoked : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        return !previous.hero!!.smoked && current.hero!!.smoked
    }
}
