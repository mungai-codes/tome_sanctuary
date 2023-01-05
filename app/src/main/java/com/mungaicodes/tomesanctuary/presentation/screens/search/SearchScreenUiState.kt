package com.mungaicodes.tomesanctuary.presentation.screens.search

import com.mungaicodes.tomesanctuary.domain.model.Book

data class SearchScreenUiState(
    val books: List<Book?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchKeyWord: String = "intitle:",
    val searchQuery: String = "",
    val menuExpanded: Boolean = false,
)
