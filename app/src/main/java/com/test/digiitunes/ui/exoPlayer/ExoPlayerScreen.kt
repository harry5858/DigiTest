package com.test.digiitunes.ui.exoPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.test.digiitunes.ui.components.ExoPlayerView

@Composable
fun ExoPlayerScreen(url: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
    ) {
        ExoPlayerView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            url = url
        )
    }
}