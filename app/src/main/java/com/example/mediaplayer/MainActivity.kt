package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var musicPlayer : MediaPlayer
    var totalTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val btnPlay = findViewById<ImageView>(R.id.ivPlay)
        val btnPause = findViewById<ImageView>(R.id.ivPause)
        val btnStop = findViewById<ImageView>(R.id.ivStop)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        musicPlayer = MediaPlayer.create(this, R.raw.gorgeous)
        musicPlayer.setVolume(1f, 1f)
        totalTime = musicPlayer.duration

        btnPlay.setOnClickListener {
            musicPlayer.start()
        }

        btnPause.setOnClickListener {
            musicPlayer.pause()
        }

        btnStop.setOnClickListener {
            musicPlayer.stop()
        }

        //Code if we change the seekBar position then, it will be observed

        seekBar.max = totalTime
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) {
                    musicPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        //Code so that seekBar moves with the flow of music
        val handler = Handler()
        handler.postDelayed( object : Runnable{

            override fun run() {
                seekBar.progress = musicPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }

        }, 0)
    }
}