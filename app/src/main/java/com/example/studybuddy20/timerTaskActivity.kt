package com.example.studybuddy20

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class timerTaskActivity : AppCompatActivity() {

    fun createArray(): Array<String> {
        return Array(61) { i -> String.format("%02d", i) }
    }

    private val options = createArray()

    private var selectedItemSec: String = ""
    private var selectedItemMin: String = ""

    private var countdownLengthMin: Long = 0
    private var countdownLengthSec: Long = 0
    private var countdownLength: Long = 0
    private var countdown: CountDownTimer? = null

    private var isPaused = false
    private var remianingTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_task)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options)
        findViewById<Spinner>(R.id.setTimerMin).adapter = adapter
        findViewById<Spinner>(R.id.setTimerSec).adapter = adapter

        findViewById<Spinner>(R.id.setTimerMin).setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedItemMin = ""
                    selectedItemMin = parent.getItemAtPosition(position).toString()
                    val selectedItemMinLong = selectedItemMin.toLong()

                    countdownLengthMin = selectedItemMinLong * 60 * 1000
                    findViewById<TextView>(R.id.txtMinTV).text = selectedItemMin
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            })

        findViewById<Spinner>(R.id.setTimerSec).setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedItemSec = ""
                    selectedItemSec = parent.getItemAtPosition(position).toString()
                    val selectedItemSecLong = selectedItemSec.toLong()

                    countdownLengthSec = selectedItemSecLong * 1000
                    findViewById<TextView>(R.id.txtSecTV).text = selectedItemSec
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            })


        findViewById<Button>(R.id.btnStart).setOnClickListener {
            startCountdown()
        }

        findViewById<Button>(R.id.btnPause).setOnClickListener {
            isPaused = true
            countdown?.cancel()
            findViewById<Button>(R.id.btnStart).isEnabled = true
        }

        findViewById<Button>(R.id.btnRepeat).setOnClickListener {
            countdown?.cancel()
            findViewById<Button>(R.id.btnStart).isEnabled = true
            countdownLength = countdownLengthMin + countdownLengthSec
            remianingTime = countdownLength
            findViewById<TextView>(R.id.txtMinTV).text = selectedItemMin
            findViewById<TextView>(R.id.txtSecTV).text = selectedItemSec
        }
    }

    private fun startCountdown() {
        findViewById<Button>(R.id.btnStart).isEnabled = false
        countdown?.cancel()
        countdownLength = countdownLengthMin + countdownLengthSec

        val TimeRemaining = if (isPaused) remianingTime else countdownLength

        countdown = object : CountDownTimer(TimeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remianingTime = millisUntilFinished
                isPaused = false
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60

                val formattedTimeMin = String.format("%02d", minutes)
                val formattedTimeSec = String.format("%02d", seconds)
                findViewById<TextView>(R.id.txtMinTV).text = formattedTimeMin
                findViewById<TextView>(R.id.txtSecTV).text = formattedTimeSec
            }

            override fun onFinish() {
                findViewById<TextView>(R.id.txtMinTV).text = "00"
                findViewById<TextView>(R.id.txtSecTV).text = "00"
                findViewById<Button>(R.id.btnStart).isEnabled = true
            }
        }.start()
    }
}