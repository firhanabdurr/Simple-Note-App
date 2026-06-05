package com.firhan.simplenoteapp.domain.usecase

import com.firhan.simplenoteapp.data.repository.NoteRepository
import com.firhan.simplenoteapp.domain.model.Note

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}