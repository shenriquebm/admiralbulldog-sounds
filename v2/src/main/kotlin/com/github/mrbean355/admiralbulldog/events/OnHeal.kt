package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState

/** Must have healed at least this much percentage. */
private const val MIN_HP_PERCENTAGE = 5

/** Health required for max chance to play the sound. */
private const val MAX_HEAL = 500

/**
 * Play a sound when the hero is healed.
 *
 * The chance increases as the heal amount increases.
 * A heal amount of [MAX_HEAL] or more gives a 100% chance to play the sound.
 * The heal amount must be at least [MIN_HP_PERCENTAGE] percent of the hero's max HP.
 */
class OnHeal : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        if (previous.hero!!.health <= 0F) {
            // We get healed on respawn; ignore.
            return false
        }
        if (current.hero!!.healthPercent - previous.hero.healthPercent < MIN_HP_PERCENTAGE) {
            // Small heal; ignore.
            return false
        }
        return random.nextFloat() <= (current.hero.health - previous.hero.health) / MAX_HEAL
    }
}
