package com.moonmola.emotional_calendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diaries")
data class Diary (
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "id") val diaryId: Int = 0,
    val emotionId: Int,
    val date: String,
    val week: String,
    val content: String
)
