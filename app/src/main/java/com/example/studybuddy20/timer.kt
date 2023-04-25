package com.example.studybuddy20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class timer : AppCompatActivity() {

    private var countdownLength: Long = 25 * 60 * 1000
    private var countdown: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        findViewById<Button>(R.id.StartBtn).setOnClickListener {
            // If timer stopped then restarted
            // Timer starts again from 25:00
            if (countdownLength == 0L)
                countdownLength = 25 * 60 * 1000

            startCountdown()
        }

        findViewById<Button>(R.id.StopBtn).setOnClickListener {
            countdown?.cancel()
            countdownLength = 0
            findViewById<TextView>(R.id.countdownTV).text = "00:00"
        }
    }

    private fun startCountdown() {
        countdown?.cancel()

        countdown = object : CountDownTimer(countdownLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                findViewById<TextView>(R.id.countdownTV).text = formattedTime
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.countdownTV).text = "00:00"
            }
        }.start()
    }

}