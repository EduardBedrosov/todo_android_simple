package com.example.todoandroidsimple.data

data class BookItem(
    val bookId :String,
    val title :String,
    val authors :String
)

data class BookDetailItem(
    val id: String,
    val title: String,
    val authors: String,
    val description: String,
    val thumbnail: String,
    val previewLink: String,
    val thumbnailLocalUri: String
)