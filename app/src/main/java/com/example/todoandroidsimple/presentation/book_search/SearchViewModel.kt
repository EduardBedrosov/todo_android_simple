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
import kotlinx.coroutines.flow.collect
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



    init {
        viewModelScope.launch {
            _books.debounce(1500).collect{

            }
        }
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            println("asdasdasdasdquery");
            println(query);
            val results = repository.searchBooks(query)
            _books.value = results.toBookItems()
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


    //rememberner@ qcel viewmodel
    //dataclass sarqel
    //myus eji hamar arandzin id-ov kanchel
}
