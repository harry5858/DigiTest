package com.test.digiitunes.ui.model

import com.test.digiitunes.domain.model.SearchResultUiModel

data class SavedScreenUiState(
    val savedResult: List<SearchResultUiModel> = emptyList()
)
