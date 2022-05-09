package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.ui.MonthScrollListener
import com.moonmola.emotional_calendar.databinding.FragmentCalendarBinding
import com.moonmola.emotional_calendar.viewmodels.CalendarViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var binder: CalendarDayBinder
    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        subscribeUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendarView()
    }

    private fun subscribeUi() {
        viewModel.currentYearMonth.observe(viewLifecycleOwner) { yearMonth ->
            binding.textYear.text = yearMonth.year.toString()
            val outputFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM")
            binding.textMonth.text = yearMonth.format(outputFormat)
        }
        viewModel.calendar.observe(viewLifecycleOwner) { calendar ->
            binder.updateCalendar(calendar)
        }
    }

    private fun initCalendarView() = with(binding) {
        binder = CalendarDayBinder(binding.calendarView).apply {
            input = object : CalendarDayBinder.Input() {
                override fun onDayClick(date: LocalDate) = dayClick(date)
            }
        }
        calendarView.monthScrollListener = object : MonthScrollListener {
            override fun invoke(date: CalendarMonth) {
                viewModel.onScrollMonth(date.yearMonth)
            }

        }
        calendarView.dayBinder = binder

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(240) // past 20 year
        val lastMonth = currentMonth.plusMonths(12) // future 1 year

        calendarView.setup(firstMonth, lastMonth, WeekFields.of(Locale.getDefault()).firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)
        viewModel.getCalendar()

    }

    private fun dayClick(date: LocalDate) {
        val diaryId = date.format(
            DateTimeFormatter.ofPattern("YYYY년 MM월 dd일").withLocale(Locale.forLanguageTag("ko"))
        )
        viewModel.calendar.value?.get(diaryId)?.let {
            navigateToDiaryDetail(diaryId)
        } ?: run {
            navigateToWriteDiary(date)
        }
    }

    private fun navigateToWriteDiary(date: LocalDate) {
        val direction =
            HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToChooseEmotionFragment(date)
        findNavController().navigate(direction)
    }

    private fun navigateToDiaryDetail(diaryId: String) {
        val direction =
            HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToDiaryDetailFragment(diaryId)
        findNavController().navigate(direction)
    }

}