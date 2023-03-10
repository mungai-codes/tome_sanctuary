package com.mungaicodes.tomesanctuary.presentation.category

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import com.mungaicodes.tomesanctuary.util.Resource
import com.mungaicodes.tomesanctuary.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryResultsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryResultsUiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                repo.getSearchResults(query + "subject:", "partial").onEach { result ->
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


