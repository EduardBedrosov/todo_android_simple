package com.example.todoandroidsimple.data.local.book

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoandroidsimple.data.local.note.NoteDao
import com.example.todoandroidsimple.data.local.note.NoteEntity

@Database(
    version = 1,
    entities = [BookEntity::class, NoteEntity::class],
    exportSchema = true,
    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
    ]
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    abstract fun noteDao(): NoteDao
}
