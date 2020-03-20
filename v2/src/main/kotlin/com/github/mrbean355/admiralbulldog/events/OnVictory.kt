package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState

class OnVictory : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        return current.map!!.winningTeam != TEAM_NONE && previous.map!!.winningTeam == TEAM_NONE
                && current.player!!.teamName == current.map.winningTeam
    }
}
