package com.codedev.home._di

import com.codedev.base._di.BaseFeatureComponent
import com.codedev.base._di.BaseFeatureModule
import com.codedev.home.HomeActivity
import com.codedev.home.video_player.VideoPlayerViewModel
import com.codedev.home.videos.VideoListViewModel
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [BaseFeatureComponent::class]
)
@HomeScope
interface HomeFeatureComponent {

    companion object {
        @JvmStatic
        fun getInitialBuilder(): DaggerHomeFeatureComponent.Builder {
            return DaggerHomeFeatureComponent.builder()
                .baseFeatureComponent(BaseFeatureComponent.getInstance())
        }
    }

    fun inject(activity: HomeActivity)

    fun getVideoListViewModel(): VideoListViewModel

}