package com.codedev.home.videos

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.codedev.base.composables.Keyboard
import com.codedev.base.composables.keyboardAsState
import com.codedev.data_lib.models.Folder
import com.codedev.home.common.GenericTopAppBar
import com.codedev.home.common.SearchActions
import com.codedev.home.common.SearchAppBar
import com.codedev.home.common.SearchModalBottomSheet
import com.codedev.home.getActivity
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorWhiteBackground
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun VideoComposable(
    folder: String,
    navController: NavController
) {

    val context = LocalContext.current

    val viewModelProvider = ViewModelProvider(context.getActivity()!!.viewModelStore, VideoViewModelFactory(
        context.applicationContext as Application
    ))

    val viewModel = viewModelProvider[VideoViewModel::class.java]

    val videos by viewModel.currentVideoList.collectAsState()

    val queries by viewModel.currentQueriesList.collectAsState()

    val uiState by viewModel.videoUiState.collectAsState(VideoViewModel.VideoVMEvents.GetVideosLoading)

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val keyboard = LocalSoftwareKeyboardController.current

    val keyboardState by keyboardAsState()

    var job: Job? = null

    LaunchedEffect(key1 = true) {
        viewModel.execute(VideoViewModel.VideoUIEvents.GetVideosByFolder(folder))
    }

    LaunchedEffect(key1 = modalSheetState.isVisible) {
        if (modalSheetState.isVisible)
            viewModel.execute(VideoViewModel.VideoUIEvents.GetQueries("", folder))
        else {
            if (keyboardState == Keyboard.Opened)
                keyboard?.hide()
            viewModel.execute(VideoViewModel.VideoUIEvents.ClearSearchList)
        }
    }

    BackHandler(enabled = modalSheetState.isVisible) {
        Timber.d("Keyboard State $keyboardState")
        if (keyboardState == Keyboard.Opened)
            keyboard?.hide()
        coroutineScope.launch { modalSheetState.hide() }
    }

    SearchModalBottomSheet(
        modalState = modalSheetState,
        queries = queries,
        onSearchAction = { action ->
            when (action) {
                is SearchActions.NavigateBack -> {
                    coroutineScope.launch { modalSheetState.hide() }
                }
                is SearchActions.OnValueChanged -> {
                    job?.cancel()
                    job = coroutineScope.launch {
                        if (isActive) {
                            delay(100L)
                            viewModel.execute(VideoViewModel.VideoUIEvents.GetQueries(action.query, folder))
                        }
                    }
                }
                is SearchActions.OnSearch -> {
                    viewModel.execute(VideoViewModel.VideoUIEvents.GetVideosByQuery(action.query, folder))
                    coroutineScope.launch { modalSheetState.hide() }
                }
                is SearchActions.DeleteQuery -> {
                    Timber.d("Delete Query")
                    viewModel.execute(VideoViewModel.VideoUIEvents.DeleteQuery(action.query))
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                GenericTopAppBar(title = folder) {
                    coroutineScope.launch {
                        if (modalSheetState.isVisible)
                            modalSheetState.hide()
                        else
                            modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                }
            }
        ) { _ ->
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxHeight(),

            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(videos.size, key = { videos[it].id!! }) {
                    VideoItemComposable(videos[it]) {

                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }

            if (uiState is VideoViewModel.VideoVMEvents.GetVideosLoading) {
                Box(modifier = Modifier
                    .fillMaxSize()
                ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .alpha(0.3f))
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    }
}