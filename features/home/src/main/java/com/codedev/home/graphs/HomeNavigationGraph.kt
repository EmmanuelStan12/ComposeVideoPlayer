package com.codedev.home.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codedev.base.Graph
import com.codedev.base.register
import com.codedev.home.HomeFeaturedComposable
import com.codedev.home.navigation.HomeScreens
import com.codedev.video.di.VideoFeatureApiComponent

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    onMenuClick: () -> Unit,
    onChangeRoute: (String) -> Unit
) {

    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = HomeScreens.Featured.route
    ) {
        composable(
            route = HomeScreens.Featured.route
        ) {
            HomeFeaturedComposable(onMenuClick)
        }

        register(
            featureApi = VideoFeatureApiComponent.getInstance().getVideoFeatureApi(),
            navController = navController,
            onChangeRoute = onChangeRoute
        )
    }
}