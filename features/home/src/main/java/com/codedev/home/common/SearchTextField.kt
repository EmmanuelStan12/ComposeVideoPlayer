package com.codedev.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorLightGrey

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onValueChanged: (String) -> Unit,
    searchQuery: String
) {

    Box(modifier = modifier) {
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                onValueChanged(it)
            },
            maxLines = 1,
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(searchQuery)
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            textStyle = MaterialTheme.typography.body1,
            cursorBrush = SolidColor(ColorBlackBackground),
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(ColorLightGrey)
                .padding(vertical = 12.dp, horizontal = 8.dp),

            )
        if (searchQuery.isBlank()) {
            Text(text = "Search...", style = MaterialTheme.typography.body1, color = Color.Gray, modifier = Modifier
                .align(
                    Alignment.CenterStart
                )
                .padding(start = 8.dp))
        }
    }
}