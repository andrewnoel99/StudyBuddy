package com.example.studybuddy20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var studyMethods: Button
    private lateinit var timerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studyMethods = findViewById(R.id.StudyMethodsBtn)
        timerButton = findViewById(R.id.btnTimer)

        studyMethods.setOnClickListener(this)
        timerButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
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
}