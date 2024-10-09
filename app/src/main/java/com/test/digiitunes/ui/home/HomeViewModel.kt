package com.test.digiitunes.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.digiitunes.data.util.Result
import com.test.digiitunes.domain.model.SearchResultUiModel
import com.test.digiitunes.domain.useCases.GetSearchResultUseCase
import com.test.digiitunes.domain.useCases.SaveResultUseCase
import com.test.digiitunes.ui.base.BaseViewModel
import com.test.digiitunes.ui.model.HomeScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val saveResultUseCase: SaveResultUseCase
): BaseViewModel() {
    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    fun onSearchClick() {
        startLoading()
        viewModelScope.launch(Dispatchers.IO) {
            getSearchResultUseCase.invoke(keyword = _uiState.value.query).collect { result ->
                when (result) {
                    is Result.Error -> { updateError(result.error) }
                    is Result.Success -> { updateSearchResult(result.data) }
                }
            }
            stopLoading()
        }
    }

    fun onSave(selectedResult: SearchResultUiModel) {
        startLoading()
        viewModelScope.launch(Dispatchers.IO) {
            saveResultUseCase.invoke(selectedResult).let { result ->
                when(result) {
                    is Result.Error -> { updateError(result.error) }
                    is Result.Success -> { updateToastMessage(selectedResult.trackName)  }
                }
            }
            stopLoading()
        }
    }

    fun onQueryChange(value: String) {
        _uiState.update { it.copy(query = value) }
    }

    private fun updateSearchResult(result: List<SearchResultUiModel>) {
        _uiState.update { it.copy(searchResults = result) }
        stopLoading()
    }
}