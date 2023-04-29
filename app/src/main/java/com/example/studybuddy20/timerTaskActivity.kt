package com.example.studybuddy20

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class timerTaskActivity : AppCompatActivity() {

    private val options = arrayOf("00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60")

    private var selectedItemSec: String = ""
    private var selectedItemMin: String = ""

    private var countdownLengthMin: Long = 0
    private var countdownLengthSec: Long = 0
    private var countdownLength: Long = 0
    private var countdown: CountDownTimer? = null

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

        findViewById<Button>(R.id.btnRepeat).setOnClickListener {
            findViewById<Button>(R.id.btnStart).isEnabled = true
            countdown?.cancel()
            countdownLength = countdownLengthMin + countdownLengthSec
            findViewById<TextView>(R.id.txtTimerTV).text = selectedItemMin + ":" + selectedItemSec
        }
    }

    private fun startCountdown() {
        findViewById<Button>(R.id.btnStart).isEnabled = false
        countdown?.cancel()

        println(countdownLengthMin)
        println(countdownLengthSec)
        println(countdownLength)
        countdownLength = countdownLengthMin + countdownLengthSec

        countdown = object : CountDownTimer(countdownLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                findViewById<TextView>(R.id.txtTimerTV).text = formattedTime
                findViewById<TextView>(R.id.txtMinTV).text = ""
                findViewById<TextView>(R.id.txtSecTV).text = ""

            }

            override fun onFinish() {
                findViewById<TextView>(R.id.txtTimerTV).text = "00:00"
                findViewById<Button>(R.id.btnStart).isEnabled = true
            }
        }.start()
    }
}