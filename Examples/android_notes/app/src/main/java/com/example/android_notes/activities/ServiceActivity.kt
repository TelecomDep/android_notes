package com.example.android_notes.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android_notes.R
import com.example.android_notes.services.BackgroundService

class ServiceActivity : AppCompatActivity() {

    private lateinit var bStart : Button
    private lateinit var bStop : Button
    private lateinit var tvTextFromBg : TextView
    lateinit var dataToUi : String
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("Status")
            val b = intent.getBundleExtra("Location")
            tvTextFromBg.setText(message)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_service)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(
            mMessageReceiver, IntentFilter("BackGroundUpdate"));

        bStart = findViewById(R.id.bStartBg)
        bStop = findViewById(R.id.bStopBg)
        tvTextFromBg = findViewById(R.id.tvDataFromService)
    }


    override fun onResume() {
        super.onResume()

        bStart.setOnClickListener({
            val startBgIntend = Intent(this, BackgroundService::class.java)
            startService(startBgIntend)
        });

        bStop.setOnClickListener({
            val stopBgIntend = Intent(this, BackgroundService::class.java)
            stopService(stopBgIntend)
        });

    }
}