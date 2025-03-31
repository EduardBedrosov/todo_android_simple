package com.example.todoandroidsimple.data.remote.dto

import com.example.todoandroidsimple.data.BookDetailItem
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.data.local.book.BookEntity

fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        id = this.id,
        title = this.volumeInfo.title ?: "Unknown Title",
        authors = this.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
        description = this.volumeInfo.description ?: "No description available",
        publishedDate = this.volumeInfo.publishedDate ?: "Unknown Date",
        thumbnail = this.volumeInfo.imageLinks?.thumbnail ?: "",
        previewLink = this.volumeInfo.previewLink ?: "",
        thumbnailLocalUri = this.volumeInfo.thumbnailLocalUri ?: ""
    )
}


fun List<BookEntity>.toBookItems(): List<BookItem>{
    return this.map{
        it.toBookItem()
    }
}

fun BookEntity.toBookItem(): BookItem {
    return BookItem(
        bookId = id,
        title = title,
        authors = authors,
    )
}

fun BookEntity.toBookDetailItem(): BookDetailItem {
    return BookDetailItem(
        id = id,
        title = title,
        authors = authors,
        description = description ?: "",
        thumbnail = thumbnail ?: "",
        previewLink = previewLink ?: "",
        thumbnailLocalUri = thumbnailLocalUri ?: ""
    )
}