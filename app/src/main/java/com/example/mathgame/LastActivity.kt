package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LastActivity : AppCompatActivity() {

    lateinit var result: TextView
    lateinit var playAgain: Button
    lateinit var exit : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_last)
        result = findViewById(R.id.resultText)
        playAgain = findViewById(R.id.playAgain)
        exit = findViewById(R.id.exit)

        val score = intent.getIntExtra("score", 0)
        result.text = "your score is :" + score.toString()


        playAgain.setOnClickListener {
            val intent = Intent(this@LastActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}