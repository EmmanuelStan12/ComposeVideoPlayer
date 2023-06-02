package com.codedev.base.providers

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalRootScaffoldState: ProvidableCompositionLocal<ScaffoldState?> = compositionLocalOf { null }