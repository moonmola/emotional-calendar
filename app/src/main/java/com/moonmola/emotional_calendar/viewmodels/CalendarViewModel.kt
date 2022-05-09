package com.moonmola.emotional_calendar.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.moonmola.emotional_calendar.HomeViewPagerFragmentDirections
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.data.DiaryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.YearMonth


@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    var currentYearMonth = MutableLiveData<YearMonth>()
    var calendar = MutableLiveData<Map<String,Int>>()


    fun onScrollMonth(current:YearMonth){
        this.currentYearMonth.value = current
    }


    fun getCalendar(){
        val map = mutableMapOf<String,Int>()
        CoroutineScope(Dispatchers.IO).launch {
            diaryRepository.getCalendar().forEach {
                map[it.date] = it.emotionId
            }
            calendar.postValue(map)
        }
    }

}