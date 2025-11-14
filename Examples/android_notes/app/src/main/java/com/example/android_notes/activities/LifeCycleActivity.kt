package com.example.android_notes.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.R

class LifeCycleActivity : AppCompatActivity() {
    private var log_tag : String = "MY_LIFE_CYCLE"
    private lateinit var tvAppContext: TextView
    private lateinit var tvActivityContext: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_life_cycle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d (log_tag, "fun onCreate is launched")

        tvAppContext = findViewById(R.id.tv_appContext1) as TextView
        tvActivityContext = findViewById(R.id.tv_activityContext1) as TextView

        tvAppContext.setText(applicationContext.toString())
        tvActivityContext.setText(this.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.d (log_tag, "fun onStart is launched")
    }

    override fun onResume() {
        super.onResume()
        Log.d (log_tag, "fun onResume is launched")
    }

    override fun onPause() {
        super.onPause()
        Log.d (log_tag, "fun onPause is launched")
    }

    override fun onStop() {
        super.onStop()
        Log.d (log_tag, "fun onStop is launched")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d (log_tag, "fun onDestroy is launched")
    }
}