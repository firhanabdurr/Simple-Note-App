package com.firhan.simplenoteapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.firhan.simplenoteapp.domain.model.Note

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    // OOP: Method untuk mapping mandiri ke Domain Model
    fun toDomain(): Note {
        return Note(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp,
            color = color
        )
    }
}

// Extension function buat mapping balik dari Domain ke Entity
fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp,
        color = color
    )
}