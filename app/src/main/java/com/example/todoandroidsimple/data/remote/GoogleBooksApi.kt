package com.example.todoandroidsimple.data.remote

import com.example.todoandroidsimple.data.remote.dto.BookResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): BookResponseDto
}
