package com.mungaicodes.tomesanctuary.presentation.mylibrary

import com.mungaicodes.tomesanctuary.data.local.BookEntity

data class MyLibraryUiState(
    val myLibrary: List<BookEntity> = emptyList()
)
