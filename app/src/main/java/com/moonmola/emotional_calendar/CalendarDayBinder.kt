package com.moonmola.emotional_calendar

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.databinding.ItemCalendarDayBinding
import com.moonmola.emotional_calendar.viewmodels.CalendarViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class CalendarDayBinder (private val calendarView: CalendarView
): DayBinder<CalendarDayBinder.DayContainer> {
    private var calendar:  Map<String,Int> = HashMap<String,Int>()
    var input: Input? = null

    fun updateCalendar(
        calendar: Map<String,Int>,
    ) {
        this.calendar = calendar
        this.calendarView.notifyCalendarChanged()
    }

    override fun create(view: View): DayContainer {
        val binding = ItemCalendarDayBinding.bind(view)
        return DayContainer(binding)
    }

    override fun bind(container: DayContainer, day: CalendarDay) {
        calendar[day.date.format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일").withLocale(Locale.forLanguageTag("ko")))]?.let {
                container.binding.dayEmoji.background = ContextCompat.getDrawable(calendarView.context,it)
                container.binding.dayEmoji.visibility = View.VISIBLE
        }?: run{
                container.binding.dayEmoji.visibility = View.GONE

        }

        container.binding.tvDay.text = day.date.dayOfMonth.toString()

        container.binding.root.setOnClickListener {
            input?.onDayClick(day.date)
        }
        day.date.year
        if (day.owner != DayOwner.THIS_MONTH){
            container.binding.tvDay.setTextColor(ContextCompat.getColor(calendarView.context,R.color.gray))
        }else {
            when(day.date.dayOfWeek){
                DayOfWeek.SUNDAY -> {
                    container.binding.tvDay.setTextColor(ContextCompat.getColor(calendarView.context,R.color.red))
                }
                DayOfWeek.SATURDAY -> {
                    container.binding.tvDay.setTextColor(ContextCompat.getColor(calendarView.context,R.color.blue))
                }
                else -> {
                    container.binding.tvDay.setTextColor(ContextCompat.getColor(calendarView.context,R.color.black))
                }
            }

        }
    }


    class DayContainer(
        val binding: ItemCalendarDayBinding
    ) : ViewContainer(binding.root)

    abstract class Input {
        abstract fun onDayClick(date: LocalDate)
    }
}