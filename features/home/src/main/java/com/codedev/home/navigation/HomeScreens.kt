package com.codedev.home.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FeaturedPlayList
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeScreens(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object Featured: HomeScreens("Featured", icon = Icons.Filled.Folder, route = "composable.home.featured")
    companion object {

        val drawerItems = listOf<HomeScreens>(
            Featured
        )
    }

}