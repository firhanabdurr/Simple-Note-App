package com.firhan.simplenoteapp.domain.usecase

import com.firhan.simplenoteapp.data.repository.NoteRepository
import com.firhan.simplenoteapp.domain.model.Note

class GetNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}