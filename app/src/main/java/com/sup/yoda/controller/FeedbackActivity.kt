package com.sup.yoda.controller

import android.content.Context
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

        var userSpinner: User = User(0,"",getString(R.string.select_user),"",0)



        var feedbackNew: Feedback = Feedback(0,"","","idUserFrom.toString()",
                "","","",0,"")



        val listUser: ArrayList<User> = ArrayList<User>()
            listUser.add(userSpinner)
            listUser.addAll(userSpinner.getList(this))
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,listUser)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinnerUsers!!.setAdapter(arrayAdapter)

            spinnerUsers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                    userSpinner = spinnerUsers.selectedItem as User
                    feedbackNew.idUserFor = userSpinner.id.toString()
                    feedbackNew.nameUserFor = userSpinner.nome
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

            if(userSpinner.id == 0){
                Toast.makeText(this,getString(R.string.without_user),Toast.LENGTH_LONG).show()
            }else if(editTextFeedback.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, getString(R.string.without_message), Toast.LENGTH_LONG).show()
                  }else if (!switchTypeBetter.isChecked && !switchTypeContinue.isChecked ) {
                Toast.makeText(this, getString(R.string.without_type), Toast.LENGTH_LONG).show()

            }else{

                var userNew: User = User(0,"","","",0)
                var userLogged: User = userNew.getUserLogged(this)
                feedbackNew.idUserFrom = userLogged.id.toString()
                feedbackNew.nameUserFrom = userLogged.nome

                feedbackNew.message = editTextFeedback.text.toString();

                val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                val currentDate = sdf.format(Date())
                System.out.println(" C DATE is --------------------------------------------------------  "+currentDate)


                val rootObject= JSONObject()
                rootObject.put("id_user_for",feedbackNew.idUserFor)
                rootObject.put("name_user_for",feedbackNew.nameUserFor)
                rootObject.put("message",feedbackNew.message)
                rootObject.put("id_user_from",feedbackNew.idUserFrom)
                rootObject.put("name_user_from",feedbackNew.nameUserFrom)
                rootObject.put("type", feedbackNew.type)
                if(feedbackNew.isAnonymous==1){
                    rootObject.put("is_anonymous",true)
                }else{
                    rootObject.put("is_anonymous",false)
                }
                rootObject.put("date","25")

                val okHttpClient = OkHttpClient()
                val requestBody = rootObject.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .method("POST", requestBody)
                    .url("https://yoda-api.agilepromoter.com/v1/support/app/feedback")
                    .build()
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Toast.makeText(this@FeedbackActivity, getString(R.string.error_send_feedback), Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call, response: Response) {
                        println("POST---------------------------------------${response.toString()}");
                        println("CALL---------------------------------------${call.toString()}");
                        val intent = Intent(this@FeedbackActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
               })


            }


        }



    }

}

