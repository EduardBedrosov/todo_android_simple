package com.example.todoandroidsimple.data.remote

import com.example.todoandroidsimple.data.remote.dto.BookDto
import com.example.todoandroidsimple.data.remote.dto.BookResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookResponseDto

    @GET("volumes/{bookId}")
    suspend fun getBookById(@Path("bookId") bookId: String): BookDto
}
