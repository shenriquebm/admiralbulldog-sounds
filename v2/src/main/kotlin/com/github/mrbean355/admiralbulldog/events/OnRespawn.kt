package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState

class OnRespawn : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        return previous.hero!!.respawnSeconds > 0 && current.hero!!.respawnSeconds == 0
    }
}
