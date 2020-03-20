package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState
import java.util.concurrent.TimeUnit

/** Minimum time in between sounds. */
private val MIN_QUIET_TIME = TimeUnit.MINUTES.toSeconds(5)

/** Maximum time in between sounds. */
private val MAX_QUIET_TIME = TimeUnit.MINUTES.toSeconds(15)

class Periodically : SoundEvent {
    private var nextPlayClockTime = UNINITIALISED

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        if (nextPlayClockTime == UNINITIALISED) {
            nextPlayClockTime = current.map!!.clockTime + random.nextLong(MIN_QUIET_TIME, MAX_QUIET_TIME)
        } else if (current.map!!.clockTime >= nextPlayClockTime) {
            nextPlayClockTime += random.nextLong(MIN_QUIET_TIME, MAX_QUIET_TIME)
            return true
        }
        return false
    }
}
