package com.github.mrbean355.admiralbulldog.events

import com.github.mrbean355.admiralbulldog.dota.GameState
import com.github.mrbean355.admiralbulldog.dota.Item
import com.github.mrbean355.admiralbulldog.dota.Items

private const val ITEM_HAND_OF_MIDAS = "item_hand_of_midas"

class OnMidasReady : SoundEvent {

    override fun didOccur(previous: GameState, current: GameState): Boolean {
        return previous.items.hasMidasOnCooldown() && current.items.hasMidasOffCooldown()
    }

    private fun Items?.hasMidasOnCooldown(): Boolean {
        if (this == null) {
            return false
        }
        return slot0.isMidasOnCooldown() || slot1.isMidasOnCooldown() || slot2.isMidasOnCooldown() ||
                slot3.isMidasOnCooldown() || slot4.isMidasOnCooldown() || slot5.isMidasOnCooldown()
    }

    private fun Items?.hasMidasOffCooldown(): Boolean {
        if (this == null) {
            return false
        }
        return slot0.isMidasOffCooldown() || slot1.isMidasOffCooldown() || slot2.isMidasOffCooldown() ||
                slot3.isMidasOffCooldown() || slot4.isMidasOffCooldown() || slot5.isMidasOffCooldown()
    }

    private fun Item?.isMidasOffCooldown(): Boolean {
        return this != null && name == ITEM_HAND_OF_MIDAS && cooldown.compareTo(0f) == 0
    }

    private fun Item?.isMidasOnCooldown(): Boolean {
        return this != null && name == ITEM_HAND_OF_MIDAS && cooldown.compareTo(0f) > 0
    }
}
