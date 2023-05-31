package com.codedev.home.folders

import android.app.Application
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.codedev.data_lib.models.Folder
import com.codedev.home.getActivity
import com.codedev.screens.home.HomeScreen

@Composable
fun FolderComposable(
    navController: NavController
) {

    val context = LocalContext.current

    val viewModelProvider = ViewModelProvider(context.getActivity()!!.viewModelStore, FolderViewModelFactory(
        context.applicationContext as Application
    ))

    val viewModel = viewModelProvider[FolderViewModel::class.java]

    val folders by viewModel.currentFolderList.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.execute(FolderViewModel.FolderUIEvents.GetVideoData)
    }

    Scaffold(
        topBar = {
            FolderTopAppBar {}
        }
    ) { paddingValues ->
        LazyColumn {
            items(folders.size) {
                FolderItemComposable(folders[it]) {
                    navController.navigate(HomeScreen.VideoList.route.replace("{folder}", folders[it].title))
                }
            }
        }
    }
}