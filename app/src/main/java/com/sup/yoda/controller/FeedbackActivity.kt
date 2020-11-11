package com.sup.yoda.controller

import android.content.Intent
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
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

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

        var userNew: User = User(0,"",getString(R.string.select_user),"")

      val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.authenticated_user), MODE_PRIVATE)

        var feedbackNew: Feedback = Feedback(0,"","",sharedPref.getInt(getString(R.string.id_user),0).toString(),
                 sharedPref.getString(getString(R.string.name_user),"") ?: "Not Set","","",0)

        val listUser: ArrayList<User> = ArrayList<User>()
            listUser.add(userNew)
            listUser.addAll(userNew.getList(this))
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,listUser)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinnerUsers!!.setAdapter(arrayAdapter)

            spinnerUsers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                    userNew = spinnerUsers.selectedItem as User
                    feedbackNew.idUserFor = userNew.id.toString()
                    feedbackNew.nameUserFor = userNew.nome
                }
            }

        switchTypeBetter.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked){
                        switchTypeContinue!!.isChecked=false
                        feedbackNew.type = getString(R.string.better)
                          }

            }

        switchTypeContinue.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                switchTypeBetter!!.isChecked=false
                feedbackNew.type = getString(R.string.continues)
            }

            }


        switchAnonymous.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
               feedbackNew.isAnonymous = 1
            }

        }

        buttonSendFeedback.setOnClickListener { it

            if(userNew.id == 0){
                Toast.makeText(this,getString(R.string.without_user),Toast.LENGTH_LONG).show()
            }else if(editTextFeedback.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, getString(R.string.without_message), Toast.LENGTH_LONG).show()
                  }else if (!switchTypeBetter.isChecked && !switchTypeContinue.isChecked ) {
                Toast.makeText(this, getString(R.string.without_type), Toast.LENGTH_LONG).show()
            }else{
                val intent =
                    Intent(this@FeedbackActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }


/*
            val payload = "test payload"

            val okHttpClient = OkHttpClient()
            val requestBody = payload.toRequestBody()
            val request = Request.Builder()
                .method("POST", requestBody)
                .url("url")
                .build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Handle this
                }*/

             /*   override fun onResponse(call: Call, response: Response) {*/



      /*          }
            })*/



        }



    }

}

