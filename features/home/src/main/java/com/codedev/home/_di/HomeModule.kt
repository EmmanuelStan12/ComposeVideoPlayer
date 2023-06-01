package com.codedev.home._di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.base._di.ViewModelKey
import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.data_lib.repositories.interfaces.IVideoRepository
import com.codedev.home.videos.VideoListViewModel
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import com.codedev.video.di.MVideoListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Provider

@Module
object HomeModule {

    @Provides
    @HomeScope
    fun provideVideoListViewModelFactory(
        map: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>
    ): MVideoListViewModelFactory = MVideoListViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(VideoListViewModel::class)
    @HomeScope
    fun provideVideoListViewModel(
        viewModelFactory: MVideoListViewModelFactory,
        activity: AppCompatActivity
    ): VideoListViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[VideoListViewModel::class.java]

    @Provides
    @HomeScope
    fun provideVideoRepository(
        videoDao: VideoDao,
        queryDao: QueryDao,
        dispatcher: CoroutineDispatcher
    ): IVideoRepository {
        return VideoRepositoryImpl(videoDao, queryDao, dispatcher)
    }
}