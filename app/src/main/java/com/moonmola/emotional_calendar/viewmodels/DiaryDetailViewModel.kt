package com.moonmola.emotional_calendar.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.data.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    var currentDiary = MutableLiveData<Diary>()
    fun getDiary(date: String){

        CoroutineScope(Dispatchers.IO).launch {
            currentDiary.postValue(diaryRepository.getDiary(date))
        }
//        viewModelScope.launch {
//            currentDiary.postValue(diaryRepository.getDiary(date))
//        }
    }
}