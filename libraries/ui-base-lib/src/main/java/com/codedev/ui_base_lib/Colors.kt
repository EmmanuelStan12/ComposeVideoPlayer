package com.codedev.ui_base_lib

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val ColorPrimary = Color(0xFF1D53E2)
val ColorPrimaryVariant = Color(0xFF4A76E8)
val ColorSecondary = Color(0xFFE21D53)
val ColorOnSecondary = Color(0xFFE21D53)
val ColorTertiary = Color(0xFFE2AC1D)
val ColorWhiteBackground = Color(0xFFFFFFFF)
val ColorBlackText = Color(0xFFFFFFFF)
val ColorWhiteText = Color(0xFFFFFFFF)
val ColorOnBackground = Color(0xFFFFFFFF)
val ColorOnPrimary = Color(0xFFFFFFFF)
val ColorBlackBackground = Color(0xFF0C0C0C)
val ColorBlack = Color(0xFF0C0C0C)
val ColorSurfaceLight = Color(0xFFFFFFFF)
val ColorSurfaceDark = Color(0xFFFFFFFF)
val ColorOnSurface = Color(0xFFFFFFFF)

val LightThemeColors = lightColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimaryVariant,
    secondary = ColorSecondary,
    background = ColorWhiteBackground,
    surface = ColorSurfaceLight,
    onPrimary = ColorOnPrimary,
    onSecondary = ColorOnSecondary,
    onBackground = ColorOnBackground,
    onSurface = ColorOnSurface,
)

val DarkThemeColors = darkColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimaryVariant,
    secondary = ColorSecondary,
    background = ColorWhiteBackground,
    surface = ColorSurfaceLight,
    onPrimary = ColorOnPrimary,
    onSecondary = ColorOnSecondary,
    onBackground = ColorOnBackground,
    onSurface = ColorOnSurface,
)