package com.example.a03_media_player

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var mediaPlayer = MediaPlayer.create(this, R.raw.test)
        mediaPlayer.start()

//        1) Кнопки управления медиа-плеером: Play, Pause, Next, Prev., Cycle, Volume, SeekTo (method);
//        2) Activity Life Cycle;
//        3) Музыка хранится в /res/raw 			// как минимум 2 файла;
    }
}