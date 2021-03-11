package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_username_activtiy.*

class UsernameActivtiy : AppCompatActivity() {

    lateinit var username: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username_activtiy)

        val empty: Int = R.drawable.username_empty
        val notEmpty: Int = R.drawable.username
        val handler = Handler()


        val cont = Intent(this,MainActivity::class.java)

        continue_btn.setOnClickListener{
            if(username_edit.text.toString() == "")
            {
                username_background.setBackgroundResource(empty)
                handler.postDelayed({
                    username_background.setBackgroundResource(notEmpty)
                }, 100)

            }
            else {
                saveData()
                startActivity(cont)
                finish()
            }
        }

        // SharedPreferences Kotlin
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            var name = getString("username","")
            username_edit.setText(name)
        }
    }
    fun saveData()
    {
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = pref.edit()
        editor.putString("username", username_edit.text.toString())
        editor.commit()
    }

}