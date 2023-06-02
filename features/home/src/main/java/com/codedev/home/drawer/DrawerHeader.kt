package com.codedev.home.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorGrey

@Composable
fun DrawerHeader(
    title: String
) {
    Text(text = title, style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold), color = ColorGrey,
    modifier = Modifier.padding(horizontal = 16.dp))
}