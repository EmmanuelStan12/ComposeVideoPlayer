package com.codedev.home.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codedev.base.TopAppBarActionButton
import com.codedev.ui_base_lib.ColorBlackText

@Composable
fun SearchAppBar(
    onSearchAction: (SearchActions) -> Unit,
    isVisible: Boolean
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = isVisible) {
        if (!isVisible) searchQuery = ""
    }

    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            IconButton(onClick = { onSearchAction(SearchActions.NavigateBack) }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = ColorBlackText)
            }
            SearchTextField(
                modifier = Modifier.weight(1f),
                onSearch = {
                    onSearchAction(SearchActions.OnSearch(it))
                },
                onValueChanged = {
                    onSearchAction(SearchActions.OnValueChanged(it))
                    searchQuery = it
                },
                searchQuery = searchQuery
            )
            TopAppBarActionButton(
                imageVector = Icons.Outlined.MoreVert,
                description = "Options"
            ) {

            }
        }
    }
}