package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import android.view.WindowManager


class SplashScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var handler = Handler()
        val user = Intent(this, UsernameActivtiy::class.java)
        val main = Intent(this, MainActivity::class.java)


        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
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

        }, 1300)





    }

}