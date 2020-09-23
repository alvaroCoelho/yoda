package com.sup.yoda.controller

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sup.yoda.R
import com.sup.yoda.model.User
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HomeActivity : AppCompatActivity() {

    private lateinit var yodaWhatGif: ImageView
    private lateinit var btnFeedback: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)

        btnFeedback = findViewById(R.id.buttonFeedback)

        yodaWhatGif = findViewById(R.id.imageViewYodaWhat)
        Glide.with(this)
            .load(R.drawable.yodawhy)
            .placeholder(R.drawable.yodawhy)
            .into(yodaWhatGif);


        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.authenticated_user), MODE_PRIVATE)
        if (sharedPref.getBoolean(getString(R.string.authenticated_user), false)) {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
            finish()

        } else {

            try {

                var url = "https://yoda-api.agilepromoter.com/v1/support/users/app/all"
                var client: OkHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        e.printStackTrace()
                        println("Request Failure.")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful){
                            try {
                                val responseData = response.body?.string()
                                var jsonArray = JSONArray(responseData)
                                println("Request Successful!!")
                                var i:Int = 0
                                var size = jsonArray.length()

                                for(i in 0 until size-1){
                                    var jsonObjectdetail:JSONObject = jsonArray.getJSONObject(i)
                                 var userNew:User = User(0,"","","")
                                     userNew.save(jsonObjectdetail.getInt("id"), jsonObjectdetail.getString("zendesk_id"),
                                         jsonObjectdetail.getString("name"),jsonObjectdetail.getString("email"),this@HomeActivity)

                                }


                                }catch (e: JSONException){

                            }

                        }



                    }


                })


                }catch(e: JSONException) {
                    e.printStackTrace()
                }


        }

        btnFeedback.setOnClickListener { it

            val intent =
                Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
            finish()

        }



    }
}


