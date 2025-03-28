package com.example.todoandroidsimple.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todoandroidsimple.data.local.book.BookDao
import com.example.todoandroidsimple.data.local.book.BookDatabase
import com.example.todoandroidsimple.data.local.note.NoteDao
import com.example.todoandroidsimple.data.remote.GoogleBooksApi
import com.example.todoandroidsimple.data.repository.BookRepository
import com.example.todoandroidsimple.data.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

//    @Provides
//    @Singleton
//    fun provideNoteDatabase(context: Context): NoteDatabase {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            NoteDatabase::class.java,
//            "notes_db"
//        ).build()
//    }

//    @Provides
//    fun provideNoteDao(database: NoteDatabase) = database.noteDao()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }

    @Provides
    @Singleton
    fun provideBookDatabase(context: Context): BookDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "books_db"
        ).build()
    }


    @Provides
    fun provideBookDao(database: BookDatabase) = database.bookDao()

    @Provides
    fun provideNoteDao(database: BookDatabase) = database.noteDao()

    @Provides
    @Singleton
    fun provideBookRepository(bookDao: BookDao, api: GoogleBooksApi): BookRepository {
        return BookRepository(bookDao, api)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleBooksApi(retrofit: Retrofit): GoogleBooksApi {
        return retrofit.create(GoogleBooksApi::class.java)
    }

}
