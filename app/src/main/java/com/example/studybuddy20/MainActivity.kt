package com.example.studybuddy20

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //private lateinit var mathButton: Button
    //private lateinit var StopBtn: Button
    //private lateinit var csButton: Button
    //private lateinit var startButton: Button
    //private lateinit var countdownTV: TextView
    private lateinit var studyMethods: Button
    private lateinit var timerButton: Button

    private var countdownLength: Long = 0
    private var countdown: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studyMethods = findViewById(R.id.StudyMethodsBtn)
        studyMethods.setBackgroundColor(Color.LTGRAY)

        timerButton = findViewById(R.id.btnTimer)
        timerButton.setBackgroundColor(Color.LTGRAY)
        studyMethods.setOnClickListener(this)
        timerButton.setOnClickListener(this)

        //countdownTV.text = "0"
    }

    override fun onClick(view: View?) {
        when (view?.id) {
//
            R.id.btnTimer ->{
                val intent = Intent(this, timerTaskActivity::class.java)
                startActivity(intent)
            }
            R.id.StudyMethodsBtn ->{
                val intent = Intent(this, StudyActivity::class.java)
                startActivity(intent)
            }
        }
    }

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