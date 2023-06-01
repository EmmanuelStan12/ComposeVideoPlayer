package com.codedev.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codedev.base._di.BaseFeatureComponent
import com.codedev.base.composables.DrawerNavigation
import com.codedev.base.register
import com.codedev.home.folders.FolderComposable
import com.codedev.home.common.folders.FolderTopAppBar
import com.codedev.home.folders.FolderViewModel
import com.codedev.home.common.offers.OfferComposable
import com.codedev.home.common.offers.OfferTopAppBar
import com.codedev.home.graphs.HomeNavigationGraph
import com.codedev.home.graphs.RootNavigationGraph
import com.codedev.home.videos.VideoComposable
import com.codedev.screens.home.HomeScreen
import com.codedev.screens.home.bottomBarItems
import com.codedev.ui_base_lib.ColorLightGrey
import com.codedev.video.di.VideoFeatureComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun HomeContainer() {

    val homeNavigationController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navBackStackEntry by homeNavigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        HomeNavigationGraph(navController = homeNavigationController)
    }
}