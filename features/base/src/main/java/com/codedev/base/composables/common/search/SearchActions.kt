package com.codedev.home.common.search

import com.codedev.data_lib.models.Query

sealed interface SearchActions {
    data class OnValueChanged(val query: String): SearchActions
    data class OnSearch(val query: String): SearchActions
    data class DeleteQuery(val query: Query): SearchActions
    object NavigateBack: SearchActions
}