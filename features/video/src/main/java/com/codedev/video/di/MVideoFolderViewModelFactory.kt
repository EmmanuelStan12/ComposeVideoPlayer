package com.codedev.video.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.video.composables.folders.VideoFolderViewModel
import javax.inject.Provider

class MVideoFolderViewModelFactory(
        private val viewModels: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>,
    ) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(VideoFolderViewModel::class.java) ->
                viewModels[VideoFolderViewModel::class.java] as T

            else -> throw IllegalArgumentException("please add class to MViewModelFactory before trying to instantiate it")
        }
    }
}