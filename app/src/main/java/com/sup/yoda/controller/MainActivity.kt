package com.sup.yoda.controller


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sup.yoda.R
import com.sup.yoda.model.User
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonStart: Button
    private lateinit var gifYodaHello: ImageView
    private lateinit var editextCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextTextEmailAddress)
        editextCode = findViewById(R.id.editTextNumberCodeVerification)
        buttonStart = findViewById(R.id.buttonStart)

        gifYodaHello = findViewById(R.id.imageViewYodaHello)
        Glide.with(this)
            .load(R.drawable.yodahello)
            .placeholder(R.drawable.yodahello)
            .into(gifYodaHello);



        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.authenticated_user),
            MODE_PRIVATE
        )
        if (sharedPref.getBoolean(getString(R.string.authenticated_user), true)) {
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()

        }

        buttonStart.setOnClickListener {

            try {
                var url =
                    "https://yoda-api.agilepromoter.com/v1/support/users/app?email=${editTextEmail.text}"

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
                        if (response.isSuccessful) {
                            val responseData = response.body?.string()
                            try {
                                var json = JSONObject(responseData)
                                println("Request Successful!!")
                                println(json)

                                var user = User(
                                    json.getString("id").toInt(), json.getString("zendesk_id"),
                                    json.getString("name"), json.getString("email")
                                )

                                if (user.idZendesk.equals(editextCode.text.toString())) {
                                    val sharedPref =
                                        this@MainActivity?.getPreferences(Context.MODE_PRIVATE)
                                            ?: return
                                    with(sharedPref.edit()) {
                                        putBoolean(getString(R.string.authenticated_user), true)
                                        putInt(getString(R.string.id_user), user.id)
                                        putString(getString(R.string.name_user), user.nome)
                                        commit()
                                    }

                                    Thread(Runnable {
                                        this@MainActivity.runOnUiThread(java.lang.Runnable {

                                            val intent =
                                                Intent(this@MainActivity, HomeActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                            Toast.makeText(
                                                this@MainActivity,
                                                getString(R.string.welcome),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        })
                                    }).start()

                                } else {

                                    Thread(Runnable {
                                        this@MainActivity.runOnUiThread(java.lang.Runnable {
                                            Toast.makeText(
                                                this@MainActivity,
                                                getString(R.string.invalid_user),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        })
                                    }).start()

                                }


                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }


                        } else {
                            Thread(Runnable {
                                this@MainActivity.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.invalid_user),
                                        Toast.LENGTH_LONG
                                    ).show()
                                })
                            }).start()
                        }
                    }

                })

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
    }
}

