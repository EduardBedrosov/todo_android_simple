package com.example.todoandroidsimple.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VolumeInfoDto(
    @SerializedName("title") val title: String?,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinksDto?,
    @SerializedName("previewLink") val previewLink: String?,
    @SerializedName("thumbnailLocalUri") val thumbnailLocalUri: String?,
    @SerializedName("isImageDownloaded") val isImageDownloaded: Boolean?
)
