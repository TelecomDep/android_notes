package com.example.android_notes.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_notes.MainActivity
import com.example.android_notes.R

class ViewExamples : AppCompatActivity(), View.OnClickListener {

    private lateinit var bBackToMain: Button
    private lateinit var tvView_01: TextView
    private lateinit var bExample: Button
    private lateinit var bExample2: Button
    private lateinit var bExample3: Button
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
        tvView_01 = findViewById(R.id.textView)
        bExample = findViewById(R.id.button)
        bExample2 = findViewById(R.id.button2)
        bExample3 = findViewById(R.id.button3)

    }

    override fun onResume() {
        super.onResume()

        bBackToMain.setOnClickListener({
            val backToMain = Intent(this, MainActivity::class.java)
            startActivity(backToMain)
        })

        TextViewExamples()
        ButtonExamples()



    }

    fun TextViewExamples(){
        // Изменить текст из кода
        tvView_01.setText("Hello from Code")

        // Изменить размеры текста
        // COMPLEX_UNIT_PX - Value is raw pixels
        tvView_01.setTextSize(TypedValue.COMPLEX_UNIT_PX, 10F)
        // COMPLEX_UNIT_DIP - Value is Device Independent Pixels
        tvView_01.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 100F)

        // Изменение цвета текста
        tvView_01.setTextColor(Color.rgb(255,192,0))

        // Возможность нажать на кнопку
        tvView_01.setOnClickListener({
          tvView_01.setTextColor(Color.rgb(55,12,0))
        })
    }

    fun ButtonExamples(){
        bExample3.setOnClickListener(this)
        bExample2.setOnClickListener({
            bExample2.setText("2nd method")
        })

    }

    override fun onClick(v: View?){
        bExample3.setText("3dr method")
    }

    fun customOnClick(v: View?){
        bExample.setText("1st method")
    }
}