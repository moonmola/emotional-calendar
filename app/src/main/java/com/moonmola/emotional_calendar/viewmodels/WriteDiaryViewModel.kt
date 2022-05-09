package com.moonmola.emotional_calendar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.data.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteDiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    fun insertDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.insertDiary(diary)
        }
    }

//    suspend fun insertDiary(diary: Diary) {
//        diaryRepository.insertDiary(diary)
//    }

}