package com.example.studybuddy20

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    private lateinit var currentStep: String
    private lateinit var steps: List<String>
    private var isBreakPeriod = false
    private var stepIndex = 0
    private var timeRemaining = 0L
    private lateinit var timer: CountDownTimer
    private lateinit var currentStepTextView: TextView
    private lateinit var timerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val technique = intent.getStringExtra("technique")
        steps = intent.getStringArrayListExtra("steps")?.toList() ?: emptyList()

        Log.d("TimerActivity", "Technique: $technique, Steps: $steps") // Add this line

        // Set up the views
        currentStepTextView = findViewById(R.id.current_step_text_view)
        timerTextView = findViewById(R.id.timer_text_view)

        // Start the first step
        startStep()
    }


    private fun startStep() {
        // Set the current step text view to the next step
        currentStep = steps[stepIndex]
        currentStepTextView.text = currentStep

        // Set the timer duration based on whether it's a work or break period
        val duration = if (isBreakPeriod) 5 * 60 * 1000L else 20 * 60 * 1000L

        // Start the timer
        timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                // Move to the next step or break period
                if (isBreakPeriod) {
                    stepIndex++
                    if (stepIndex == steps.size) {
                        // If all steps have been completed, finish the activity
                        finish()
                    } else {
                        // If there are more steps, start the next one
                        isBreakPeriod = false
                        startStep()
                    }
                } else {
                    isBreakPeriod = true
                    startStep()
                }
            }
        }.start()
    }

    private fun updateTimerText() {
        val minutes = timeRemaining / 1000 / 60
        val seconds = timeRemaining / 1000 % 60
        timerTextView.text = String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
