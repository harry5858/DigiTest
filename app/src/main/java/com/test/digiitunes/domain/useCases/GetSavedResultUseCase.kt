package com.test.digiitunes.domain.useCases

import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.mapper.toSearchUiModel
import com.test.digiitunes.domain.model.SearchResultUiModel
import com.test.digiitunes.domain.repo.ItunesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSavedResultUseCase(
    private val repo: ItunesRepository
) {
    suspend fun invoke(): Flow<Result<List<SearchResultUiModel>, Exception>> {
        return  repo.getSavedResult().map { result ->
            when(result) {
                is Result.Error -> result
                is Result.Success -> Result.Success(result.data.map { it.toSearchUiModel() })
            }
        }
    }
}