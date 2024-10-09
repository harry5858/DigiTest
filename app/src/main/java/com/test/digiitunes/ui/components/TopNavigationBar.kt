package com.test.digiitunes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.digiitunes.ui.util.noIndicationClick

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    canBack: Boolean,
    onBackClick: () -> Unit,
    onGoToSavedResultClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(vertical = 16.dp, horizontal = 24.dp),
    ) {
        if (canBack) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .noIndicationClick { onBackClick.invoke() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
        title?.let { safeTitle ->
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = safeTitle,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            )
        }
        if (!canBack) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .noIndicationClick { onGoToSavedResultClick.invoke() },
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun TopNavigationBarPreview() {
    TopNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = "title",
        canBack = true,
        onBackClick = {},
        onGoToSavedResultClick = {}
    )
}



@Preview
@Composable
fun TopNavigationBarOnlyWithTitlePreview() {
    TopNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        canBack = false,
        title = "title",
        onBackClick = {},
        onGoToSavedResultClick = {}
    )
}