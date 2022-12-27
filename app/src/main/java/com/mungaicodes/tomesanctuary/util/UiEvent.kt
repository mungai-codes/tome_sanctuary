package com.mungaicodes.tomesanctuary.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    data class ShowInfoToast(val message: String) : UiEvent()
}
