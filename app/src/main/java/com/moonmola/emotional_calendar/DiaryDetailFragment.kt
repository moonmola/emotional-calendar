package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.moonmola.emotional_calendar.databinding.FragmentDiaryDetailBinding
import com.moonmola.emotional_calendar.viewmodels.DiaryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryDetailFragment : Fragment() {
    private val args: DiaryDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentDiaryDetailBinding
    private val viewModel: DiaryDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiaryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        viewModel.getDiary(args.diaryId)

    }

    private fun subscribeUi() {
        viewModel.currentDiary.observe(viewLifecycleOwner) { diary ->
            binding.diaryDate.text = diary.date
            binding.diaryWeek.text = diary.week
            binding.contentDiary.text = diary.content
            Glide.with(binding.root)
                .load(diary.emotionGifId)
                .into(binding.imageEmotion)
        }
    }
}