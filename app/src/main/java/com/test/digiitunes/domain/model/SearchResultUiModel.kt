package com.test.digiitunes.domain.model

data class SearchResultUiModel(
    val uid: Int? = null,
    val artistId: Int? = null,
    val artistName: String? = null,
    val artworkUrl100: String? = null,
    val artworkUrl60: String? = null,
    val kind: String? = null,
    val previewUrl: String? = null,
    val trackName: String? = null,
    val trackViewUrl: String? = null,
    val wrapperType: String? = null
)
