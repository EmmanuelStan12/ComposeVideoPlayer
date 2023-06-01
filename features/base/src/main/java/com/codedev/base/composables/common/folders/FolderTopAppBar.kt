package com.codedev.home.common.folders

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import com.codedev.base.TopAppBarActionButton
import com.codedev.ui_base_lib.ColorBlackText

@Composable
fun FolderTopAppBar(
    onNavigate: () -> Unit
) {

    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = { Text(text = "Folders") },
        actions = {
            // search icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search"
            ) {

            }

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
                // show the drop down menu
                dropDownMenuExpanded = true
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}