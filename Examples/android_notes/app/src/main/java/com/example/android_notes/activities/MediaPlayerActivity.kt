package com.example.android_notes.activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.R
import java.io.File

class MediaPlayerActivity : AppCompatActivity() {

    private var log_tag : String = "MY_LOG_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_media_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.back_to_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Обработка резрешения на получение доступа к файлам
        val requestPermessionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                playMusic()
                Toast.makeText(this, "Please grant permission", Toast.LENGTH_LONG).show()
            }
        }
        requestPermessionLauncher.launch(READ_MEDIA_IMAGES)
        requestPermessionLauncher.launch(READ_EXTERNAL_STORAGE)
        // Target SDR = 22 || Android 5.0

    }

    private fun playMusic(){
        var musicPath: String = Environment.getExternalStorageDirectory().path + "/Music"


        Log.d (log_tag, "PATH: " + musicPath)
        var directory: File = File(musicPath)
        //directory.isDirectory()
        directory.listFiles().forEach {
            Log.d (log_tag, it.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        var mediaPlayer = MediaPlayer.create(this, R.raw.test)
        Log.d(log_tag, mediaPlayer.duration.toString())
        mediaPlayer.start()


    }
}















//        // Check if the Android version is TIRAMISU or newer
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            // Use the requestPermessionLauncher to request the READ_MEDIA_IMAGES permission
//            requestPermessionLauncher.launch(READ_MEDIA_IMAGES)
//        } else {
//            // For older Android versions, use READ_EXTERNAL_STORAGE permission
//            requestPermessionLauncher.launch(READ_EXTERNAL_STORAGE)
//        }