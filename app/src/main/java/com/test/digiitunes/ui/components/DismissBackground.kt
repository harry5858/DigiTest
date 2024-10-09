package com.test.digiitunes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DismissBackground(
    isSaved: Boolean,
    dismissState: SwipeToDismissBoxState
) {
    val color = when (dismissState.dismissDirection) {
        EndToStart -> if (isSaved) Color.Red else Color.Green
        StartToEnd -> Color.Transparent
        Settled -> Color.Transparent
    }
    Row(
       modifier = Modifier
           .fillMaxSize()
           .clip(RoundedCornerShape(8.dp))
           .background(color)
           .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier)
        Icon(
            imageVector = if (isSaved) Icons.Default.Delete else Icons.Default.Add,
            contentDescription = if (isSaved) "delete" else "save"
        )
    }
}