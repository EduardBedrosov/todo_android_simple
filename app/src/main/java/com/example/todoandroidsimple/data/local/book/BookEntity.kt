package com.example.todoandroidsimple.data.local.book

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val authors: String,
    val description: String?,
    val publishedDate: String?,
    val thumbnail: String?,
    val previewLink: String?,
    val thumbnailLocalUri: String? = null,
    val isImageDownloaded: Boolean = false
)
