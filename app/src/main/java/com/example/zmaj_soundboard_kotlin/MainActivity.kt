package com.example.zmaj_soundboard_kotlin

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imageView: ImageView
    private lateinit var soundRecyclerView: RecyclerView
    private val soundList by lazy {
        val rawClass = R.raw::class.java
        rawClass.fields.mapNotNull { field ->
            try {
                field.getInt(null) // Get resource ID
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

        val expandListButton: Button = findViewById(R.id.expandListButton)

        setupRecyclerView()

        imageView.setOnClickListener {
            playRandomSound(soundList.random())
        }

        expandListButton.setOnClickListener {
            toggleSoundList()
        }
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