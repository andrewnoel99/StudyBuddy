package com.example.studybuddy20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class CSButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csbutton)


  //STILL NEEDS WORK~SR
        val countDownTimer = object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                mTextView.text = "Seconds Remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                mTextView.text = "Time Up!"
            }
        }




    }

}



