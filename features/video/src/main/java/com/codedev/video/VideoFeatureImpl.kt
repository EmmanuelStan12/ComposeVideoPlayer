package com.codedev.video

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codedev.base.Graph
import com.codedev.video.composables.VideoNavigationGraph
import com.codedev.video.navigation.VideoScreens

class VideoFeatureImpl: VideoFeatureApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = Graph.VIDEO_GRAPH) {
            VideoNavigationGraph()
        }
    }

    override fun registerScreen() {
        TODO("Not yet implemented")
    }

    override fun videoRoute(): String {
        return Graph.VIDEO_GRAPH
    }
}