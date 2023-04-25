package com.example.studybuddy20

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class mathBtn : AppCompatActivity() {

    private var countdownLength: Long = 20 * 60 * 1000
    private var countdown: CountDownTimer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_btn)

        findViewById<Button>(R.id.StartBtn).setOnClickListener {
            startCountdown()
        }

        findViewById<Button>(R.id.StopBtn).setOnClickListener {
            countdown?.cancel()
            countdownLength = 0
            findViewById<TextView>(R.id.countdownTV).text = "00:00"
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val taskName = findViewById<EditText>(R.id.txtAddTask).text.toString()
            val db = FirebaseFirestore.getInstance()
            val task: MutableMap<String, Any> = HashMap()
            task["taskName"] = taskName
            db.collection("Tasks")
                .add(task)
                .addOnSuccessListener {
                    Log.d("dbfirebase", "save ${task}")
                }
                .addOnFailureListener{
                    Log.d("dbfirebase Failed", "${task}")
                }
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