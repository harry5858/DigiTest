package com.test.digiitunes.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedResult")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
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
