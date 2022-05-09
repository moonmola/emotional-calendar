package com.moonmola.emotional_calendar.data

import android.os.Parcelable
import androidx.room.Entity
import java.io.Serializable

@Entity
data class Emotion (
    val name: String,
    val drawable: Int,
    val gif:Int
):Serializable