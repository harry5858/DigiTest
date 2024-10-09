package com.test.digiitunes.ui.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.widget.Toast

fun audioPlayerSetup(
    context: Context,
    mediaPlayer: MediaPlayer,
    url: String,
    trackName: String?
) {
    try {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.apply {
            reset()
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener { mp ->
                mp.start()
                Toast.makeText(context, "Playing $trackName", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error: $e", Toast.LENGTH_SHORT).show()
    }
}