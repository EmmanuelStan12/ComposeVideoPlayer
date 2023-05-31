package com.codedev.home.videos

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codedev.data_lib.Either
import com.codedev.data_lib.models.Folder
import com.codedev.data_lib.models.Query
import com.codedev.data_lib.models.Video
import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.home.common.SearchActions
import com.codedev.room_lib.MediaDatabase
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import com.codedev.storage_lib.VideoContentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class VideoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(application) as T
    }
}

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val dispatcher = Dispatchers.Main

    private val videoDao: VideoDao =
        MediaDatabase.getInstance(application.applicationContext).getVideoDao()

    private val queryDao: QueryDao =
        MediaDatabase.getInstance(application.applicationContext).getQueryDao()

    private val repository = VideoRepositoryImpl(videoDao, queryDao)

    private val _currentVideoList: MutableStateFlow<SnapshotStateList<Video>> = MutableStateFlow(
        SnapshotStateList()
    )
    val currentVideoList: StateFlow<SnapshotStateList<Video>>
        get() = _currentVideoList

    private val _currentQueriesList: MutableStateFlow<SnapshotStateList<Query>> = MutableStateFlow(
        SnapshotStateList()
    )
    val currentQueriesList: StateFlow<SnapshotStateList<Query>>
        get() = _currentQueriesList

    sealed interface VideoUIEvents {
        data class GetVideosByFolder(val folder: String) : VideoUIEvents
        data class GetQueries(val query: String, val folder: String) : VideoUIEvents
        data class GetVideosByQuery(val query: String, val folder: String) : VideoUIEvents
        data class DeleteQuery(val query: Query) : VideoUIEvents
        object ClearSearchList : VideoUIEvents
    }

    sealed interface VideoVMEvents {
        object GetVideosLoading : VideoVMEvents
        object GetQueriesFailure : VideoVMEvents
        object GetQueriesSuccessful : VideoVMEvents
        data class GetVideosSuccessful(val folders: List<Video>) : VideoVMEvents
        data class GetVideosFailure(val message: String) : VideoVMEvents
        object None : VideoVMEvents
    }

    private val _videoUiState = MutableSharedFlow<VideoVMEvents>()
    val videoUiState: SharedFlow<VideoVMEvents>
        get() = _videoUiState

    fun execute(event: VideoUIEvents) {
        viewModelScope.launch(dispatcher) {
            when (event) {
                is VideoUIEvents.GetVideosByFolder -> {
                    Timber.d("GetAllVideos")
                    getVideosByFolder(event.folder)
                }

                is VideoUIEvents.GetQueries -> {
                    getQueries(event.folder, event.query)
                }

                is VideoUIEvents.GetVideosByQuery -> {
                    getVideosBySearchQuery(event.folder, event.query)
                }

                is VideoUIEvents.ClearSearchList -> {
                    _currentQueriesList.value.clear()
                }

                is VideoUIEvents.DeleteQuery -> {
                    deleteQuery(event.query)
                }
            }
        }
    }

    private suspend fun getVideosByFolder(folder: String) {
        val status = repository.getVideosByFolder(folder)
        when (status) {
            is Either.Failure -> {
                Timber.d("Failed - ${status.reason}")
                _videoUiState.emit(VideoVMEvents.GetVideosFailure(status.reason?.localizedMessage.toString()))
            }

            is Either.Success -> {
                Timber.d("Succeeded - ${status.data}")
                val data = status.data.map {
                    it.copy(
                        bitmap = VideoContentProvider.getVideoThumbnail(
                            getApplication(),
                            it.path
                        )
                    )
                }
                _videoUiState.emit(VideoVMEvents.GetVideosSuccessful(data))
                _currentVideoList.value.clear()
                _currentVideoList.value.addAll(data)
            }
        }
    }

    private suspend fun getQueries(folder: String, query: String) {
        val status = repository.getQueriesBySearch("video", query, folder)
        when (status) {
            is Either.Failure -> {
                Timber.d("Failed - ${status.reason}")
                _videoUiState.emit(VideoVMEvents.GetQueriesFailure)
            }

            is Either.Success -> {
                Timber.d("Succeeded - ${status.data}")
                _videoUiState.emit(VideoVMEvents.GetQueriesSuccessful)
                _currentQueriesList.value.clear()
                _currentQueriesList.value.addAll(status.data)
            }
        }
    }

    private suspend fun getVideosBySearchQuery(folder: String, query: String) = withContext(dispatcher) {
        _videoUiState.emit(VideoVMEvents.GetVideosLoading)
        val deferred = async { repository.insertQuery("video", query) }
        val status = repository.searchVideosByFolder(folder, query)
        when (status) {
            is Either.Failure -> {
                Timber.d("Failed - ${status.reason}")
                _videoUiState.emit(VideoVMEvents.GetVideosFailure(status.reason?.localizedMessage.toString()))
            }

            is Either.Success -> {
                Timber.d("Succeeded - ${status.data}")
                val data = status.data.map {
                    it.copy(
                        bitmap = VideoContentProvider.getVideoThumbnail(
                            getApplication(),
                            it.path
                        )
                    )
                }
                Timber.d("After Conversion - $data")
                _videoUiState.emit(VideoVMEvents.GetVideosSuccessful(data))
                _currentVideoList.value.clear()
                _currentVideoList.value.addAll(data)
            }
        }
    }

    private suspend fun deleteQuery(query: Query) {
        _currentQueriesList.value.remove(query)
        repository.deleteQuery(query)
    }
}