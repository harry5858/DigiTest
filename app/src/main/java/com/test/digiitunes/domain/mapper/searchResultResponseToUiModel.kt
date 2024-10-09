package com.test.digiitunes.domain.mapper

import com.test.digiitunes.data.remote.model.SearchResultResponse
import com.test.digiitunes.domain.model.SearchResultUiModel

fun SearchResultResponse.toUiModel(): SearchResultUiModel =
    SearchResultUiModel(
        artistId = this.artistId,
        artistName = this.artistName,
        artworkUrl100 = this.artworkUrl100,
        artworkUrl60 = this.artworkUrl60,
        kind = this.kind,
        previewUrl = this.previewUrl,
        trackName = this.trackName,
        trackViewUrl = this.trackViewUrl,
        wrapperType = this.wrapperType
    )