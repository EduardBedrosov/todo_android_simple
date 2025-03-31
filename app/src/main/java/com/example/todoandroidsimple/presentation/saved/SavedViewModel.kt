package com.example.todoandroidsimple.presentation.saved

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.remote.dto.toBookEntity
import com.example.todoandroidsimple.data.remote.dto.toBookItems
import com.example.todoandroidsimple.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: BookRepository,
): ViewModel() {


    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books = _books.asStateFlow()
    private val _bookEntity = MutableStateFlow<List<BookEntity>>(emptyList())

    init {
        getAllSavedBooks()
    }

    fun deleteBook(bookId: String) {
        viewModelScope.launch {
            _bookEntity.value.find { it.id == bookId }?.let { bookEntity ->
                repository.deleteBook(bookEntity)
            }
        }
    }

    private fun getAllSavedBooks() {
        viewModelScope.launch {
            repository.getSavedBooks().collect { savedBookList->
                _books.value = savedBookList.toBookItems()
                _bookEntity.value = savedBookList
            }
        }
    }
}