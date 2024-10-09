package com.test.digiitunes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.digiitunes.data.local.dao.SavedResultDao
import com.test.digiitunes.data.local.model.ResultEntity

@Database(
    entities = [ResultEntity::class],
    version = 2,
    exportSchema = true
)
abstract class SavedResultDatabase: RoomDatabase() {
    abstract fun searchResultDao(): SavedResultDao
}