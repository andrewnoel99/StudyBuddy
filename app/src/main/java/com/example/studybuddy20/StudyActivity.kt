package com.example.studybuddy20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class StudyActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var recyclerView: RecyclerView

    private val techniques = listOf(
        Technique(
            "SQ3R", listOf(
                "Survey (1 mins): skimming the chapter and taking notes",
                "1 min break",
                "Question (1 mins): formulate questions around the chapter's content",
                "5 min break",
                "Read (20 mins): read full chapter and look for answers to the questions you made",
                "5 min break",
                "Recite (20 mins): summarize what you just read, recall and identify major points",
                "5 min break",
                "Review (20 mins): review material, quiz yourself"
            )
        ),
        Technique(
            "Pomodoro", listOf(
                "Work (25 mins): focus on a task",
                "5 min break",
                "Work (25 mins): focus on a task",
                "5 min break",
                "Work (25 mins): focus on a task",
                "5 min break",
                "Work (25 mins): focus on a task",
                "20 min break"
            )
        ),
        Technique(
            "Feynman", listOf(
                "Study (20 mins): focus on a topic",
                "5 min break",
                "Explain (20 mins): explain what you just studied, as if you were teaching it to someone",
                "5 min break",
                "Identify gaps (20 mins): identify areas you struggled with and revisit them",
                "5 min break",
                "Review (20 mins): review material, quiz yourself"
            )
        )
    )

    val myTechniques = mapOf(
        "SQ3R" to listOf(
            "Survey \n\n Skimming the chapter and taking notes" to 3,
            "Break" to 1,
            "Question \n\n Formulate questions around the chapter's content" to 3,
            "Break" to 1,
            "Read \n\n Read full chapter and look for answers to the questions you made" to 3,
            "Break" to 1,
            "Recite \n\n Summarize what you just read, recall and identify major points" to 2,
            "Break" to 1,
            "Review\n\n Review material, quiz yourself" to 2
        ),
        "Pomodoro" to listOf(
            "Work \n\n Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5
        ),
        "Feynman" to listOf(
            "Learn" to 30,
            "Break" to 5,
            "Explain" to 30,
            "Break" to 5,
            "Review" to 30
        )
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        // Find the RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter with the list of techniques and a click listener
        val adapter = TechniqueAdapter(techniques) { technique ->
            startButton.isEnabled = true
            startButton.setOnClickListener {
                // Check if the selected technique is in the predefined techniques list
                if (myTechniques.containsKey(technique.title)) {
                    val steps = myTechniques.getValue(technique.title)
                    val stepDurations = steps.map { it.first to it.second.times(60).toLong() }

                    val intent = Intent(this, TimerActivity::class.java).apply {
                        putExtra("techniqueTitle", technique.title)

                        val stepsBundle = Bundle().apply {
                            putSerializable("steps", ArrayList(stepDurations))
                        }
                        putExtras(stepsBundle)
                    }
                    startActivity(intent)



                } else {
                    Toast.makeText(
                        this,
                        "Please select a valid technique. Selected technique: ${technique.title}, Available techniques: ${myTechniques.keys}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        recyclerView.adapter = adapter
        // Find the start button and disable it by default
        startButton = findViewById(R.id.button_start_studying)
        startButton.isEnabled = false
    }



}
