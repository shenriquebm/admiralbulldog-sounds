package com.github.mrbean355.admiralbulldog.dota

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

object GameStateMonitor {
    private val coroutineScope = CoroutineScope(IO + Job())
    private val logger = LoggerFactory.getLogger(GameStateMonitor::class.java)
    private val server = createServer()
    private var onUpdate: (() -> Unit)? = null

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

    private fun processNewGameState(state: GameState) = GlobalScope.launch {
        // TODO: Implement me!
        withContext(Main) {
            if (onUpdate != null) {
                onUpdate?.invoke()
                onUpdate = null
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