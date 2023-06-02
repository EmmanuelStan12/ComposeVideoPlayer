package com.codedev.video.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FeaturedPlayList
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.ui.graphics.vector.ImageVector

sealed class VideoScreens(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object Folders: VideoScreens("Folders", icon = Icons.Filled.Folder, route = "composable.video.folder")

    object Offers: VideoScreens("Offers", icon = Icons.Outlined.VideoFile, route = "composable.video.offer")
    object Playlist: VideoScreens("Playlist", icon = Icons.Outlined.FeaturedPlayList, route = "composable.video.playlist")
    object Favorites: VideoScreens("Favorites", icon = Icons.Outlined.Favorite, route = "composable.video.playlist")

    object VideoList: VideoScreens("Videos", icon = Icons.Outlined.VideoFile, route = "composable.video.video_list/{folder}")

    companion object {
        val bottomBarItems = listOf<VideoScreens>(
            Folders,
            Offers
        )

        val drawerItems = listOf<VideoScreens>(
            Folders,
            Offers,
            Playlist,
            Favorites
        )
    }

}