package com.sup.yoda.controller

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sup.yoda.R

class FeedbackActivity : AppCompatActivity() {

    private lateinit var yodaPresent: ImageView
    private lateinit var spinnerUsers: Spinner
    private lateinit var  editTextFeedback: EditText
    private lateinit var switchAnonymous: Switch
    private lateinit var switchTypeBetter: Switch
    private lateinit var switchTypeContinue: Switch
    private lateinit var buttonSendFeedback: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_feedback)

        yodaPresent = findViewById(R.id.imageViewYodaPresent)
        Glide.with(this)
            .load(R.drawable.yodapresent)
            .placeholder(R.drawable.yodapresent)
            .into(yodaPresent);

        spinnerUsers = findViewById(R.id.spinnerUsers)
        editTextFeedback = findViewById(R.id.editTextFeedback)
        switchAnonymous = findViewById(R.id.switchAnonymous)
        switchTypeBetter = findViewById(R.id.switchTypeBetter)
        switchTypeContinue = findViewById(R.id.switchTypeContinue)
        buttonSendFeedback = findViewById(R.id.buttonSendFeedback)




    }

}