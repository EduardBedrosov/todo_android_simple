package com.example.todoandroidsimple.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.local.worker.DownloadBooksWorker
import com.example.todoandroidsimple.data.remote.dto.toBookItems
import com.example.todoandroidsimple.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: BookRepository,
    private val workManager: WorkManager
) : ViewModel() {


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

//    fun downloadAllBooks() {
//        viewModelScope.launch {
//            _bookEntity.value.filter { !it.isImageDownloaded }.forEach { book ->
//                repository.insertBook(book)
//            }
//        }
//    }

    fun downloadAllBooks() {
        viewModelScope.launch {
            val request = OneTimeWorkRequestBuilder<DownloadBooksWorker>().build()
            workManager.enqueue(request)
        }
    }


        fun saveBook(bookId: String) {
            viewModelScope.launch {
                _bookEntity.value.find { it.id == bookId }?.let { bookEntity ->
                    repository.insertBook(bookEntity)
                }
            }
        }

        private fun getAllSavedBooks() {
            viewModelScope.launch {
                repository.getSavedBooks().collect { savedBookList ->
                    _books.value = savedBookList.toBookItems()
                    _bookEntity.value = savedBookList
                }
            }
        }
    }