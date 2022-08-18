package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val mssg: String? = intent.getStringExtra("Result")

        findViewById<TextView>(R.id.textView).apply {
            text = mssg
            setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

        }

    }
}