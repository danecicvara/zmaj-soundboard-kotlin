package com.example.zmaj_soundboard_kotlin

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imageView: ImageView
    private val soundList = listOf(
        R.raw.kasalj,
        R.raw.bjezi_dragoja,
        R.raw.ojebine
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playRandomButton: Button = findViewById(R.id.playRandomButton)
        imageView = findViewById(R.id.imageView)

        playRandomButton.setOnClickListener {
            playRandomSound(soundList.random())
        }
    }

    private fun playRandomSound(soundResId: Int) {
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.release()
        }

        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer.start()

        // Change the image to the "playing" state
        imageView.setImageResource(R.drawable.zmaj_prica200)

        // Set an OnCompletionListener to reset the image when the sound finishes
        mediaPlayer.setOnCompletionListener {
            resetImage()
        }
    }

    private fun resetImage() {
        imageView.setImageResource(R.drawable.zmaj_cuti200)
    }
}