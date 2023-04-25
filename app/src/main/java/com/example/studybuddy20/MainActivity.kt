package com.example.studybuddy20

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

// NOTES
// 1. API from the slides
    // I think we should change it
    // Idk to what tho

class MainActivity : AppCompatActivity() {

    private var countdownLength: Long = 0
    private var countdown: CountDownTimer? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mathBtn).setOnClickListener {
            startActivity(Intent(this, mathBtn::class.java))
        }

        findViewById<Button>(R.id.CSBtn).setOnClickListener {
            startActivity(Intent(this, csBtn::class.java))
        }

        findViewById<Button>(R.id.timerBtn).setOnClickListener {
            startActivity(Intent(this, timer::class.java))
        }
    }

//    override fun onClick(view: View?) {
//        when (view?.id) {
//            R.id.Mathbtn -> {
//                countdownLength = 20 * 60 * 1000 // 20 minutes
//                countdownTV.text = "20:00"
//            }
//            R.id.CSbtn -> {
//                countdownLength = 15 * 60 * 1000 // 15 minutes
//                countdownTV.text = "15:00"
//            }
//            R.id.StopBtn -> {
//                countdown?.cancel()
//                countdownLength = 0
//                countdownTV.text = "00:00"
//            }
//            R.id.StartBtn -> startCountdown()
//        }
//    }

//    private fun startCountdown() {
//        countdown?.cancel()
//
//        countdown = object : CountDownTimer(countdownLength, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                val minutes = millisUntilFinished / 1000 / 60
//                val seconds = (millisUntilFinished / 1000) % 60
//
//                val formattedTime = String.format("%02d:%02d", minutes, seconds)
//                countdownTV.text = formattedTime
//            }
//
//            override fun onFinish() {
//                countdownTV.text = "00:00"
//            }
//        }.start()
//    }


}