package com.example.flashcardproject

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Flashcard questions
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994",
        "The Great Wall of China is visible from space",
        "The Declaration of Independence was signed in 1776",
        "World War II ended in 1950",
        "The Roman Empire fell in 476 AD"
    )

    // Correct answers (True or False)
    private val answers = booleanArrayOf(true, false, true, false, true)

    // Track the current question and score
    private var currentIndex = 0
    private var score = 0

    // View components
    private lateinit var welcomeLayout: LinearLayout
    private lateinit var quizLayout: LinearLayout
    private lateinit var startButton: Button
    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var feedbackText: TextView
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hook up UI elements to Kotlin variables
        welcomeLayout = findViewById(R.id.welcomeLayout)
        quizLayout = findViewById(R.id.quizLayout)
        startButton = findViewById(R.id.startButton)
        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        feedbackText = findViewById(R.id.feedbackText)
        nextButton = findViewById(R.id.nextButton)

        // Start Quiz button logic
        startButton.setOnClickListener {
            welcomeLayout.visibility = LinearLayout.GONE
            quizLayout.visibility = LinearLayout.VISIBLE
            loadQuestion()
        }

        // Answer buttons
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        // Next button
        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                Toast.makeText(this, "Quiz Complete! Your Score: $score / 5", Toast.LENGTH_LONG).show()
                finish() // You can replace this later with ScoreActivity
            }
        }
    }

    // Load the current question
    private fun loadQuestion() {
        questionText.text = questions[currentIndex]
        feedbackText.text = ""
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        nextButton.isEnabled = false
    }

    // Check answer and give feedback
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]
        if (userAnswer == correct) {
            feedbackText.text = "Correct!"
            score++
        } else {
            feedbackText.text = "Incorrect"
        }

        trueButton.isEnabled = false
        falseButton.isEnabled = false
        nextButton.isEnabled = true
    }
}
