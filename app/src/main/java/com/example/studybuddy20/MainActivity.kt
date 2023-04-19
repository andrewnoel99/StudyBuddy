package com.example.studybuddy20

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var counter = 0


        //@TargetApi(Build.VERSION_CODES.O)



            val counttime = findViewById<TextView>(R.id.textVeiw2)
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
