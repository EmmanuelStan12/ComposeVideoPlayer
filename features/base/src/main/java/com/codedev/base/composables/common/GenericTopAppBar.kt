package com.codedev.home.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import com.codedev.base.TopAppBarActionButton
import com.codedev.ui_base_lib.ColorBlackText

enum class TopAppBarState {
    SEARCH, NORMAL
}

@Composable
fun GenericTopAppBar(
    title: String,
    onSearchIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = ColorBlackText)
            }
        },
        actions = {
            // search icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search",
                onClick = onSearchIconClicked
            )

            // lock icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Grid3x3,
                description = "Grid"
            ) {

            }

            // options icon (vertical dots)
            TopAppBarActionButton(
                imageVector = Icons.Outlined.MoreVert,
                description = "Options"
            ) {

            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}