package com.example.coaldelivery

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.nio.file.attribute.AttributeView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun nextScreen(AttributeView: View) {
        val nextScreenIntent = Intent (this, SecondActivity::class.java)
        startActivity(nextScreenIntent)
    }
}