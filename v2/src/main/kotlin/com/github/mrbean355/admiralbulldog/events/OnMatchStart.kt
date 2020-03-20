package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState
import com.github.mrbean355.admiralbulldog.dota.MatchState

/** Don't play the sound this many seconds after the match starts. */
private const val CUTOFF_TIME = 5

/** Plays a sound just after the clock hits 0. */
class OnMatchStart : SoundEvent {
    private var played = false

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        if (!played && current.map!!.gameState == MatchState.DOTA_GAMERULES_STATE_GAME_IN_PROGRESS && current.map.clockTime < CUTOFF_TIME) {
            played = true
            return true
        }
        return false
    }
}
