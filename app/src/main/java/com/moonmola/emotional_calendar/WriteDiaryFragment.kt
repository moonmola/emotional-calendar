package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.data.Emotion
import com.moonmola.emotional_calendar.databinding.FragmentWriteDiaryBinding
import com.moonmola.emotional_calendar.viewmodels.WriteDiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class WriteDiaryFragment: Fragment() {
    private val args: WriteDiaryFragmentArgs by navArgs()
    private var currentEmotion: Emotion? = null
    private lateinit var binding: FragmentWriteDiaryBinding
    private val viewModel: WriteDiaryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteDiaryBinding.inflate(inflater, container, false)
        this.currentEmotion = args.currentEmotion

        binding.doneButton.setOnClickListener {
            currentEmotion?.let {currentEmotion->
                viewModel.insertDiary(Diary(emotionId = currentEmotion.drawable,
                    emotionGifId = currentEmotion.gif,
                    date = binding.diaryDate.text.toString(),
                    week = binding.diaryWeek.text.toString(),
                    content = binding.editWriteDiary.text.toString()))
            }
            val direction = WriteDiaryFragmentDirections.actionWriteDDiaryFragmentToHomeViewPagerFragment()
            findNavController().navigate(direction)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide.with(binding.root)
                .load(currentEmotion?.gif)
                .into(binding.imageWriteEmotion)
        }
        binding.diaryDate.text = args.date.format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일").withLocale(Locale.forLanguageTag("ko")))
        binding.diaryWeek.text = args.date.format(DateTimeFormatter.ofPattern("E요일").withLocale(Locale.forLanguageTag("ko")))

    }



}