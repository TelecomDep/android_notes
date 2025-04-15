package com.example.a01_hello_world

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var tvHelloWorld : TextView = findViewById(R.id.hello_world_tv)
        tvHelloWorld.setText("Hello from MainActivity")

        // Задаем обработчик нажатия кнопки.
        var bButtonTest : Button = findViewById<Button>(R.id.button_test)
        bButtonTest.setOnClickListener({
            tvHelloWorld.setText("Hello from Clicked Button")
        });

    }

    // Еще один способ работы с нажатием кнопки. "Соединяем через XML".
    var counter : Int = 0
    fun countButton(view: View) {
        counter++
        var tvHelloWorld : TextView = findViewById(R.id.hello_world_tv)
        tvHelloWorld.setText(counter.toString())
        var value = tvHelloWorld.text.toString().toInt() * 10
        Log.d ("BUTTON", value.toString())
    }
}