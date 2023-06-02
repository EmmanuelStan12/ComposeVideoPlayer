package com.codedev.home._di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.home.HomeViewModel
import com.codedev.video.composables.video_player.VideoPlayerViewModel
import javax.inject.Provider

class MHomeViewModelFactory (
    private val viewModels: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(VideoPlayerViewModel::class.java) ->
                viewModels[HomeViewModel::class.java] as T

            else -> throw IllegalArgumentException("please add class to MViewModelFactory before trying to instantiate it")
        }
    }
}