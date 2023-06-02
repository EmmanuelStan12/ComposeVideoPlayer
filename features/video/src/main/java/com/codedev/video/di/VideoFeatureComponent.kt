package com.codedev.video.di

import com.codedev.base._di.BaseFeatureComponent
import com.codedev.video.composables.video_player.VideoPlayerViewModel
import com.codedev.video.VideoFeatureApi
import com.codedev.video.composables.folders.VideoFolderViewModel
import com.codedev.video.composables.video_list.VideoListViewModel
import dagger.Component

@Component(
    modules = [VideoFeatureModule::class],
    dependencies = [BaseFeatureComponent::class]
)
@VideoFeatureScope
interface VideoFeatureComponent {
    fun getVideoFolderViewModel(): VideoFolderViewModel
    fun getVideoListViewModel(): VideoListViewModel
    fun getVideoPlayerViewModel(): VideoPlayerViewModel

}