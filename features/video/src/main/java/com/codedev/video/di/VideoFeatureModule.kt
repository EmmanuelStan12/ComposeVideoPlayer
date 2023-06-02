package com.codedev.video.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.base._di.ViewModelKey
import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.data_lib.repositories.interfaces.IVideoRepository
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import com.codedev.video.composables.video_player.VideoPlayerViewModel
import com.codedev.video.VideoFeatureApi
import com.codedev.video.VideoFeatureImpl
import com.codedev.video.composables.folders.VideoFolderViewModel
import com.codedev.video.composables.video_list.VideoListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
object VideoFeatureModule {

    @Provides
    @VideoFeatureScope
    fun provideVideoFeatureApi(): VideoFeatureApi {
        return VideoFeatureImpl()
    }

    @Provides
    @VideoFeatureScope
    fun provideVideoPlayerViewModelFactory(
        map: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): MVideoPlayerViewModelFactory = MVideoPlayerViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(VideoPlayerViewModel::class)
    @VideoFeatureScope
    fun provideVideoPlayerViewModel(
        viewModelFactory: MVideoPlayerViewModelFactory,
        activity: AppCompatActivity
    ): VideoPlayerViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[VideoPlayerViewModel::class.java]


    @Provides
    @VideoFeatureScope
    fun provideVideoListViewModelFactory(
        map: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): MVideoListViewModelFactory = MVideoListViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(VideoListViewModel::class)
    @VideoFeatureScope
    fun provideVideoListViewModel(
        viewModelFactory: MVideoListViewModelFactory,
        activity: AppCompatActivity
    ): VideoListViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[VideoListViewModel::class.java]

    @Provides
    @VideoFeatureScope
    fun provideVideoFolderViewModelFactory(
        map: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): MVideoFolderViewModelFactory = MVideoFolderViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(VideoFolderViewModel::class)
    @VideoFeatureScope
    fun provideVideoFolderViewModel(
        viewModelFactory: MVideoFolderViewModelFactory,
        activity: AppCompatActivity
    ): VideoFolderViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[VideoFolderViewModel::class.java]


}