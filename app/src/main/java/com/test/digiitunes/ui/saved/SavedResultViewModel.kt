package com.test.digiitunes.ui.saved

import androidx.lifecycle.viewModelScope
import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.useCases.DeleteSavedEntryUseCase
import com.test.digiitunes.domain.useCases.GetSavedResultUseCase
import com.test.digiitunes.ui.base.BaseViewModel
import com.test.digiitunes.ui.model.SavedScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedResultViewModel(
    private val getSavedResultUseCase: GetSavedResultUseCase,
    private val deleteSavedEntryUseCase: DeleteSavedEntryUseCase
): BaseViewModel() {

    private val _uiState: MutableStateFlow<SavedScreenUiState> = MutableStateFlow(SavedScreenUiState())
    val uiState: StateFlow<SavedScreenUiState>
        get() = _uiState

    fun onDeleteSavedResult(uid: Int, trackName: String) {
        startLoading()
        viewModelScope.launch(Dispatchers.IO) {
            deleteSavedEntryUseCase.invoke(uid).let { result ->
                when(result) {
                    is Result.Error -> { updateError(result.error) }
                    is Result.Success -> { updateToastMessage(message = trackName) }
                }
            }
            getSavedResults()
        }
    }

    fun getSavedResults() {
        startLoading()
        viewModelScope.launch(Dispatchers.IO) {
            getSavedResultUseCase.invoke().collect { result ->
                when(result) {
                    is Result.Error -> { updateError(result.error) }
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(savedResult = result.data)
                        }
                    }
                }
            }
            stopLoading()
        }
    }
}