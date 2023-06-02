package com.codedev.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codedev.base.DefaultTheme
import com.codedev.base._di.BaseFeatureComponentProvider
import com.codedev.base.providers.LocalRootScaffoldState
import com.codedev.home._di.DaggerHomeFeatureComponent
import com.codedev.home._di.HomeFeatureComponent
import com.codedev.home.drawer.DrawerNavigation
import com.codedev.home.graphs.HomeNavigationGraph
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeActivity: FragmentActivity() {

    private lateinit var homeFeatureComponent: HomeFeatureComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeFeatureComponent = DaggerHomeFeatureComponent.builder()
            .baseFeatureComponent((applicationContext as BaseFeatureComponentProvider).provideBaseComponent())
            .build()

        homeFeatureComponent.inject(this)
        
        setContent {
            DefaultTheme {

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var currentRoute by remember {
                    mutableStateOf(currentDestination?.route)
                }
                LaunchedEffect(key1 = currentDestination?.route) {
                    currentRoute = currentDestination?.route
                }

                CompositionLocalProvider(LocalRootScaffoldState provides scaffoldState) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        drawerContent = {
                            DrawerNavigation(navController, currentRoute)
                        },
                        drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                    ) { _ ->
                        HomeNavigationGraph(navController, onMenuClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) { route ->
                            currentRoute = route
                        }
                    }
                }
            }
        }
    }
}