package com.mungaicodes.tomesanctuary.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryResultsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryResultsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("category")?.let { categoryTitle ->
            if (categoryTitle != "CATEGORY") {
                viewModelScope.launch {
                    _uiState.update {
                        it.copy(categoryTitle = categoryTitle)
                    }
                }
            }

        }
        savedStateHandle.get<String>("query")?.let { query ->
            viewModelScope.launch {
                repo.getSearchResults(query).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState
                            _uiState.update {
                                it.copy(isLoading = true)
                            }
                        }
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    books = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }
                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false
                                )
                            }
                        }
                    }
                }.launchIn(this)
            }
        }
    }
}