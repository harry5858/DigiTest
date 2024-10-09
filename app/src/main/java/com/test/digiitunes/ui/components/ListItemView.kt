package com.test.digiitunes.ui.components

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.test.digiitunes.domain.model.SearchResultUiModel

@Composable
fun ListItemView(
    modifier: Modifier = Modifier,
    isSaved: Boolean,
    searchResultUiModel: SearchResultUiModel,
    onSwipe: (SearchResultUiModel) -> Unit,
    onPreviewClick: () -> Unit
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(searchResultUiModel)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                EndToStart -> {
                    onSwipe(currentItem)
                    val toastText = if (isSaved) "${currentItem.trackName} is Delete" else "Result saved ${currentItem.trackName}"
                    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                    return@rememberSwipeToDismissBoxState false
                }
                StartToEnd -> {
                    return@rememberSwipeToDismissBoxState false
                }
                Settled -> return@rememberSwipeToDismissBoxState false
            }
        },
        positionalThreshold = { it * .25f }
    )
    SwipeToDismissBox(
        modifier = modifier,
        state = dismissState,
        backgroundContent = { DismissBackground(isSaved, dismissState) },
        content = {
            SearchResultView(
                searchResultUiModel = searchResultUiModel,
                onPreviewClick = onPreviewClick
            )
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchResultView(
    searchResultUiModel: SearchResultUiModel,
    onPreviewClick: () -> Unit
) {
    val artworkUrl = searchResultUiModel.artworkUrl100 ?: searchResultUiModel.artworkUrl60
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Cyan)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        artworkUrl?.let {
            GlideImage(
                modifier = Modifier
                    .size(64.dp),
                model = artworkUrl,
                contentDescription = "artwork image"
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color.Gray)
                    .padding(horizontal = 8.dp),
                text = searchResultUiModel.kind ?: "Unknown kind",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = searchResultUiModel.trackName ?: "Unknown track",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = searchResultUiModel.artistName ?: "Unknown artist",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        searchResultUiModel.previewUrl?.let {
            PreviewButton(
                modifier = Modifier.wrapContentSize(),
                onClick = onPreviewClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultViewPreview() {
    val test = SearchResultUiModel(
        artistId = 123,
        artistName = "name",
        kind = "kind",
        trackName = "track Name",
    )
    SearchResultView(
        searchResultUiModel = test,
        onPreviewClick = {}
    )
}