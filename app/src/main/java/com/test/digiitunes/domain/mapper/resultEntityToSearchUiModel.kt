package com.test.digiitunes.domain.mapper

import com.test.digiitunes.data.local.model.ResultEntity
import com.test.digiitunes.domain.model.SearchResultUiModel

fun ResultEntity.toSearchUiModel(): SearchResultUiModel =
    SearchResultUiModel(
        uid = this.uid,
        artistId = this.artistId,
        artistName = this.artistName,
        artworkUrl100 = this.artworkUrl60,
        artworkUrl60 = this.artworkUrl100,
        kind = this.kind,
        previewUrl = this.previewUrl,
        trackName = this.trackName,
        trackViewUrl = this.trackViewUrl,
        wrapperType = this.wrapperType
    )