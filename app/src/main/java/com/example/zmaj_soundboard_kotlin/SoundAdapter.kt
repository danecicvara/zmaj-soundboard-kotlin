package com.example.zmaj_soundboard_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class SoundAdapter(
    private val soundList: List<Int>,
    private val onSoundClick: (Int) -> Unit
) : RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {

    class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val soundButton: Button = itemView.findViewById(R.id.soundButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.soundButton.text = "Sound ${position + 1}"
        holder.soundButton.setOnClickListener {
            onSoundClick(soundList[position])
        }
    }

    override fun getItemCount(): Int = soundList.size
}
