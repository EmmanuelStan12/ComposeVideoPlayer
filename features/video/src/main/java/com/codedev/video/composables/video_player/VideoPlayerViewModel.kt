package com.codedev.home.video_player

import androidx.lifecycle.ViewModel
import com.codedev.data_lib.Either
import com.codedev.data_lib.models.Video
import com.codedev.data_lib.repositories.interfaces.IVideoRepository
import com.codedev.home.videos.VideoListViewModel
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import com.codedev.storage_lib.VideoContentProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

class VideoPlayerViewModel @Inject constructor(): ViewModel() {

    private val _currentVideoList: MutableStateFlow<List<Video>> = MutableStateFlow(
        emptyList()
    )
    val currentVideoList: StateFlow<List<Video>>
        get() = _currentVideoList

    private suspend fun getVideosByFolder(folder: String) {
        /*val status = repository.getVideosByFolder(folder)
        when (status) {
            is Either.Failure -> {
                Timber.d("Failed - ${status.reason}")
            }

            is Either.Success -> {
                Timber.d("Succeeded - ${status.data}")

            }
        }*/
    }
}