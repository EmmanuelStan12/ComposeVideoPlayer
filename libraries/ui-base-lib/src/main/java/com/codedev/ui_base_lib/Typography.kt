package com.codedev.ui_base_lib

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Lato = FontFamily(
    Font(
        R.font.lato_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        R.font.lato_black,
        weight = FontWeight.Black,
        style = FontStyle.Normal
    ),
    Font(
        R.font.lato_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        R.font.lato_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        R.font.lato_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        R.font.lato_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        R.font.lato_lightitalic,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
)

val LatoTypography = Typography(
    defaultFontFamily = Lato,
    h1 = TextStyle(
        color = ColorWhiteText,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    h2 = TextStyle(
        color = ColorWhiteText,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    h3 = TextStyle(
        color = ColorWhiteText,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    body1 = TextStyle(
        color = ColorWhiteText,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    body2 = TextStyle(
        color = ColorWhiteText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    caption = TextStyle(
        color = ColorWhiteText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
)