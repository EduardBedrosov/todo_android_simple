package com.example.todoandroidsimple.data.local.images

import android.content.Context
import android.graphics.Bitmap
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.facebook.common.executors.CallerThreadExecutor
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import kotlin.coroutines.resume
import androidx.core.net.toUri
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.imagepipeline.image.CloseableImage

 interface  ImageDownloader{
     suspend fun download(imageUrl: String?):String?
 }

class ImageDownloaderImpl1(
    private val context: Context
):ImageDownloader {

    override suspend fun download(imageUrl: String?):String? {
        return suspendCancellableCoroutine { continuation ->
            val uri = imageUrl?.toUri()
            val imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .build()

            val dataSource = Fresco.getImagePipeline().fetchDecodedImage(imageRequest, context)

            dataSource.subscribe(object : BaseBitmapDataSubscriber() {
                override fun onNewResultImpl(bitmap: Bitmap?) {
                    if (bitmap != null) {
                        try {
//                            val file = File(context.cacheDir, "${UUID.randomUUID()}.jpg")
                            val imagesDir = File(context.filesDir, "book_images").apply { mkdirs() }
                            val file = File(imagesDir, "${UUID.randomUUID()}.jpg")
                            FileOutputStream(file).use { out ->
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                            }
                            continuation.resume(file.absolutePath)
                        } catch (e: Exception) {
                            continuation.resume(null)
                        }
                    } else {
                        continuation.resume(null)
                    }
                }

                override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage>>) {
                    TODO("Not yet implemented")
                }

            }, CallerThreadExecutor.getInstance())
        }
    }
}
