package com.github.mrbean355.admiralbulldog.sounds

import com.github.mrbean355.admiralbulldog.events.OnBountyRunesSpawn
import com.github.mrbean355.admiralbulldog.events.OnDeath
import com.github.mrbean355.admiralbulldog.events.OnDefeat
import com.github.mrbean355.admiralbulldog.events.OnHeal
import com.github.mrbean355.admiralbulldog.events.OnKill
import com.github.mrbean355.admiralbulldog.events.OnMatchStart
import com.github.mrbean355.admiralbulldog.events.OnMidasReady
import com.github.mrbean355.admiralbulldog.events.OnRespawn
import com.github.mrbean355.admiralbulldog.events.OnSmoked
import com.github.mrbean355.admiralbulldog.events.OnVictory
import com.github.mrbean355.admiralbulldog.events.Periodically
import com.github.mrbean355.admiralbulldog.events.SoundEventType
import com.github.mrbean355.admiralbulldog.resources.getString

val SoundEventType.friendlyName: String
    get() {
        return getString(when (this) {
            OnBountyRunesSpawn::class -> "event_name_bounty_runes"
            OnDeath::class -> "event_name_death"
            OnDefeat::class -> "event_name_defeat"
            OnHeal::class -> "event_name_heal"
            OnKill::class -> "event_name_kill"
            OnMatchStart::class -> "event_name_match_start"
            OnMidasReady::class -> "event_name_midas_ready"
            OnRespawn::class -> "event_name_respawn"
            OnSmoked::class -> "event_name_smoked"
            OnVictory::class -> "event_name_victory"
            Periodically::class -> "event_name_periodically"
            else -> throw IllegalArgumentException("Unexpected sound event: $this}")
        })
    }

val SoundEventType.description: String
    get() {
        return getString(when (this) {
            OnBountyRunesSpawn::class -> "event_desc_bounty_runes"
            OnDeath::class -> "event_desc_death"
            OnDefeat::class -> "event_desc_defeat"
            OnHeal::class -> "event_desc_heal"
            OnKill::class -> "event_desc_kill"
            OnMatchStart::class -> "event_desc_match_start"
            OnMidasReady::class -> "event_desc_midas_ready"
            OnRespawn::class -> "event_desc_respawn"
            OnSmoked::class -> "event_desc_smoked"
            OnVictory::class -> "event_desc_victory"
            Periodically::class -> "event_desc_periodically"
            else -> throw IllegalArgumentException("Unexpected sound event: $this}")
        })
    }
