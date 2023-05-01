package com.codedev.home

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codedev.base.DefaultTheme

class HomeActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Hello", "World")

        setContent {
            DefaultTheme {
                HomeContainer()
            }
        }
    }
}