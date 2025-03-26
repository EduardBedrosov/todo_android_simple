package com.example.todoandroidsimple.data.remote.dto

import com.example.todoandroidsimple.data.local.book.BookEntity

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        title = this.volumeInfo.title ?: "Unknown Title",
        authors = this.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
        description = this.volumeInfo.description ?: "No description available",
        publishedDate = this.volumeInfo.publishedDate ?: "Unknown Date",
        thumbnail = this.volumeInfo.imageLinks?.thumbnail ?: "",
        previewLink = this.volumeInfo.previewLink ?: ""
    )
}
