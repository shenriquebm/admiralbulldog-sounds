package com.github.mrbean355.admiralbulldog.model

import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

private val players = CopyOnWriteArrayList<MediaPlayer>()

class SoundBite(directory: String, fileName: String) {
    private val path = "$directory/$fileName"
    val name = fileName.substringBeforeLast('.')

    // TODO: Make volume configurable.
    fun play() {
        val media = Media(File(path).toURI().toString())
        MediaPlayer(media).apply {
            this.volume = 0.2
            onEndOfMedia = Runnable {
                dispose()
                players.remove(this)
            }
            players += this
            play()
        }
    }

    override fun toString(): String {
        return "SoundBite(path='$path', name='$name')"
    }
}
