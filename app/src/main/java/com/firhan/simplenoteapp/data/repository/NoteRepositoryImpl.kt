package com.firhan.simplenoteapp.data.repository

import com.firhan.simplenoteapp.data.local.NoteDao
import com.firhan.simplenoteapp.data.local.toEntity
import com.firhan.simplenoteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        // Translasi otomatis: List<NoteEntity> -> List<Note>
        return dao.getNotes().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.toDomain()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }
}