package com.moonmola.emotional_calendar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "diaries")
data class Diary (
    @PrimaryKey @ColumnInfo(name = "id") val date: String,
    val emotionId: Int,
    val emotionGifId: Int,
    val week: String,
    val content: String
):Serializable
