package com.test.digiitunes.data.repo

import com.test.digiitunes.data.local.dao.SavedResultDao
import com.test.digiitunes.data.local.model.ResultEntity
import com.test.digiitunes.data.remote.ItunesApi
import com.test.digiitunes.data.remote.model.SearchResultResponse
import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.repo.ItunesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItunesRepositoryImpl(
    private val api: ItunesApi,
    private val savedResultDao: SavedResultDao
): ItunesRepository {

    override suspend fun getSearchResult(keyword: String): Flow<Result<List<SearchResultResponse>, Exception>> = flow {
        try {
            val result = api.search(name = keyword).body()?.results ?: emptyList()
            emit(Result.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e))
        }

    }

    override suspend fun getSavedResult(): Flow<Result<List<ResultEntity>, Exception>> = flow {
        try {
            emit(Result.Success(savedResultDao.getAllSavedResult()))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }


    override suspend fun saveSearchResult(resultEntity: ResultEntity): Result<Nothing?, Exception> {
        return try {
            savedResultDao.saveSearchResult(resultEntity)
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteSavedResult(uid: Int): Result<Nothing?, Exception> {
        return try {
            savedResultDao.deleteSavedResultByUid(uid)
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun checkIfResultIsAlreadySaved(
        artistId: Int?,
        kind: String?,
        trackName: String?
    ): Result<Boolean, Exception> {
        return try {
            val isExist = savedResultDao.checkIfResultIsExist(artistId, kind, trackName)
            Result.Success(isExist)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}