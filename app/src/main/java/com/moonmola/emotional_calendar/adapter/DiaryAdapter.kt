package com.moonmola.emotional_calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moonmola.emotional_calendar.HomeViewPagerFragmentDirections
import com.moonmola.emotional_calendar.data.Diary
import com.moonmola.emotional_calendar.databinding.ItemDiaryBinding
import javax.inject.Inject

class DiaryAdapter@Inject constructor(): ListAdapter<Diary, RecyclerView.ViewHolder>(DiaryDiffCallback()){

    inner class VH(private val binding: ItemDiaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diary: Diary) {
            Glide.with(binding.root)
                .load(diary.emotionGifId)
                .into(binding.emotion)
            binding.textDate.text = diary.date
            binding.textWeek.text = diary.week
            binding.textContent.text = diary.content
            binding.root.setOnClickListener {
                val direction = HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToDiaryDetailFragment(diary.date)
                it.findNavController().navigate(direction)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemDiaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val diary = getItem(position)
        (holder as VH).bind(diary)
    }


}

private class DiaryDiffCallback : DiffUtil.ItemCallback<Diary>() {

    override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
        return oldItem == newItem
    }
}