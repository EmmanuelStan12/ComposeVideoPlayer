package com.codedev.home.common.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codedev.data_lib.models.Query
import com.codedev.ui_base_lib.ColorBlackBackground
import com.codedev.ui_base_lib.ColorWhiteBackground

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun SearchModalBottomSheet(
    modalState: ModalBottomSheetState,
    onSearchAction: (SearchActions) -> Unit,
    queries: SnapshotStateList<Query>,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp),
        modifier = Modifier.fillMaxSize(),
        sheetState = modalState,
        sheetContent = {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxHeight().background(ColorWhiteBackground).padding(vertical = 16.dp)
            ) {
                item {
                    SearchAppBar(
                        onSearchAction = onSearchAction,
                        isVisible = modalState.isVisible
                    )
                }
                items(queries.size, key = { queries[it].title }) {
                    val query = queries[it]
                    ListItem(
                        trailing = {
                            if (query.isFromDB) {
                                IconButton(onClick = { onSearchAction(
                                    SearchActions.DeleteQuery(
                                        query
                                    )
                                ) }) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = ColorBlackBackground)
                                }
                            }
                        },
                        modifier = Modifier.animateItemPlacement()
                            .clickable { onSearchAction(SearchActions.OnSearch(query.title)) }
                    ) {
                        Text(text = query.title, style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    ) {
        content()
    }
}