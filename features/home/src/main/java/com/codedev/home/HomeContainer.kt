package com.codedev.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codedev.base.composables.DrawerNavigation
import com.codedev.home.folders.FolderComposable
import com.codedev.home.folders.FolderTopAppBar
import com.codedev.home.offers.OfferComposable
import com.codedev.home.offers.OfferTopAppBar
import com.codedev.screens.home.HomeScreen
import com.codedev.screens.home.bottomBarItems
import com.codedev.storage_lib.VideoContentProvider
import com.codedev.ui_base_lib.ColorLightGrey
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun HomeContainer() {

    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }
    val homeNavigationController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navBackStackEntry by homeNavigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        VideoContentProvider.getVideoFolders(context)
    }

    Scaffold(
        topBar = {
            if (currentRoute == HomeScreen.Offers.route)
                OfferTopAppBar()
            else
                FolderTopAppBar {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
        },
        drawerContent = {
                        DrawerNavigation()
        },
        scaffoldState = scaffoldState,
        drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
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
                            homeNavigationController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(homeNavigationController.graph.findStartDestination().id) {
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
    ) { paddingValues ->
        NavHost(
            navController = homeNavigationController,
            startDestination = HomeScreen.Folders.route
        ) {
            composable(route = HomeScreen.Folders.route) {
                FolderComposable()
            }

            composable(route = HomeScreen.Offers.route) {
                OfferComposable()
            }
        }
    }
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}