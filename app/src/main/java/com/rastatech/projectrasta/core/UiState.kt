package com.rastatech.projectrasta.core

sealed class UiState {
    object Uninitialized: UiState()
    object Loading : UiState()
    class Success<T>(val data: T) : UiState()
    class Fail(val throwable: Throwable) : UiState()
}
