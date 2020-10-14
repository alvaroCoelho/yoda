package com.sup.yoda.controller

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sup.yoda.R
import com.sup.yoda.model.Feedback
import com.sup.yoda.model.User

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

        var userNew: User = User(0,"","","")

        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.authenticated_user), MODE_PRIVATE)

        var feedbackNew:Feedback = Feedback(0,"","",sharedPref.getInt(getString(R.string.id_user),0).toString(),
                 sharedPref.getString(getString(R.string.name_user),"") ?: "Not Set","","",0)



        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userNew.getList(this))
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinnerUsers!!.setAdapter(arrayAdapter)

            spinnerUsers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // You can define you actions as you want
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                    userNew = spinnerUsers.selectedItem as User

                }
            }

        switchTypeBetter.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked){
                        switchTypeContinue!!.isChecked=false
                          }

            }

        switchTypeContinue.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                switchTypeBetter!!.isChecked=false
            }

            }

        buttonSendFeedback.setOnClickListener { it



        }



    }

}

