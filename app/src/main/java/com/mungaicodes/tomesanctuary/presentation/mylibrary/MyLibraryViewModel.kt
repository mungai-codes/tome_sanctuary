package com.mungaicodes.tomesanctuary.presentation.mylibrary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.tomesanctuary.domain.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyLibraryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyLibraryUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val myLibrary = repo.getMyLibrary()
            _uiState.update {
                it.copy(myLibrary = myLibrary)
            }
        }
    }

    fun deleteBookFromLibrary(bookId: String) {
        viewModelScope.launch {
            val book = withContext(Dispatchers.Default) {
                bookId.let {
                    repo.getBookById(bookId)
                }
            }
            withContext(Dispatchers.Default) {
                repo.deleteBook(
                    book = book
                )
            }


        }
    }


}