package com.example.todoandroidsimple.data.local.book

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoandroidsimple.data.local.note.NoteDao
import com.example.todoandroidsimple.data.local.note.NoteEntity

@Database(
    version = 5,
    entities = [BookEntity::class, NoteEntity::class],
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 4, to = 5, spec = BookMigrationFrom4to5::class )
    ]
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun noteDao(): NoteDao
}
