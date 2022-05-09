package com.moonmola.emotional_calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

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
            input = object : CalendarDayBinder.Input(){
                override fun onDayClick(date: LocalDate) = dayClick(date)
            }
        }
        calendarView.monthScrollListener = object: MonthScrollListener {
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

    private fun dayClick(date: LocalDate){
//        binder.updateCalendar(calendar)
        navigateToWriteDiary(date)
    }
    private fun navigateToWriteDiary(date: LocalDate){
        val direction = HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToWriteDiaryFragment(date)
        findNavController().navigate(direction)

    }

}