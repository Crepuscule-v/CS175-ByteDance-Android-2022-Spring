package com.crepuscule.jstu.hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val details = intent.extras?.getString("detailElem")
        val textView = findViewById<TextView>(R.id.details)
        textView.text = details
        textView.textSize = 20f
    }
}

