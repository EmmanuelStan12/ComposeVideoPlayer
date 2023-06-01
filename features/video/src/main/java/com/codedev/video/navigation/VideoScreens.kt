package com.codedev.video.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.ui.graphics.vector.ImageVector

sealed class VideoScreens(
    val title: String,
    val icon: ImageVector? = null,
    @DrawableRes val iconRes: Int? = null,
    val route: String
) {

    object Folders: VideoScreens("Folders", icon = Icons.Filled.Folder, route = "composable.video.folder")

    object Offers: VideoScreens("Videos", icon = Icons.Outlined.VideoFile, route = "composable.video.offer")

    object VideoList: VideoScreens("Videos", icon = null, route = "composable.video.video_list/{folder}")

    companion object {
        val bottomBarItems = listOf<VideoScreens>(
            Folders,
            Offers
        )
    }

}