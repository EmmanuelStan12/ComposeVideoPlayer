package com.codedev.video.composables.folders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codedev.data_lib.Either
import com.codedev.data_lib.models.Folder
import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.room_lib.MediaDatabase
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import com.codedev.storage_lib.VideoContentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class FolderViewModel(application: Application) : AndroidViewModel(application) {

    private val dispatcher = Dispatchers.Main

    private val videoDao: VideoDao = MediaDatabase.getInstance(application.applicationContext).getVideoDao()
    private val queryDao: QueryDao = MediaDatabase.getInstance(application.applicationContext).getQueryDao()

    private val repository = VideoRepositoryImpl(videoDao, queryDao)

    private val _currentFolderList: MutableStateFlow<List<Folder>> = MutableStateFlow(emptyList())
    val currentFolderList: StateFlow<List<Folder>>
        get() = _currentFolderList

    sealed interface FolderUIEvents {
        object GetVideoData: FolderUIEvents
        data class GetVideosByFolder(val folder: String): FolderUIEvents
    }

    sealed interface FolderVMEvents {
        object GetVideosDataLoading: FolderVMEvents
        data class GetVideosDataSuccessful(val folders: List<Folder>): FolderVMEvents
        data class GetVideosDataFailure(val message: String): FolderVMEvents
        object None: FolderVMEvents
    }

    private val _folderUiState = MutableSharedFlow<FolderVMEvents>()
    val folderUiState: SharedFlow<FolderVMEvents>
        get() = _folderUiState

    fun execute(event: FolderUIEvents) {
        viewModelScope.launch(dispatcher) {
            when (event) {
                is FolderUIEvents.GetVideoData -> {
                    Timber.d("GetAllVideos")
                    if (_currentFolderList.value.isNotEmpty())
                        return@launch
                    getVideosData()
                }
                is FolderUIEvents.GetVideosByFolder -> {

                }
            }
        }
    }

    private suspend fun getVideosData() {
        _folderUiState.emit(FolderVMEvents.GetVideosDataLoading)

        val videosData =
            VideoContentProvider.getVideos(getApplication<Application>().applicationContext)

        val contentProviderVideos = videosData.second
        _currentFolderList.value = videosData.first

        val status = repository.saveVideos(contentProviderVideos)
        when (status) {
            is Either.Failure -> {
                Timber.d("Failed - ${status.reason}")
                _folderUiState.emit(FolderVMEvents.GetVideosDataFailure(status.reason?.localizedMessage.toString()))
            }
            is Either.Success -> {
                Timber.d("Succeeded - ${status.data}")
                _folderUiState.emit(FolderVMEvents.GetVideosDataSuccessful(videosData.first))
            }
        }
    }
}