package com.codedev.home.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codedev.base.Graph
import com.codedev.base.register
import com.codedev.video.di.VideoFeatureComponent

@Composable
fun HomeNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = ""
    ) {
        composable(route = "home") {

        }

        register(
            featureApi = VideoFeatureComponent.getInstance().getVideoFeatureApi(),
            navController
        )
    }
}