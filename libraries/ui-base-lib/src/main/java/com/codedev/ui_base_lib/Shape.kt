package com.codedev.ui_base_lib

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val DefaultRectangleRadius = RoundedCornerShape(16.dp)
val MediumRectangleRadius = RoundedCornerShape(16.dp)
val LargeRectangleRadius = RoundedCornerShape(32.dp)
val SmallRectangleRadius = RoundedCornerShape(8.dp)

val Shapes = Shapes(
    small = SmallRectangleRadius,
    medium = MediumRectangleRadius,
    large = LargeRectangleRadius
)