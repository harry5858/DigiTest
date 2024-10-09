package com.test.digiitunes.data.util

sealed class Result<out S,out E> {
    data class Success<out S>(val data: S): Result<S, Nothing>()
    data class Error<out E>(val error: E): Result<Nothing, E>()
}