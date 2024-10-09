package com.test.digiitunes.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean>
        get() = _loading

    private val _error: MutableState<Exception?> = mutableStateOf(null)
    val error: State<Exception?>
        get() = _error

    private val _toastMessage: MutableState<String?> = mutableStateOf(null)
    val toastMessage: State<String?>
        get() = _toastMessage

    fun startLoading() { _loading.value = true }
    fun stopLoading() { _loading.value = false }

    fun updateError(e: Exception) { _error.value = e }
    fun resetError() { _error.value = null }

    fun updateToastMessage(message: String?) { _toastMessage.value = message }
    fun resetToastMessage() { _toastMessage.value = null }
}