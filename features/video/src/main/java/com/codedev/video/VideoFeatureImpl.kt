package com.codedev.video

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
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
        navController: NavController,
        modifier: Modifier,
        onChangeRoute: (String) -> Unit
    ) {
        navGraphBuilder.composable(
            route = Graph.VIDEO_GRAPH,
            arguments = listOf(navArgument("route") { defaultValue = VideoScreens.Folders.route })
        ) { backStackEntry ->
            val route = backStackEntry.arguments?.getString("route", VideoScreens.Folders.route)!!
            VideoNavigationGraph(
                route = route,
                onChangeRoute = onChangeRoute
            )
        }
    }

    override fun registerScreen() {
        TODO("Not yet implemented")
    }

    override fun videoRoute(): String {
        return Graph.VIDEO_GRAPH
    }
}