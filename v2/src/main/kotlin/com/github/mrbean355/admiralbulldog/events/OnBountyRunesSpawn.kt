package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState
import com.github.mrbean355.admiralbulldog.dota.MatchState
import kotlin.math.ceil

/** How often (in seconds) the runes spawn. */
private const val HOW_OFTEN = 5 * 60L

/** Play a sound this many seconds before the bounty runes spawn. */
private const val WARNING_PERIOD = 15L

/** Plays a sound shortly before the bounty runes spawn. */
class OnBountyRunesSpawn : SoundEvent {
    private var nextPlayTime = UNINITIALISED

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        val gameState = current.map!!.gameState
        if (gameState != MatchState.DOTA_GAMERULES_STATE_PRE_GAME && gameState != MatchState.DOTA_GAMERULES_STATE_GAME_IN_PROGRESS) {
            // Don't play during the picking phase.
            return false
        }
        val currentTime = current.map.clockTime
        if (nextPlayTime == UNINITIALISED) {
            nextPlayTime = findNextPlayTime(currentTime)
        }
        if (currentTime >= nextPlayTime) {
            val diff = currentTime - nextPlayTime
            nextPlayTime = findNextPlayTime(currentTime + 10)
            if (diff <= WARNING_PERIOD) {
                return true
            }
        }
        return false
    }

    private fun findNextPlayTime(clockTime: Long): Long {
        val iteration = ceil((clockTime + WARNING_PERIOD) / HOW_OFTEN.toFloat()).toInt()
        val nextPlayTime = iteration * HOW_OFTEN - WARNING_PERIOD
        return nextPlayTime.coerceAtLeast(-WARNING_PERIOD)
    }
}
