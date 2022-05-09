package com.moonmola.emotional_calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moonmola.emotional_calendar.R
import com.moonmola.emotional_calendar.databinding.ItemEmotionBinding
import javax.inject.Inject
import com.moonmola.emotional_calendar.data.Emotion


class EmotionAdapter@Inject constructor():
    RecyclerView.Adapter<EmotionAdapter.VH>() {
    var itemList: List<Emotion> = ArrayList()
    var callback: ClickListener? = null
    var selectedPosition = 10

    inner class VH(private val binding: ItemEmotionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emotion: Emotion) {
            if (position == selectedPosition) {
                Glide.with(binding.root)
                    .load(emotion.gif)
                    .into(binding.emotion)

            } else {
                Glide.with(binding.root)
                    .load(emotion.drawable)
                    .into(binding.emotion)
            }
            binding.textEmotion.text = emotion.name
            binding.root.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()

                callback?.onEmotionClick(emotion)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface ClickListener {
        fun onEmotionClick(clickedItem: Emotion)
    }
}