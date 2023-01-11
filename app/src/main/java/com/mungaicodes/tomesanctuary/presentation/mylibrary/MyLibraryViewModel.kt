package com.mungaicodes.tomesanctuary.presentation.mylibrary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyLibraryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyLibraryUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            val myLibrary = repo.getMyLibrary()
            _uiState.update {
                it.copy(myLibrary = myLibrary)
            }
        }
    }

    fun insertBookToDatabase() {

    }


}