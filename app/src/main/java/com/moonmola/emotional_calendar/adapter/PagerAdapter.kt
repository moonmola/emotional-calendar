package com.moonmola.emotional_calendar.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moonmola.emotional_calendar.CalendarFragment
import com.moonmola.emotional_calendar.DiaryListFragment

const val CALENDER_PAGE_INDEX = 0
const val DIARY_LIST_PAGE_INDEX = 1

class PagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        CALENDER_PAGE_INDEX to { CalendarFragment() },
        DIARY_LIST_PAGE_INDEX to { DiaryListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}