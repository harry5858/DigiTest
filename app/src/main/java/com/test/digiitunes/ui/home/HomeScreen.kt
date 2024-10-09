package com.test.digiitunes.ui.home

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.test.digiitunes.domain.model.Kind
import com.test.digiitunes.ui.base.BaseScreen
import com.test.digiitunes.ui.components.ListItemView
import com.test.digiitunes.ui.components.SearchBar
import com.test.digiitunes.ui.navigation.Screen
import com.test.digiitunes.ui.util.audioPlayerSetup
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(navController: NavHostController) {
    val vm: HomeViewModel = koinViewModel()
    val uiState = vm.uiState.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    LaunchedEffect(key1 = vm.error.value) {
        if (vm.error.value != null) {
            Toast.makeText(context, vm.error.value?.message, Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(vm.toastMessage.value) {
        if (vm.toastMessage.value != null) {
            Toast.makeText(context, "${vm.toastMessage.value} is saved.", Toast.LENGTH_SHORT).show()
        }
    }
    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.reset()
        }
    }
    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        isLoading = vm.loading.value
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = uiState.query,
                onValueChange = { vm.onQueryChange(it) },
                onCancelClick = { vm.onQueryChange("") },
                onSearchClick = { vm.onSearchClick() }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.searchResults) {
                    ListItemView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        searchResultUiModel = it,
                        isSaved = false,
                        onSwipe = { item -> vm.onSave(item) },
                        onPreviewClick = {
                            it.previewUrl?.let { safeUrl ->
                                when (it.kind) {
                                    Kind.COACHED_AUDIO.text, Kind.PODCAST.text, Kind.PODCAST_EPISODE.text, Kind.SONG.text, Kind.ALBUM.text -> {
                                        audioPlayerSetup(context, mediaPlayer, safeUrl, it.trackName)
                                    }
                                    Kind.FEATURE_MOVIE.text, Kind.MUSIC_VIDEO.text, Kind.TV_EPISODE.text -> {
                                        if (mediaPlayer.isPlaying) {
                                            mediaPlayer.stop()
                                        }
                                        val encodedUrl = URLEncoder.encode(safeUrl, StandardCharsets.UTF_8.toString())
                                        navController.navigate(route = "${Screen.ExoPlayerScreen.route}/{url}".replace(
                                            oldValue = "{url}",
                                            newValue = encodedUrl
                                        ) )
                                    }
                                    else -> {
                                        // TODO to cover unknown kind
                                        audioPlayerSetup(context, mediaPlayer, safeUrl, it.trackName)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

