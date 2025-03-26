package com.example.todoandroidsimple.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageLinksDto(
    @SerializedName("thumbnail") val thumbnail: String?
)
