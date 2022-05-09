package com.moonmola.emotional_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moonmola.emotional_calendar.data.Emotion
import com.moonmola.emotional_calendar.adapter.EmotionAdapter
import com.moonmola.emotional_calendar.databinding.FragmentChooseEmotionBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ChooseEmotionFragment: Fragment() ,EmotionAdapter.ClickListener {
    private val args: ChooseEmotionFragmentArgs by navArgs()
    private var currentEmotion: Emotion? = null
    private lateinit var binding: FragmentChooseEmotionBinding
    @Inject
    lateinit var itemAdapter: EmotionAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseEmotionBinding.inflate(inflater, container, false)
        val items = ArrayList<Emotion>()
        items.add(Emotion("Good", R.drawable.good, R.raw.ani_good))
        items.add(Emotion("Soso", R.drawable.soso, R.raw.ani_soso))
        items.add(Emotion("Sad", R.drawable.sad, R.raw.ani_sad))
        items.add(Emotion("Gloom", R.drawable.gloom, R.raw.ani_gloom))
        items.add(Emotion("Angry", R.drawable.angry, R.raw.ani_angry))
        with(binding.recyclerEmotion) {
            itemAdapter.itemList = items
            itemAdapter.callback = this@ChooseEmotionFragment
            adapter = itemAdapter
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        }
        binding.nextButton.setOnClickListener {
            val direction = ChooseEmotionFragmentDirections.actionChooseEmotionFragmentToWriteDiaryFragment(args.date, currentEmotion)
            findNavController().navigate(direction)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.isEnabled = false

    }

    override fun onEmotionClick(emotion: Emotion) {
        currentEmotion = emotion
        binding.nextButton.isEnabled = true

    }


}