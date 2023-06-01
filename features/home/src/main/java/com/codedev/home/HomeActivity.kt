package com.codedev.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.codedev.base.DefaultTheme
import com.codedev.home._di.HomeFeatureComponent
import timber.log.Timber

class HomeActivity: FragmentActivity() {

    lateinit var homeFeatureComponent: HomeFeatureComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeFeatureComponent = HomeFeatureComponent.getInitialBuilder()
            .build()

        homeFeatureComponent.inject(this)
        
        setContent {
            DefaultTheme {

                HomeContainer()
            }
        }
    }
}