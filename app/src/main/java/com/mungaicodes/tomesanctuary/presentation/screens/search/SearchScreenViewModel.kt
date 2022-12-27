package com.mungaicodes.tomesanctuary.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.util.Resource
import com.mungaicodes.tomesanctuary.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repo: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch() {
        val queryString = _uiState.value.searchQuery
        val keyword = _uiState.value.searchKeyWord
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repo.getSearchResults(keyword + queryString).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
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
                        _eventFlow.emit(
                            UiEvent.ShowInfoToast(
                                "Loaded Successfully!!"
                            )
                        )
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }
    }

    fun keywordSelector(keyword: String) {
        _uiState.update {
            it.copy(searchKeyWord = keyword)
        }
    }

    fun expandDropDown() {
        _uiState.update {
            it.copy(menuExpanded = true)
        }
    }

    fun closeDropdown() {
        _uiState.update {
            it.copy(menuExpanded = false)
        }
    }
}