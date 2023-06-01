package com.codedev.home.video_player._di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.base._di.ViewModelKey
import com.codedev.home._di.VideoPlayerViewModelFactory
import com.codedev.home.video_player.VideoPlayerViewModel
import com.codedev.home.videos.VideoListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
object VideoPlayerModule {

    @Provides
    @VideoPlayerScope
    fun provideVideoPlayerViewModelFactory(
        map: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
    ): VideoPlayerViewModelFactory = VideoPlayerViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(VideoPlayerViewModel::class)
    @VideoPlayerScope
    fun provideVideoPlayerViewModel(
        viewModelFactory: VideoPlayerViewModelFactory,
        activity: AppCompatActivity
    ): VideoPlayerViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[VideoPlayerViewModel::class.java]

}