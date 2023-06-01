package com.codedev.video.composables.folders

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoStroller
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.codedev.home.common.folders.FolderItemComposable
import com.codedev.home.common.folders.FolderTopAppBar
import com.codedev.video.navigation.VideoScreens

@Composable
fun FolderComposable(
    navController: NavController,
    viewModel: FolderViewModel
) {

    val context = LocalContext.current

    val folders by viewModel.currentFolderList.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.execute(FolderViewModel.FolderUIEvents.GetVideoData)
    }

    Scaffold(
        topBar = {
            FolderTopAppBar {}
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
        } else {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.NoStroller, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Sorry, you currently don't have any videos on your device\n" +
                        "Please make sure you also gave the " +
                        "right permissions to the app to access your local storage", style = MaterialTheme.typography.body1, color = Color.Gray)
            }
        }
    }
}