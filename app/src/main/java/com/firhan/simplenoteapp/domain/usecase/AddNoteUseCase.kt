package com.firhan.simplenoteapp.domain.usecase

import com.firhan.simplenoteapp.data.repository.NoteRepository
import com.firhan.simplenoteapp.domain.model.InvalidNoteException
import com.firhan.simplenoteapp.domain.model.Note
import kotlin.jvm.Throws

//class AddNoteUseCase(
//    private val repository: NoteRepository
//) {
//    @Throws(InvalidNoteException::class)
//    suspend operator fun invoke(note: Note) {
//        if (note.title.isBlank()) {
//            throw InvalidNoteException("Judul catatan gaboleh kosong bray.")
//        } else if (note.content.isBlank()) {
//            throw InvalidNoteException("Isi catatan gaboleh kosong juga bray")
//        }
//        repository.insertNote(note)
//    }
//}

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Judul catatan gak boleh kosong bray.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Isi catatan gak boleh kosong bray.")
        }
        repository.insertNote(note)
    }
}