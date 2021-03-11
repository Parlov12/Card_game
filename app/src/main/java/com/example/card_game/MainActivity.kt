package com.example.card_game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_username_activtiy.*
import java.lang.System.exit


class   MainActivity : AppCompatActivity() {

    var pDeck: Int = 0
    var num_of_decks: Int = 0
    val TAG = "TAG"
    var game_state: Boolean = false



    override fun onBackPressed() {
        finishAffinity()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadGameState()
        loadNumDeck()


        val handler = Handler()

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



        //settings_button.background.alpha = 200
        //start_button.background.alpha = 200

        val customize = Intent(this, Parameters::class.java)
        val black_jack = Intent(this, Blackjack::class.java)

        /* start_button.setOnClickListener {
             savePDeck()
             saveNumDeck()
             startActivity(customize)
         } */


        start_button.setOnClickListener { v -> startButton()
            start_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            handler.postDelayed({
                start_button.animate().scaleX(1f).scaleY(1f).setDuration(50)
            }, 50)
            true}
        start_button.setOnLongClickListener { v ->
            start_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            false

        }
        start_button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                start_button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(50)
            }
            false
        }

        continue_button.setOnClickListener { v -> continueButton()
            continue_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            handler.postDelayed({
                continue_button.animate().scaleX(1f).scaleY(1f).setDuration(50)
            }, 50)
            true
        }
        continue_button.setOnLongClickListener { v ->
            continue_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            true
        }
        continue_button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                continue_button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(50)
            }
            false
        }

        /*
        button.setOnClickListener { v ->
            button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            handler.postDelayed({
                button.animate().scaleX(1f).scaleY(1f).setDuration(50)
            }, 50)
            true
        }
        button.setOnLongClickListener { v ->
            button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            true
        }
        button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(50)
            }
            false
        }

        button2.setOnClickListener { v ->
            button2.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            handler.postDelayed({
                button2.animate().scaleX(1f).scaleY(1f).setDuration(50)
            }, 50)
            true
        }
        button2.setOnLongClickListener { v ->
            button2.animate().scaleX(0.9f).scaleY(0.9f).setDuration(50)
            true
        }
        button2.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                button2.animate().scaleX(1.0f).scaleY(1.0f).setDuration(50)
            }
            false
        }

         */
        settings_button.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }


        about_button.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }





    } // end of onCreate()


    fun startButton()
    {
        savePDeck()
        game_state = false
        saveGameState()
        val customize = Intent(this, Parameters::class.java)
        startActivity(customize)
    }

    fun continueButton()
    {
        val black_jack = Intent(this, Blackjack::class.java)
        if(game_state == true) {
            startActivity(black_jack)
        }
    }

    fun savePDeck()
    {
        var pref_pdeck = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref_pdeck.edit()
        editor.putInt("POINTER_ON_DECK", pDeck)
        editor.commit()

        Log.d(TAG, "pDeck saved!")
    }
    fun saveNumDeck()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putInt("NUM_OF_DECKS", num_of_decks)
        editor.commit()

        Log.d(TAG, "num_of_decks saved!")
    }

    fun loadNumDeck()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        num_of_decks = pref.getInt("NUM_OF_DECKS", 0)

    }

    fun saveGameState()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putBoolean("GAME_STATE", game_state)
        editor.commit()
    }

    fun loadGameState()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        game_state = pref.getBoolean("GAME_STATE", false)
    }


}
