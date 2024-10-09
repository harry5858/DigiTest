package com.test.digiitunes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.digiitunes.data.local.model.ResultEntity

@Dao
interface SavedResultDao {
    @Query("SELECT * FROM savedResult ORDER BY uid DESC")
    fun getAllSavedResult(): List<ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearchResult(vararg resultEntity: ResultEntity)

    @Query("DELETE FROM savedResult WHERE uid = :uid")
    fun deleteSavedResultByUid(uid: Int)

    @Query("SELECT COUNT(1) FROM savedResult WHERE artistId = :artistId AND kind = :kind AND trackName = :trackName")
    fun checkIfResultIsExist(
        artistId: Int?,
        kind: String?,
        trackName: String?
    ): Boolean
}