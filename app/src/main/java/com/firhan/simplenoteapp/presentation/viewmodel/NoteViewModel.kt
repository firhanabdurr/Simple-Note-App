package com.firhan.simplenoteapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firhan.simplenoteapp.domain.model.InvalidNoteException
import com.firhan.simplenoteapp.domain.model.Note
import com.firhan.simplenoteapp.domain.usecase.NoteUseCases
import com.firhan.simplenoteapp.domain.util.NoteOrder
import com.firhan.simplenoteapp.domain.util.OrderType
import com.firhan.simplenoteapp.presentation.state.NoteEvent
import com.firhan.simplenoteapp.presentation.state.NoteState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NoteEvent.AddNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = event.title,
                                content = event.content,
                                timestamp = System.currentTimeMillis(),
                                color = event.color
                            )
                        )
                    } catch (e: InvalidNoteException) {
                    }
                }
            }

            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote?.let { note ->
                        noteUseCases.addNote(note)
                        recentlyDeletedNote = null
                    }
                }
            }

            is NoteEvent.ToggleOrderSection -> {
                _state.update {
                    it.copy(isOrderSectionVisible = !it.isOrderSectionVisible)
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.update {
                    it.copy(
                        notes = notes,
                        noteOrder = noteOrder
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}

