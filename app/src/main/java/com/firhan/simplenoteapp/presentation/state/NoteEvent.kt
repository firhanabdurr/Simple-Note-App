package com.firhan.simplenoteapp.presentation.state

import com.firhan.simplenoteapp.domain.model.Note
import com.firhan.simplenoteapp.domain.util.NoteOrder

sealed interface NoteEvent {
    data class AddNote(val title: String, val content: String, val color: Int) : NoteEvent
    data class Order(val noteOrder: NoteOrder) : NoteEvent
    data class DeleteNote(val note: Note) : NoteEvent
    object RestoreNote : NoteEvent
    object ToggleOrderSection : NoteEvent
}