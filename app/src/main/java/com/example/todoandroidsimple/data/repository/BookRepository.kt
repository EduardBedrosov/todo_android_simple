package com.example.todoandroidsimple.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.todoandroidsimple.data.local.book.BookDao
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.data.remote.GoogleBooksApi
import com.example.todoandroidsimple.data.remote.dto.toBookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.todoandroidsimple.data.local.images.ImageDownloader
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

@Singleton
class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val googleBooksApi: GoogleBooksApi,
    private val imageDownloader: ImageDownloader
) {
    fun getSavedBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()


    suspend fun insertBook(book: BookEntity) {

        val localPath = imageDownloader.download(book.thumbnail)
        val updatedBook = book.copy(thumbnailLocalUri = localPath ?: "", isImageDownloaded = true )

        bookDao.insertBook(updatedBook)
    }

    suspend fun addBook(book: BookEntity) {
        bookDao.insertBook(book)
    }

    suspend fun getBookById(bookId: String): BookEntity {
        return googleBooksApi.getBookById(bookId).toBookEntity()
    }

    suspend fun getBook(bookId: String): BookEntity? {

        return bookDao.getBook(bookId)
    }

    suspend fun deleteBook(book: BookEntity) {
        bookDao.deleteBook(book)
    }

    suspend fun searchBooks(query: String): List<BookEntity> {
        val response = googleBooksApi.searchBooks(query)
        return response.items?.map { it.toBookEntity() } ?: emptyList()
    }

    suspend fun downloadAllUnsavedImages() {
        val books = bookDao.getAllBooks().first()

        books.filter { !it.isImageDownloaded }.forEach { book ->
            delay(5000)
            val localPath = imageDownloader.download(book.thumbnail)
            if (localPath != null) {
                val updatedBook = book.copy(
                    thumbnailLocalUri = localPath,
                    isImageDownloaded = true
                )
                bookDao.insertBook(updatedBook)
            }
        }
    }

}
