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

val Roboto = FontFamily(
    Font(
        R.font.roboto_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        R.font.roboto_italic,
        weight = FontWeight.Black,
        style = FontStyle.Normal
    ),
    Font(
        R.font.roboto_light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
    Font(
        R.font.roboto_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        R.font.roboto_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
    Font(
        R.font.roboto_thin,
        weight = FontWeight.Thin,
        style = FontStyle.Normal
    ),
    Font(
        R.font.roboto_lightitalic,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    ),
)

val RobotoTypography = Typography(
    defaultFontFamily = Roboto,
    h1 = TextStyle(
        color = ColorBlackText,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    h2 = TextStyle(
        color = ColorBlackText,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    h3 = TextStyle(
        color = ColorBlackText,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
    body1 = TextStyle(
        color = ColorBlackText,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    body2 = TextStyle(
        color = ColorBlackText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    ),
    caption = TextStyle(
        color = ColorBlackText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    ),
)