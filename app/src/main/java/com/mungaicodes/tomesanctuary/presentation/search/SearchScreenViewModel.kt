package com.mungaicodes.tomesanctuary.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.presentation.search.util.filterList
import com.mungaicodes.tomesanctuary.presentation.search.util.keyWords
import com.mungaicodes.tomesanctuary.util.Resource
import com.mungaicodes.tomesanctuary.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        val keyword = keyWords[_uiState.value.keyWordIndex].value
        val filter = filterList[_uiState.value.filterIndex].value
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repo.getSearchResults(query = keyword + queryString, filter = filter).onEach { result ->
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
                                "Results for: ${keyword.uppercase()} $queryString"
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

    fun phaseThroughKeyWords() {

        var currentIndex = _uiState.value.keyWordIndex

        if (currentIndex < 2) {
            currentIndex++
            _uiState.update { it.copy(keyWordIndex = currentIndex) }
        } else if (currentIndex == 2) {
            currentIndex--
            _uiState.update { it.copy(keyWordIndex = 0) }
        }
    }

    fun phaseThroughFilters() {
        var currentIndex = _uiState.value.filterIndex

        if (currentIndex < 4) {
            currentIndex++
            _uiState.update { it.copy(filterIndex = currentIndex) }
        } else if (currentIndex == 4) {
            currentIndex--
            _uiState.update { it.copy(filterIndex = 0) }
        }

    }

    suspend fun getModalBook(volumeId: String) {
        viewModelScope.launch {
            repo.findBookByVolumeId(volumeId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(modalBook = result.data)
                        }
                        Log.d("ModalBook", "Success")
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        Log.d("ModalBook", "Error")
                    }

                    is Resource.Loading -> {
                        //nothing really happens
                    }
                }
            }.launchIn(this)
        }
    }

    fun insertBookToDatabase(volumeId: String) {
        viewModelScope.launch {
            val book = withContext(Dispatchers.Default) {
                repo.findBookById(volumeId)
            }
            withContext(Dispatchers.Default) {
                repo.insertBook(book)
            }
        }
    }
}