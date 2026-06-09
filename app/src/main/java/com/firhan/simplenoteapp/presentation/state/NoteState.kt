package com.firhan.simplenoteapp.presentation.state

import com.firhan.simplenoteapp.domain.model.Note
import com.firhan.simplenoteapp.domain.util.NoteOrder
import com.firhan.simplenoteapp.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
