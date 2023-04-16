package com.codedev.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.codedev.ui_base_lib.DarkThemeColors
import com.codedev.ui_base_lib.LatoTypography
import com.codedev.ui_base_lib.LightThemeColors
import com.codedev.ui_base_lib.Shapes

@Composable
fun DefaultTheme(
    content: @Composable () -> Unit
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val colors = if (isSystemInDarkTheme) LightThemeColors else DarkThemeColors

    MaterialTheme(
        colors = colors,
        typography = LatoTypography,
        shapes = Shapes,
    ) {
        content()
    }
}