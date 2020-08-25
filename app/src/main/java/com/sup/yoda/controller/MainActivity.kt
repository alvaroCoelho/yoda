package com.sup.yoda.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide


import com.sup.yoda.R



class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonStart: Button
    private  lateinit var gifYodaHello: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextTextEmailAddress)
        buttonStart = findViewById(R.id.buttonStart)

        gifYodaHello = findViewById(R.id.imageViewYodaHello)
        Glide.with(this)
            .load(R.drawable.yodahello)
            .placeholder(R.drawable.yodahello)
            .into(gifYodaHello);

        buttonStart.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {

            }
            startActivity(intent)

        }
    }
}