package com.codedev.video.composables.video_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codedev.base.composables.Keyboard
import com.codedev.base.composables.keyboardAsState
import com.codedev.home.common.GenericTopAppBar
import com.codedev.home.common.search.SearchActions
import com.codedev.home.common.search.SearchModalBottomSheet
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun VideoComposable(
    folder: String,
    viewModel: VideoListViewModel,
    navController: NavController
) {

    val context = LocalContext.current

    val videos by viewModel.currentVideoList.collectAsState()

    val queries by viewModel.currentQueriesList.collectAsState()

    val uiState by viewModel.videoUiState.collectAsState(VideoListViewModel.VideoVMEvents.GetVideosLoading)

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val keyboard = LocalSoftwareKeyboardController.current

    val keyboardState by keyboardAsState()

    var job: Job? = null

    LaunchedEffect(key1 = true) {
        viewModel.execute(VideoListViewModel.VideoUIEvents.GetVideosByFolder(folder))
    }

    LaunchedEffect(key1 = modalSheetState.isVisible) {
        if (modalSheetState.isVisible)
            viewModel.execute(VideoListViewModel.VideoUIEvents.GetQueries("", folder))
        else {
            if (keyboardState == Keyboard.Opened)
                keyboard?.hide()
            viewModel.execute(VideoListViewModel.VideoUIEvents.ClearSearchList)
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
                            viewModel.execute(VideoListViewModel.VideoUIEvents.GetQueries(action.query, folder))
                        }
                    }
                }
                is SearchActions.OnSearch -> {
                    viewModel.execute(VideoListViewModel.VideoUIEvents.GetVideosByQuery(action.query, folder))
                    coroutineScope.launch { modalSheetState.hide() }
                }
                is SearchActions.DeleteQuery -> {
                    Timber.d("Delete Query")
                    viewModel.execute(VideoListViewModel.VideoUIEvents.DeleteQuery(action.query))
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

            if (uiState is VideoListViewModel.VideoVMEvents.GetVideosLoading) {
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