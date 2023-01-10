package com.mungaicodes.tomesanctuary.presentation.search

import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.search.util.Filter
import com.mungaicodes.tomesanctuary.presentation.search.util.filterList

data class SearchScreenUiState(
    val books: List<Book?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchKeyWord: String = "intitle:",
    val keyWordIndex: Int = 0,
    val filter: Filter = filterList[0],
    val filterIndex: Int = 0,
    val searchQuery: String = "",
    val menuExpanded: Boolean = false,
)
