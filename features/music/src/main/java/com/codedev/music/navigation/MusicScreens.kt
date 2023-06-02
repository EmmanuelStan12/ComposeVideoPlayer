package com.codedev.music.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FeaturedPlayList
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MusicScreens(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object Folders: MusicScreens("Folders", icon = Icons.Filled.Folder, route = "composable.music.folder")

    object Offers: MusicScreens("Offers", icon = Icons.Outlined.VideoFile, route = "composable.music.offer")
    object Playlist: MusicScreens("Playlists", icon = Icons.Outlined.FeaturedPlayList, route = "composable.music.playlist")
    object Favorites: MusicScreens("Favorites", icon = Icons.Outlined.Favorite, route = "composable.music.playlist")

    object VideoList: MusicScreens("Videos", icon = Icons.Outlined.VideoFile, route = "composable.music.music_list/{folder}")

    object Downloads: MusicScreens("Downloads", icon = Icons.Outlined.Download, route = "composable.music.dowloads")

    companion object {
        val bottomBarItems = listOf<MusicScreens>(
            Folders,
            Offers
        )

        val drawerItems = listOf<MusicScreens>(
            Folders,
            Offers,
            Playlist,
            Favorites,
            Downloads
        )
    }

}