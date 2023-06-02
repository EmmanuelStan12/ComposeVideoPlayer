package com.codedev.base

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.codedev.feature_api.FeatureApi

fun NavGraphBuilder.register(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onChangeRoute: (String) -> Unit
) {
    featureApi.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier,
        onChangeRoute = onChangeRoute
    )
}

fun Context.getActivity(): FragmentActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is FragmentActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}