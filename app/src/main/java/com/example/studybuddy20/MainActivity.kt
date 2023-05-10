package com.example.studybuddy20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.studybuddy20.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigation.setOnItemSelectedListener { item ->
            onNavigationItemSelected(item)
        }
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

    private fun onNavigationItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.home ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return false;
    }

}