package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.moonmola.emotional_calendar.adapter.CALENDER_PAGE_INDEX
import com.moonmola.emotional_calendar.adapter.DIARY_LIST_PAGE_INDEX
import com.moonmola.emotional_calendar.adapter.PagerAdapter
import com.moonmola.emotional_calendar.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = PagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

//    private fun getTabIcon(position: Int): Int {
//        return when (position) {
//            CALENDER_PAGE_INDEX -> R.drawable.angry
//            DIARY_LIST_PAGE_INDEX -> R.drawable.soso
//            else -> throw IndexOutOfBoundsException()
//        }
//    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            CALENDER_PAGE_INDEX -> getString(R.string.title_calendar)
            DIARY_LIST_PAGE_INDEX -> getString(R.string.title_diary_list)
            else -> null
        }
    }
}