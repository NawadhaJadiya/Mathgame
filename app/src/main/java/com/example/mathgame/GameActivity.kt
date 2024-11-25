package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var score : TextView
    lateinit var lives : TextView
    lateinit var time : TextView

    lateinit var ques : TextView
    lateinit var ans : EditText
    lateinit var OK : Button
    lateinit var next : Button

    var correctAns = 0
    var userScore = 0
    var userLives = 3



    lateinit var timer : CountDownTimer
    private val startTimerInMills : Long = 20000
    var timeLeftInMillis : Long = startTimerInMills
    override fun onCreate(savedInstanceState: Bundle?) {
        val choice = intent.getIntExtra("choice", 0)
        supportActionBar!!.title = "Addition"
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        score = findViewById(R.id.valueScore)
        lives = findViewById(R.id.valueLives)
        time = findViewById(R.id.valueTime)
        ques = findViewById(R.id.question)
        ans = findViewById(R.id.ans)
        OK = findViewById(R.id.ok)
        next = findViewById(R.id.next)

        gameContinue(choice)

        OK.setOnClickListener {
            val input = ans.text.toString()
            if(input == "")
            {
                Toast.makeText(applicationContext, "please write an answer or click the next button", Toast.LENGTH_LONG).show()

            }
            else
            {   pauseTimer()
                val userAns = input.toInt()
                if(userAns == correctAns)
                {
                    userScore = userScore + 10
                    ques.text = "congratulations, your answer is correct"
                    score.text = userScore.toString()
                }

                else{
                    userLives--
                    ques.text = "sorry buddy wrong answer"
                    lives.text = userLives.toString()

        }     }
        }
        next.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue(choice)
            ans.setText("")

            if(userLives == 0){
                Toast.makeText(applicationContext, "Game over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, LastActivity :: class.java)

                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()


            }
            else{
                gameContinue(choice)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun gameContinue(choice : Int){
        val num1 = Random.nextInt(0, 100)
        val num2 = Random.nextInt(0, 100)
        if(choice == 1){
            ques.text = "$num1 + $num2"
            correctAns = num1 + num2
        }
        else if(choice == 2){
            ques.text = "$num1 - $num2"
            correctAns = num1 - num2
        }
        else{
            ques.text = "$num1 * $num2"
            correctAns = num1 * num2
        }


        startTimer()
    }
    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLives--
                lives.text = userLives.toString()
                ques.text = "Sorry your time is up"
            }

        }.start()


    }
    fun updateText(){
        val remainingTime : Int = (timeLeftInMillis / 1000).toInt()
        time.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeLeftInMillis = startTimerInMills
        updateText()
    }
}