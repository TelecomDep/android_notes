package com.example.android_notes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.MainActivity
import com.example.android_notes.R

class ViewExamples : AppCompatActivity() {

    private lateinit var bBackToMain: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_examples)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.back_to_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bBackToMain = findViewById<Button>(R.id.button_back_to_main)

    }

    override fun onResume() {
        super.onResume()

        bBackToMain.setOnClickListener({
            val backToMain = Intent(this, MainActivity::class.java)
            startActivity(backToMain)
        })
    }
}