package com.codedev.video.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codedev.base.Graph
import com.codedev.ui_base_lib.ColorLightGrey
import com.codedev.video.di.VideoFeatureComponent
import com.codedev.video.navigation.VideoScreens
import com.codedev.video.navigation.VideoScreens.Companion.bottomBarItems

@Composable
fun VideoNavigationGraph() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
            ) {
                bottomBarItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon!!,
                                contentDescription = null,
                                tint = if (currentRoute.equals(screen.route)) MaterialTheme.colors.primary else ColorLightGrey
                            )
                        },
                        label = {
                            Text(screen.title, color = if (currentRoute.equals(screen.route)) MaterialTheme.colors.primary else ColorLightGrey)
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = VideoScreens.Folders.route,
            route = Graph.VIDEO_GRAPH
        ) {
            composable(route = VideoScreens.Folders.route) {

            }

            composable(route = VideoScreens.Offers.route) {

            }

            composable(route = VideoScreens.VideoList.route) {

            }
        }
    }
}