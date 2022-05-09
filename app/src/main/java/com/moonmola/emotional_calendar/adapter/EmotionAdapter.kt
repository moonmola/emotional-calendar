package com.moonmola.emotional_calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.moonmola.emotional_calendar.databinding.ItemEmotionBinding
import javax.inject.Inject
import com.moonmola.emotional_calendar.data.Emotion


class EmotionAdapter@Inject constructor():
    RecyclerView.Adapter<EmotionAdapter.VH>() {
    var itemList: List<Emotion> = ArrayList()
    var callback: ClickListener? = null

    inner class VH(private val binding: ItemEmotionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emotion: Emotion) {
            binding.emotion.background = ContextCompat.getDrawable(binding.emotion.context, emotion.drawable)
            binding.textEmotion.text = emotion.name
            binding.root.setOnClickListener {
                callback?.onEmotionClick(emotion)
                binding.emotion.animate()
                    .setDuration(300)
                    .scaleX(1.2f)
                    .scaleY(1.2f)
                    .withEndAction {
                        binding.emotion.scaleX = 1f
                        binding.emotion.scaleY = 1f}
                    .start()
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