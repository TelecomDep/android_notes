package com.example.android_notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.activities.MediaPlayerActivity
import com.example.android_notes.activities.ViewExamples


class MainActivity : AppCompatActivity() {

    private var log_tag : String = "MY_LOG_TAG"
    private lateinit var bGoToPlayerActivity: Button
    private lateinit var bViewExamples: Button

    // onCreate() – вызывается при первом создании Activity
    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d (log_tag, "вызывается onCreate method")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.back_to_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bGoToPlayerActivity = findViewById<Button>(R.id.bPlayer)
        bViewExamples = findViewById<Button>(R.id.bViews)
    }

    // onResume() – вызывается перед тем как будет доступно для активности пользователя (взаимодействие)
    override fun onResume() {
        super.onResume()
        Log.d (log_tag, "вызывается onResume method")
        bGoToPlayerActivity.setOnClickListener({
            // Create an Intent to start the second activity
            val randomIntent = Intent(this, MediaPlayerActivity::class.java)
            // Start the new activity.
            startActivity(randomIntent)
        });

        bViewExamples.setOnClickListener({
            val randomIntent = Intent(this, ViewExamples::class.java)
            startActivity(randomIntent)
        });
    }









    // onStart() – вызывается перед тем, как Activity будет видно пользователю
    override fun onStart() {
        super.onStart()
        Log.d (log_tag, "вызывается onStart method")
    }

    // onPause() – вызывается перед тем, как будет показано другое Activity
    override fun onPause() {
        super.onPause()
        Log.d (log_tag, "вызывается onPause method")
    }

    // onStop() – вызывается когда Activity становится не видно пользователю
    override fun onStop() {
        super.onStop()
        Log.d (log_tag, "вызывается onStop method")
    }

    // onDestroy() – вызывается перед тем, как Activity будет уничтожено
    override fun onDestroy() {
        super.onDestroy()
        Log.d (log_tag, "вызывается onDestroy method")
    }
}