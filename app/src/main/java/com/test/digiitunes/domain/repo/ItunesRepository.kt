package com.test.digiitunes.domain.repo

import com.test.digiitunes.data.local.model.ResultEntity
import com.test.digiitunes.data.remote.model.SearchResultResponse
import com.test.digiitunes.data.util.Result
import kotlinx.coroutines.flow.Flow

interface ItunesRepository {
    suspend fun getSearchResult(keyword: String): Flow<Result<List<SearchResultResponse>, Exception>>
    suspend fun getSavedResult(): Flow<Result<List<ResultEntity>, Exception>>
    suspend fun saveSearchResult(resultEntity: ResultEntity): Result<Nothing?, Exception>
    suspend fun deleteSavedResult(uid: Int): Result<Nothing?, Exception>
    suspend fun checkIfResultIsAlreadySaved(artistId: Int?, kind: String?, trackName: String?): Result<Boolean, Exception>
}