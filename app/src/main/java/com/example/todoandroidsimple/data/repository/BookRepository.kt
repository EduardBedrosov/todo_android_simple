package com.example.todoandroidsimple.data.repository

import com.example.todoandroidsimple.data.local.book.BookDao
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.remote.GoogleBooksApi
import com.example.todoandroidsimple.data.remote.dto.BookDto
import com.example.todoandroidsimple.data.remote.dto.toBookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val googleBooksApi: GoogleBooksApi
) {
    fun getSavedBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    suspend fun insertBook(book: BookEntity) {
        bookDao.insertBook(book)
    }

    suspend fun getBookById(bookId: String): BookEntity {
        return googleBooksApi.getBookById(bookId).toBookEntity()
    }

    suspend fun deleteBook(book: BookEntity) {
        bookDao.deleteBook(book)
    }

    suspend fun searchBooks(query: String): List<BookEntity> {
        val response = googleBooksApi.searchBooks(query)
        return response.items?.map { it.toBookEntity() } ?: emptyList()
    }

}
