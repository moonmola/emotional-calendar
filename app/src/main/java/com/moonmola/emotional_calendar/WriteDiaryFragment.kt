package com.moonmola.emotional_calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moonmola.emotional_calendar.data.Emotion
import com.moonmola.emotional_calendar.adapter.EmotionAdapter
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.databinding.FragmentWriteDiaryBinding
import com.moonmola.emotional_calendar.viewmodels.CalendarViewModel
import com.moonmola.emotional_calendar.viewmodels.WriteDiaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WriteDiaryFragment: Fragment() ,EmotionAdapter.ClickListener {
    private val args: WriteDiaryFragmentArgs by navArgs()
    private var currentEmotion: Emotion? = null
    private lateinit var binding: FragmentWriteDiaryBinding
    @Inject
    lateinit var itemAdapter: EmotionAdapter
    private val viewModel: WriteDiaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteDiaryBinding.inflate(inflater, container, false)
        val items = ArrayList<Emotion>()
        items.add(Emotion("Love", R.drawable.love))
        items.add(Emotion("Soso", R.drawable.soso))
        items.add(Emotion("Sad", R.drawable.sad))
        items.add(Emotion("Gloom", R.drawable.gloom))
        items.add(Emotion("Angry", R.drawable.angry))
        with(binding.recyclerEmotion) {
            itemAdapter.itemList = items
            itemAdapter.callback = this@WriteDiaryFragment
            adapter = itemAdapter
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        }
        binding.nextButton.setOnClickListener {
            binding.frameChooseEmotion.visibility = View.GONE
        }
        binding.doneButton.setOnClickListener {
            currentEmotion?.let {currentEmotion->
                viewModel.insertDiary(Diary(emotionId = currentEmotion.drawable,
                    date = binding.diaryDate.text.toString(),
                    week = binding.diaryWeek.text.toString(),
                    content = binding.editWriteDiary.text.toString()))
            }
            val direction = WriteDiaryFragmentDirections.actionWriteDiaryFragmentToHomeViewPagerFragment()
            findNavController().navigate(direction)
        }

//        subscribeUi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.diaryDate.text = args.date.format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일").withLocale(Locale.forLanguageTag("ko")))
        binding.diaryWeek.text = args.date.format(DateTimeFormatter.ofPattern("E요일").withLocale(Locale.forLanguageTag("ko")))
        binding.nextButton.isEnabled = false


    }
    private fun subscribeUi() {
//        viewModel.currentYearMonth.observe(viewLifecycleOwner) { yearMonth ->
//            binding.textYear.text = yearMonth.year.toString()
//            val outputFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM")
//            binding.textMonth.text = yearMonth.format(outputFormat)
//        }
    }

    override fun onEmotionClick(emotion: Emotion) {
        context?.let {
            binding.imageWriteEmotion.background = ContextCompat.getDrawable(it, emotion.drawable)
        }
        currentEmotion = emotion
        binding.nextButton.isEnabled = true

    }


}