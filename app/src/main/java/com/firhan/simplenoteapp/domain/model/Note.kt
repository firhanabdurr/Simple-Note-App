package com.firhan.simplenoteapp.domain.model

data class Note(
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(
            0xFFFFAB91.toInt(),
            0xFFF48FB1.toInt(),
            0xFF81DEEA.toInt(),
            0xFFCFD8DC.toInt(),
            0xFFFFF59D.toInt()
        )
    }
}