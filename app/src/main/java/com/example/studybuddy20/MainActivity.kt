package com.example.studybuddy20

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var counter = 0

        @SuppressLint("MissingInflatedId")
        @TargetApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val counttime = findViewById<TextView>(R.id.counttime)
            object : CountDownTimer(50000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    counttime.text = counter.toString()
                    counter++
                }

                override fun onFinish() {
                    counttime.text = "Finished"
                }
            }.start()
        }
    }
}}