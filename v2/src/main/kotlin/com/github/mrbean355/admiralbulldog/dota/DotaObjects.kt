@file:Suppress("SpellCheckingInspection", "unused")

package com.github.mrbean355.admiralbulldog.dota

import com.google.gson.annotations.SerializedName

data class GameState(
        val provider: Provider,
        val map: DotaMap?,
        val player: Player?,
        val hero: Hero?,
        val abilities: Any?,
        val items: Items?
)

data class Provider(
        val name: String,
        @SerializedName("appid")
        val appId: Int,
        val version: Int,
        val timestamp: Long
)

data class DotaMap(
        val name: String,
        @SerializedName("matchid")
        val matchId: String,
        @SerializedName("game_time")
        val gameTime: Long,
        @SerializedName("clock_time")
        val clockTime: Long,
        val daytime: Boolean,
        @SerializedName("nightstalker_night")
        val nightstalkerNight: Boolean,
        @SerializedName("game_state")
        val gameState: MatchState,
        val paused: Boolean,
        @SerializedName("win_team")
        val winningTeam: String,
        @SerializedName("customgamename")
        val customGameName: String,
        @SerializedName("ward_purchase_cooldown")
        val wardPurchaseCooldown: Float?
)

enum class MatchState {
    DOTA_GAMERULES_STATE_DISCONNECT,
    DOTA_GAMERULES_STATE_GAME_IN_PROGRESS,
    DOTA_GAMERULES_STATE_HERO_SELECTION,
    DOTA_GAMERULES_STATE_INIT,
    DOTA_GAMERULES_STATE_LAST,
    DOTA_GAMERULES_STATE_POST_GAME,
    DOTA_GAMERULES_STATE_PRE_GAME,
    DOTA_GAMERULES_STATE_STRATEGY_TIME,
    DOTA_GAMERULES_STATE_WAIT_FOR_PLAYERS_TO_LOAD,
    DOTA_GAMERULES_STATE_CUSTOM_GAME_SETUP
}

data class Player(
        @SerializedName("steamid")
        val steamId: String,
        val name: String,
        val activity: String,
        val kills: Int,
        val deaths: Int,
        val assists: Int,
        @SerializedName("last_hits")
        val lastHits: Int,
        val denies: Int,
        @SerializedName("kill_streak")
        val killStreak: Int,
        @SerializedName("commands_issued")
        val commandsIssued: Long,
        @SerializedName("kill_list")
        val killList: Any,
        @SerializedName("team_name")
        val teamName: String,
        val gold: Int,
        @SerializedName("gold_reliable")
        val goldReliable: Int,
        @SerializedName("gold_unreliable")
        val goldUnreliable: Int,
        @SerializedName("gold_from_hero_kills")
        val goldFromHeroKills: Int,
        @SerializedName("gold_from_creep_kills")
        val goldFromCreepKills: Int,
        @SerializedName("gold_from_income")
        val goldFromIncome: Int,
        @SerializedName("gold_from_shared")
        val goldFromShared: Int,
        val gpm: Float,
        val xpm: Float
)

data class Hero(
        @SerializedName("xpos")
        val xPos: Float,
        @SerializedName("ypos")
        val yPos: Float,
        val id: Int,
        val name: String,
        val level: Int,
        val alive: Boolean,
        @SerializedName("respawn_seconds")
        val respawnSeconds: Int,
        @SerializedName("buyback_cost")
        val buybackCost: Int,
        @SerializedName("buyback_cooldown")
        val buybackCooldown: Float,
        val health: Float,
        @SerializedName("max_health")
        val maxHealth: Float,
        @SerializedName("health_percent")
        val healthPercent: Float,
        val mana: Float,
        @SerializedName("max_mana")
        val maxMana: Float,
        @SerializedName("mana_percent")
        val manaPercent: Float,
        val silenced: Boolean,
        val stunned: Boolean,
        val disarmed: Boolean,
        @SerializedName("magicimmune")
        val magicImmune: Boolean,
        val hexed: Boolean,
        val muted: Boolean,
        val smoked: Boolean,
        @SerializedName("hasDebuff")
        val has_debuff: Boolean,
        // todo: special parsing?
        val talent_1: Boolean,
        val talent_2: Boolean,
        val talent_3: Boolean,
        val talent_4: Boolean,
        val talent_5: Boolean,
        val talent_6: Boolean,
        val talent_7: Boolean,
        val talent_8: Boolean,
        @SerializedName("break")
        val broken: Boolean
)

data class Items(
        // todo: special parsing?
        val slot0: Item,
        val slot1: Item,
        val slot2: Item,
        val slot3: Item,
        val slot4: Item,
        val slot5: Item,
        val slot6: Item,
        val slot7: Item,
        val slot8: Item,
        val stash0: Item,
        val stash1: Item,
        val stash2: Item,
        val stash3: Item,
        val stash4: Item,
        val stash5: Item
)

data class Item(
        val name: String,
        val purchaser: Float,
        @SerializedName("can_cast")
        val canCast: Boolean,
        val cooldown: Float,
        val passive: Boolean
)
