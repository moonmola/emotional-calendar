package com.moonmola.emotional_calendar.data

import androidx.room.Entity

@Entity
data class Emotion (
    val name: String,
    val drawable: Int
)