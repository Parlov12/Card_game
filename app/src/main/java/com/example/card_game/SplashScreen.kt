package com.example.card_game

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View


class SplashScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler = Handler()
        val user = Intent(this, UsernameActivtiy::class.java)
        val main = Intent(this, MainActivity::class.java)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var savedUsername = pref.getString("username","")



        handler.postDelayed({
            if(savedUsername == "")
            {
                startActivity(user)
                finish()
            }
            else
            {
                startActivity(main)
                finish()
            }

        }, 1000)





    }

}