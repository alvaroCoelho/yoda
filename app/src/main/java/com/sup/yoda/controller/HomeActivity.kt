package com.sup.yoda.controller

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sup.yoda.R

class HomeActivity : AppCompatActivity() {

    private lateinit var yodaPowerGif: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        yodaPowerGif = findViewById(R.id.imageViewYodaPower)
        Glide.with(this)
            .load(R.drawable.yodapower)
            .placeholder(R.drawable.yodapower)
            .into(yodaPowerGif);
    }
}