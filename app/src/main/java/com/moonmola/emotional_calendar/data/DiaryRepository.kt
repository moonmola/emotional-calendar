package com.moonmola.emotional_calendar.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryRepository@Inject constructor(private val diaryDao: DiaryDao) {
    fun getDiaries() = diaryDao.getDiaries()
    fun getCalendar() = diaryDao.getDiariesMap()
    fun getDiary(diaryId: String) = diaryDao.getDiary(diaryId)
    suspend fun insertDiary(diary: Diary) = diaryDao.insert(diary)

}