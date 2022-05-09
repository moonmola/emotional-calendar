package com.moonmola.emotional_calendar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.data.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryListViewModel @Inject constructor(
    diaryRepository: DiaryRepository
) : ViewModel() {
    val diaries: LiveData<List<Diary>> = diaryRepository.getDiaries().asLiveData()
}