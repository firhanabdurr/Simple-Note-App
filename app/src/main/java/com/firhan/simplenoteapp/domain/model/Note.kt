package com.firhan.simplenoteapp.domain.model

data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(0xFFFFAB91, 0xFFF48FB1, 0xFF81DEEA, 0xFFCFD8DC, 0xFFFFF59D)
    }
}