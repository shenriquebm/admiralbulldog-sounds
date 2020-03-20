package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState

class OnKill : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        return current.player!!.kills > previous.player!!.kills
    }
}
