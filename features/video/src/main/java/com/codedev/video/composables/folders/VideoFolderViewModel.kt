package com.codedev.video.composables.folders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codedev.data_lib.Either
import com.codedev.data_lib.models.Folder
import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.data_lib.repositories.interfaces.IVideoRepository
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
import javax.inject.Inject

class VideoFolderViewModel @Inject constructor(
    private val repository: IVideoRepository
) : ViewModel() {

    private val dispatcher = Dispatchers.Main

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
            VideoContentProvider.getVideos()

        val contentProviderVideos = videosData.second
        _currentFolderList.emit(videosData.first)

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