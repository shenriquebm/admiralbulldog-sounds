package com.github.mrbean355.dota2.integration.assets

/** Deserialised `config.json` file. */
data class SoundEffectToggles(
        var onBountyRunesSpawn: List<String>?,
        var onDeath: List<String>?,
        var onDefeat: List<String>?,
        var onHeal: List<String>?,
        var onKill: List<String>?,
        var onMatchStart: List<String>?,
        var onMidasReady: List<String>?,
        var onSmoked: List<String>?,
        var onVictory: List<String>?,
        var periodically: List<String>?)
