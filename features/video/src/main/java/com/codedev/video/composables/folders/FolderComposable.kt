package com.codedev.video.composables.folders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoStroller
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codedev.base.providers.LocalRootScaffoldState
import com.codedev.home.common.folders.FolderItemComposable
import com.codedev.home.common.folders.FolderTopAppBar
import com.codedev.video.composables.video_list.VideoListViewModel
import com.codedev.video.navigation.VideoScreens
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun FolderComposable(
    navController: NavController,
    viewModel: VideoFolderViewModel
) {

    val scope = rememberCoroutineScope()

    val scaffoldState = LocalRootScaffoldState.current

    val folders by viewModel.currentFolderList.collectAsState()

    val folderUiState by viewModel.folderUiState.collectAsState(initial = VideoFolderViewModel.FolderVMEvents.GetVideosDataLoading)

    LaunchedEffect(key1 = true) {
        viewModel.execute(VideoFolderViewModel.FolderUIEvents.GetVideoData)
    }

    Scaffold(
        topBar = {
            FolderTopAppBar {
                scope.launch {
                    scaffoldState?.drawerState?.open()
                }
            }
        }
    ) { paddingValues ->
        if (folders.isNotEmpty()) {
            LazyColumn {
                items(folders.size) {
                    FolderItemComposable(folders[it]) {
                        navController.navigate(VideoScreens.VideoList.route.replace("{folder}", folders[it].title))
                    }
                }
            }
        } else if (folderUiState is VideoFolderViewModel.FolderVMEvents.GetVideosDataLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .alpha(0.3f))
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.NoStroller, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(100.dp))
                Text(
                    text = "Sorry, you currently don't have any videos on your device" +
                        "Please make sure you also gave the " +
                        "right permissions to the app to access your local storage",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}