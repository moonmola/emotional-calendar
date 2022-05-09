package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moonmola.emotional_calendar.adapter.DiaryAdapter
import com.moonmola.emotional_calendar.databinding.FragmentDiaryListBinding
import com.moonmola.emotional_calendar.viewmodels.DiaryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DiaryListFragment : Fragment() {
    private val viewModel: DiaryListViewModel by viewModels()
    private lateinit var binding: FragmentDiaryListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiaryListBinding.inflate(inflater, container, false)
        val adapter = DiaryAdapter()
        binding.recyclerDiary.layoutManager = LinearLayoutManager(context)
        binding.recyclerDiary.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
        binding.recyclerDiary.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: DiaryAdapter) {
        viewModel.diaries.observe(viewLifecycleOwner) { diaries ->
            adapter.submitList(diaries)
        }
    }
}