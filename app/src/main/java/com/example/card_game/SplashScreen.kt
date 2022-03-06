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

  lateinit var data : DataManager
  lateinit var file : SharedPreferences

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


    file = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    data = DataManager(file)



    handler.postDelayed({
      if(data.getUsername() == "") {
        // setting background no2 and deck no6 as active
        data.setBackgroundList(1, true)
        data.setDeckList(5, true)
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