package com.test.digiitunes.domain.useCases

import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.repo.ItunesRepository

class DeleteSavedEntryUseCase(
    private val repo: ItunesRepository
) {
    suspend fun invoke(uid: Int): Result<Nothing?, Exception> {
        return repo.deleteSavedResult(uid)
    }
}