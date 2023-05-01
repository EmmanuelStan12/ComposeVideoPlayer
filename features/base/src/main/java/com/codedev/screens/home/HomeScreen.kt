package com.codedev.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.ui.graphics.vector.ImageVector
import com.codedev.base.R

sealed class HomeScreen(
    val title: String,
    val icon: ImageVector? = null,
    @DrawableRes val iconRes: Int? = null,
    val route: String
) {

    object Folders: HomeScreen("Folders", icon = Icons.Filled.Folder, route = "composable.folder")

    object Offers: HomeScreen("Videos", icon = Icons.Outlined.VideoFile, route = "composable.offer")

}

val bottomBarItems = listOf(
    HomeScreen.Folders,
    HomeScreen.Offers
)