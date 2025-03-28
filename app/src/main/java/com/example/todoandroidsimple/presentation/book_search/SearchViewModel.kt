package com.example.todoandroidsimple.presentation.book_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.remote.dto.toBookItems
import com.example.todoandroidsimple.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _searchValue = MutableStateFlow<String>("")
    val searchValue = _searchValue.asStateFlow()

    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books = _books.asStateFlow()

    private val _bookEntity = MutableStateFlow<List<BookEntity>>(emptyList())

    init {
        viewModelScope.launch {
            _searchValue.debounce(500).collect { query ->
                if (query.isNotBlank()) {
                    searchBooks(query)
                }
            }
        }
    }

    fun updateSearchValue(newValue: String) {
        _searchValue.value = newValue
    }

    private fun searchBooks(query: String) {
        viewModelScope.launch {
            val results = repository.searchBooks(query)
            _books.value = results.toBookItems()
            _bookEntity.value = results
        }
    }

    fun saveBookById(bookId: String) {
        viewModelScope.launch {
            val bookEntity = repository.getBookById(bookId)
            repository.insertBook(bookEntity)
        }
    }

    //ete henc entityov petq lini save anel steghic harc
    fun saveBook(bookId: String) {
        viewModelScope.launch {
            _bookEntity.value.find { it.id == bookId }?.let { bookEntity ->
                repository.insertBook(bookEntity)
            }
        }
    }

}
