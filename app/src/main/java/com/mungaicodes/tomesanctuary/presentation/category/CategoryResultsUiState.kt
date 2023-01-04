package com.mungaicodes.tomesanctuary.presentation.category

import com.mungaicodes.tomesanctuary.domain.model.Book

data class CategoryResultsUiState(
    val categoryTitle: String = "CATEGORY",
    val books: List<Book?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
