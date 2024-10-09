package com.test.digiitunes.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.digiitunes.ui.util.noIndicationClick

@Composable
fun PreviewButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Preview")
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonPreview() {
    PreviewButton(
        modifier = Modifier
            .wrapContentSize(),
        onClick = {}
    )
}