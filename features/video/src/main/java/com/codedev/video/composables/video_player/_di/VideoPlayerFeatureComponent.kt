package com.codedev.home.video_player._di

import com.codedev.base._di.BaseFeatureComponent
import com.codedev.base._di.BaseFeatureModule
import com.codedev.home.HomeActivity
import com.codedev.home.video_player.VideoPlayerActivity
import com.codedev.home.video_player.VideoPlayerViewModel
import com.codedev.home.videos.VideoListViewModel
import dagger.Component

@Component(
    modules = [VideoPlayerModule::class],
    dependencies = [BaseFeatureComponent::class]
)
@VideoPlayerScope
interface VideoPlayerFeatureComponent {

    companion object {
        @JvmStatic
        fun getInitialBuilder(): DaggerVideoPlayerFeatureComponent.Builder {
            return DaggerVideoPlayerFeatureComponent.builder()
                .baseFeatureComponent(BaseFeatureComponent.getInstance())
        }
    }

    fun inject(activity: VideoPlayerActivity)

    fun getVideoPlayerViewModel(): VideoPlayerViewModel
}