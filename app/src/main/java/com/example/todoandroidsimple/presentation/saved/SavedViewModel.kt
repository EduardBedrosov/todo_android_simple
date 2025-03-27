package com.example.todoandroidsimple.presentation.saved

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
class SavedViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel() {

    private val _books = MutableStateFlow<List<BookEntity>>(emptyList())
    val books = _books.asStateFlow()


    init {
        getAllSavedBooks()
    }

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }

    private fun getAllSavedBooks() {
        viewModelScope.launch {
            repository.getSavedBooks().collect { savedBookList->
                _books.value = savedBookList
            }
        }
    }
}