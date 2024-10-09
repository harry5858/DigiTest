package com.test.digiitunes.domain.useCases

import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.mapper.toResultEntity
import com.test.digiitunes.domain.model.SearchResultUiModel
import com.test.digiitunes.domain.repo.ItunesRepository

class SaveResultUseCase(
    private val repo: ItunesRepository
) {
    suspend fun invoke(uiModel: SearchResultUiModel): Result<Nothing?, Exception> {
        val isSavedPreviously = repo.checkIfResultIsAlreadySaved(
            artistId = uiModel.artistId,
            kind = uiModel.kind,
            trackName = uiModel.trackName
        )

        return when (isSavedPreviously) {
            is Result.Error -> isSavedPreviously
            is Result.Success -> {
                when (isSavedPreviously.data) {
                    true -> Result.Error(UnsupportedOperationException("Result is previously saved"))
                    false -> {
                        uiModel.toResultEntity().let { entity ->
                            repo.saveSearchResult(entity)
                        }
                    }
                }
            }
        }
    }
}