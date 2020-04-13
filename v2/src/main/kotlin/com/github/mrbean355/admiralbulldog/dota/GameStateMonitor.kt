package com.github.mrbean355.admiralbulldog.dota

import com.github.mrbean355.admiralbulldog.events.SoundEvent
import com.github.mrbean355.admiralbulldog.events.random
import com.github.mrbean355.admiralbulldog.model.SoundBiteRepository
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import kotlin.reflect.full.createInstance

object GameStateMonitor {
    private val soundBiteRepository = SoundBiteRepository()
    private val coroutineScope = CoroutineScope(IO + Job())
    private val logger = LoggerFactory.getLogger(GameStateMonitor::class.java)
    private val server = createServer()
    private var onUpdate: (() -> Unit)? = null

    private val soundEvents = mutableSetOf<SoundEvent>()
    private var previousState: GameState? = null

    fun onFirstUpdate(onUpdate: () -> Unit) {
        this.onUpdate = onUpdate
    }

    fun start() {
        coroutineScope.launch {
            server.start(wait = true)
        }
    }

    fun stop() {
        server.stop(gracePeriodMillis = 0, timeoutMillis = 0)
        coroutineScope.cancel()
    }

    private fun processNewGameState(currentState: GameState) = GlobalScope.launch {
        val previousMatchId = previousState?.map?.matchId
        val currentMatchId = currentState.map?.matchId

        if (currentMatchId != previousMatchId) {
            previousState = null
            soundEvents.clear()
            soundEvents.addAll(soundBiteRepository.getAllSoundEventTypes().map { it.createInstance() })
        }

        val previousState = previousState
        if (previousState != null && previousState.hasValidProperties() && currentState.hasValidProperties() && currentState.map?.paused == false) {
            soundEvents.forEach { soundEvent ->
                soundEvent.playIfNecessary(previousState, currentState)
            }
        }
        withContext(Main) {
            onUpdate?.let {
                it()
                onUpdate = null
            }
        }
        this@GameStateMonitor.previousState = currentState
    }

    private fun SoundEvent.playIfNecessary(previousState: GameState, currentState: GameState) {
        if (didOccur(previousState, currentState)) {
            val chance = soundBiteRepository.getSoundEventChance(this::class) / 100.0
            if (random.nextFloat() < chance) {
                val choices = soundBiteRepository.getSelectedSoundBites(this::class)
                if (choices.isNotEmpty()) {
                    choices.random().play()
                }
            }
        }
    }

    private fun createServer(): ApplicationEngine {
        return embeddedServer(Netty, port = 12345) {
            install(ContentNegotiation) {
                gson()
            }
            routing {
                post {
                    runCatching { call.receive<GameState>() }
                            .onSuccess { processNewGameState(it) }
                            .onFailure { logger.error("Error receiving game state update", it) }
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }
}

private fun GameState.hasValidProperties(): Boolean {
    return null !in listOf(map, player, hero, items)
}