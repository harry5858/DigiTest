package com.test.digiitunes.data.remote.model

data class SearchResponse(
    val resultCount: Int,
    val results: List<SearchResultResponse>
)
