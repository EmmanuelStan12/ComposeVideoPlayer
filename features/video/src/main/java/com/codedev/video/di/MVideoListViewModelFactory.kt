package com.codedev.video.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.home.video_player.VideoPlayerViewModel
import com.codedev.home.videos.VideoListViewModel
import javax.inject.Provider

class MVideoListViewModelFactory(
        private val viewModels: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>,
    ) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(VideoPlayerViewModel::class.java) ->
                viewModels[VideoListViewModel::class.java] as T

            else -> throw IllegalArgumentException("please add class to MViewModelFactory before trying to instantiate it")
        }
    }
}