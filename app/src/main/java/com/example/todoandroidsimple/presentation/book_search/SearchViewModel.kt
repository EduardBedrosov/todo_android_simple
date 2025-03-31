package com.example.todoandroidsimple.presentation.book_search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.remote.dto.toBookItems
import com.example.todoandroidsimple.data.repository.BookRepository
import com.example.todoandroidsimple.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: BookRepository,
    private val networkUtils: NetworkUtils,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val _searchValue = MutableStateFlow<String>("")
    val searchValue = _searchValue.asStateFlow()

    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books = _books.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

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
            if (!networkUtils.isConnected()) {
                _error.value = "No internet connection"
                return@launch
            }

            try {
                val results = repository.searchBooks(query)
                _books.value = results.toBookItems()
                _bookEntity.value = results
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Something went wrong"
            }
        }
    }

    fun saveBook(bookId: String) {
        viewModelScope.launch {
            _bookEntity.value.find { it.id == bookId }?.let { bookEntity ->
                repository.insertBook(bookEntity, appContext)
            }
        }
    }

}
