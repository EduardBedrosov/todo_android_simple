package com.example.todoandroidsimple.presentation.book_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _books = MutableStateFlow<List<BookEntity>>(emptyList())
    val books = _books.asStateFlow()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            println("22222");
            val results = repository.searchBooks(query)
            _books.value = results
            println("33333333");

        }
    }

    fun saveBook(book: BookEntity) {
        viewModelScope.launch {
            repository.insertBook(book)
        }
    }

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }

}
