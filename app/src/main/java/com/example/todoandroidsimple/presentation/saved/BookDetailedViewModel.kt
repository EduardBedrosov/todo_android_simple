package com.example.todoandroidsimple.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroidsimple.data.BookDetailItem
import com.example.todoandroidsimple.data.remote.dto.toBookDetailItem
import com.example.todoandroidsimple.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailedViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _book = MutableStateFlow<BookDetailItem?>(null)
    val book: StateFlow<BookDetailItem?> = _book


    fun loadBook(bookId: String) {
        viewModelScope.launch {
            val bookEntity = repository.getBook(bookId)
            _book.value = bookEntity?.toBookDetailItem()
        }
    }
}
