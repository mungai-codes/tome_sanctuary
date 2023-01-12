package com.mungaicodes.tomesanctuary.presentation.mylibrary.bookpreview

import android.util.Log
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
class BookPreviewViewModel @Inject constructor(
    private val repo: BooksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookPreviewState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            savedStateHandle.get<String>("bookId").let { bookId ->
                val book = withContext(Dispatchers.Default) {
                    bookId?.let {
                        repo.findBookById(it)
                    }
                }
                val previewUrl = withContext(Dispatchers.Default) { book?.volumeInfo?.previewLink }
                Log.i("PreviewLink", previewUrl!!)
                withContext(Dispatchers.Default) {
                    if (previewUrl != null) {
                        _uiState.update {
                            it.copy(
                                bookUrl = previewUrl.replace("http", "https")
                            )
                        }
                    }
                }
            }
        }


    }
}