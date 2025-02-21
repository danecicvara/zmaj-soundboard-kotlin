package com.example.zmaj_soundboard_kotlin

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imageView: ImageView
    private lateinit var expandListButton: Button
    private lateinit var soundRecyclerView: RecyclerView

    private val soundList by lazy {
        val rawClass = R.raw::class.java
        rawClass.fields.mapNotNull { field ->
            try {
                field.getInt(null)
            } catch (e: Exception) {
                null
            }
        }
    }

    private var isListVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        soundRecyclerView = findViewById(R.id.soundRecyclerView)
        expandListButton= findViewById(R.id.expandListButton)

        setupRecyclerView()
        initializeButtons()
    }
    private fun toggleSoundList() {
        isListVisible = !isListVisible
        soundRecyclerView.visibility = if (isListVisible) View.VISIBLE else View.GONE
    }
    private fun playRandomSound(soundResId: Int) {
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.release()
        }

        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer.start()

        imageView.setImageResource(R.drawable.zmaj_prica200)

        mediaPlayer.setOnCompletionListener {
            resetImage()
        }
    }

    private fun initializeButtons() {
        imageView.setOnClickListener {
            playRandomSound(soundList.random())
        }

        expandListButton.setOnClickListener {
            toggleSoundList()
        }
    }

    private fun setupRecyclerView() {
        soundRecyclerView.layoutManager = LinearLayoutManager(this)
        soundRecyclerView.adapter = SoundAdapter(soundList) { soundResId ->
            playRandomSound(soundResId)
        }
    }

    private fun resetImage() {
        imageView.setImageResource(R.drawable.zmaj_cuti200)
    }
}