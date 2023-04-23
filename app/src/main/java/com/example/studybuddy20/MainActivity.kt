package com.example.studybuddy20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mathButton: Button
    private lateinit var StopBtn: Button
    private lateinit var csButton: Button
    private lateinit var startButton: Button
    private lateinit var countdownTV: TextView

    private var countdownLength: Long = 0
    private var countdown: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathButton = findViewById(R.id.Mathbtn)
        csButton = findViewById(R.id.CSbtn)
        startButton = findViewById(R.id.StartBtn)
        countdownTV = findViewById(R.id.countdownTV)
        StopBtn = findViewById(R.id.StopBtn)

        mathButton.setOnClickListener(this)
        csButton.setOnClickListener(this)
        startButton.setOnClickListener(this)

        countdownTV.text = "0"
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.Mathbtn -> {
                countdownLength = 20 * 60 * 1000 // 20 minutes
                countdownTV.text = "20:00"
            }
            R.id.CSbtn -> {
                countdownLength = 15 * 60 * 1000 // 15 minutes
                countdownTV.text = "15:00"
            }
            R.id.StopBtn -> {
                countdown?.cancel()
                countdownLength = 0
                countdownTV.text = "00:00"
            }
            R.id.StartBtn -> startCountdown()
        }
    }

    private fun startCountdown() {
        countdown?.cancel()

        countdown = object : CountDownTimer(countdownLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                countdownTV.text = formattedTime
            }

            override fun onFinish() {
                countdownTV.text = "00:00"
            }
        }.start()
    }


}