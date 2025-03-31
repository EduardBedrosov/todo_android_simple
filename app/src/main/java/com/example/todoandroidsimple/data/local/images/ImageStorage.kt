//package com.example.todoandroidsimple.data.local.images
//
//import android.content.Context
//import java.io.File
//import java.io.FileOutputStream
//import java.io.InputStream
//import java.net.HttpURLConnection
//import java.net.URL
//import java.util.UUID
//
//object ImageStorage {
//
//    fun downloadAndSaveImage(context: Context, imageUrl: String?): String? {
//        if (imageUrl == null) return null
//
//        return try {
//            println("ppppp Image download started: $imageUrl")
//
//            val url = URL(imageUrl)
//            val connection = url.openConnection() as HttpURLConnection
//            connection.connect()
//
//            val input: InputStream = connection.inputStream
//            val fileName = "${UUID.randomUUID()}.jpg"
//            val file = File(context.filesDir, fileName)
//
//            FileOutputStream(file).use { output ->
//                input.copyTo(output)
//            }
//
//            println("ppppp Image saved at: ${file.absolutePath}")
//            file.absolutePath
//
//        } catch (e: Exception) {
//            println("ppppp Image download failed: ${e.localizedMessage}")
//            null
//        }
//    }
//}
