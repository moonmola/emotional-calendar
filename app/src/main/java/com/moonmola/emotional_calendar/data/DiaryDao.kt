package com.moonmola.emotional_calendar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diaries ORDER BY id")
    fun getDiaries(): Flow<List<Diary>>

    @Query("SELECT * FROM diaries")
    fun getDiariesMap(): List<Diary>

    @Query("SELECT * FROM diaries WHERE id = :diaryId")
    fun getDiary(diaryId: String): Flow<Diary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(diaries: List<Diary>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diary: Diary)

}