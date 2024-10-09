package com.test.digiitunes.ui.model

import com.test.digiitunes.domain.model.SearchResultUiModel

data class HomeScreenUiState(
    val query: String = "",
    val searchResults: List<SearchResultUiModel> = emptyList()
)
