package com.example.studybuddy20

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimerActivity : AppCompatActivity() {

    private lateinit var techniqueTitle: String
    private lateinit var stepDurations: List<Pair<String, Long>>
    private lateinit var currentStep: String
    private var currentStepIndex: Int = 0
    private var timeRemaining: Long = 0
    private lateinit var timer: CountDownTimer
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)



        // Retrieve the technique title and step durations from the intent
        val intent = intent
        techniqueTitle = intent.getStringExtra("techniqueTitle")!!
        stepDurations = intent.getSerializableExtra("steps") as List<Pair<String, Long>>

        // Set the current step to the first step
        currentStepIndex = 0
        currentStep = stepDurations[currentStepIndex].first

        // Start the timer with the duration of the first step
        timeRemaining = stepDurations[currentStepIndex].second
        startTimer()

        // Set the technique title and current step on the screen
        findViewById<TextView>(R.id.current_step_text_view).text = techniqueTitle
        findViewById<TextView>(R.id.text_current_step).text = currentStep





        findViewById<Button>(R.id.pause_button).setOnClickListener { view ->
            onPauseButtonClick(view)
        }

        findViewById<Button>(R.id.play_button).setOnClickListener { view ->
            onPlayButton(view)
        }



        findViewById<Button>(R.id.play_button).setOnClickListener { view ->
            onPlayButton(view)
            findViewById<Button>(R.id.reset_button).isEnabled = true
        }



    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeRemaining * 1000, 1000) {
            override fun onFinish() {
                // If this is not the last step, move to the next step and start the timer again
                if (currentStepIndex < stepDurations.size - 1) {
                    currentStepIndex++
                    currentStep = stepDurations[currentStepIndex].first
                    timeRemaining = stepDurations[currentStepIndex].second
                    startTimer()

                    // Update the screen with the new step
                    findViewById<TextView>(R.id.text_current_step).text = currentStep
                } else {
                    // If this is the last step, finish the activity
                    finish()
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                // Update the time remaining on the screen
                timeRemaining = millisUntilFinished / 1000
                val minutes = timeRemaining / 60
                val seconds = timeRemaining % 60
                findViewById<TextView>(R.id.timer_text_view).text =
                    String.format("%02d:%02d", minutes, seconds)


            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun onPauseButtonClick(view: View) {
        timer.cancel()
        findViewById<Button>(R.id.play_button).isEnabled = true
        findViewById<Button>(R.id.pause_button).isEnabled = false
    }

    private fun onPlayButton(view: View) {
        startTimer()
        findViewById<Button>(R.id.play_button).isEnabled = false
        findViewById<Button>(R.id.pause_button).isEnabled = true
    }
    fun onResetButton(view: View) {
        // Cancel the current timer
        timer.cancel()

        // Start a new timer with the duration of the current step
        timeRemaining = stepDurations[currentStepIndex].second
        startTimer()

        // Update the screen with the new time remaining
        val minutes = timeRemaining / 60
        val seconds = timeRemaining % 60
        findViewById<TextView>(R.id.timer_text_view).text = String.format("%02d:%02d", minutes, seconds)


    }


}
