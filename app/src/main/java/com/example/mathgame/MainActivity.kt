package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var add : Button
    lateinit var multi : Button
    lateinit var sub : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        add = findViewById(R.id.add)
        sub = findViewById(R.id.sub)
        multi = findViewById(R.id.mult)

        add.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)

            intent.putExtra("choice", 1)
            startActivity(intent)

        }

        sub.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)

            intent.putExtra("choice", 2)
            startActivity(intent)

        }

        multi.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)

            intent.putExtra("choice", 3)
            startActivity(intent)

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}