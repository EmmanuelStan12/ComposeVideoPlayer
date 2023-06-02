package com.codedev.home.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codedev.base.Graph
import com.codedev.base.providers.LocalRootScaffoldState
import com.codedev.home.navigation.HomeScreens
import com.codedev.music.navigation.MusicScreens
import com.codedev.ui_base_lib.ColorBlackText
import com.codedev.ui_base_lib.ColorGrey
import com.codedev.video.navigation.VideoScreens
import kotlinx.coroutines.launch

@Composable
fun DrawerNavigation(
    navController: NavHostController,
    currentRoute: String?
) {

    val scaffoldState = LocalRootScaffoldState.current
    val scope = rememberCoroutineScope()

    val navigate: (String, String) -> Unit = { graph, route ->
        navController.navigate(graph.replace("{route}", route)) {
            scope.launch {
                scaffoldState?.drawerState?.close()
            }
            popUpTo(Graph.HOME_GRAPH) {
                inclusive = true
            }
        }
    }

    LazyColumn {
        item {
            DrawerHeader(title = "Home")
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = ColorGrey)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(HomeScreens.drawerItems) { item ->
            DrawerNavigationItem(
                title = item.title,
                imageVector = item.icon,
                route = item.route,
                selected = item.route.equals(currentRoute, true)
            ) {
                navigate(Graph.HOME_GRAPH, item.route)
            }
        }

        item {
            DrawerHeader(title = "Video")
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = ColorGrey)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(VideoScreens.drawerItems) { item ->
            DrawerNavigationItem(
                title = item.title,
                imageVector = item.icon,
                route = item.route,
                selected = item.route.equals(currentRoute, true)
            ) {
                navigate(Graph.VIDEO_GRAPH, item.route)
            }
        }
        item {
            DrawerHeader(title = "Music")
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = ColorGrey)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(MusicScreens.drawerItems) { item ->
            DrawerNavigationItem(
                title = item.title,
                imageVector = item.icon,
                route = item.route,
                selected = item.route.equals(currentRoute, true)
            ) {
                navigate(Graph.MUSIC_GRAPH, item.route)
            }
        }
    }
}