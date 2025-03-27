package com.example.todoandroidsimple.data.repository

import com.example.todoandroidsimple.data.local.note.NoteEntity
import com.example.todoandroidsimple.data.local.note.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    suspend fun insertNote(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }
}
