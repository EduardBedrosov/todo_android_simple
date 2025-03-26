package com.example.todoandroidsimple.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BookResponseDto(
    @SerializedName("items") val items: List<BookDto>?
)
