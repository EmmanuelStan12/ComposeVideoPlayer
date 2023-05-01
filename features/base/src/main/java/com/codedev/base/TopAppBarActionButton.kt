package com.codedev.base

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.codedev.ui_base_lib.ColorBlackText

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            tint = ColorBlackText
            )
    }
}