package com.example.card_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class   MainActivity : AppCompatActivity() {

    var pDeck: Int = 0
    var num_of_decks: Int = 0
    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        loadNumDeck()

        //settings_button.background.alpha = 200
        //start_button.background.alpha = 200

        val customize = Intent(this, Parameters::class.java)
        val black_jack = Intent(this, Blackjack::class.java)

        start_button.setOnClickListener {
            savePDeck()
            saveNumDeck()
            startActivity(customize)
        }

        continue_btn.setOnClickListener {
            if(num_of_decks != 0) {
                startActivity(black_jack)
            }
        }


        settings_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()




        }

        instructions_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        about_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
        }



    } // end of onCreate()

    fun savePDeck()
    {
        var pref_pdeck = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = pref_pdeck.edit()
        editor.putInt("POINTER_ON_DECK", pDeck)
        editor.commit()

        Log.d(TAG, "pDeck saved!")
    }
    fun saveNumDeck()
    {
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var editor = pref.edit()
        editor.putInt("NUM_OF_DECKS", num_of_decks)
        editor.commit()

        Log.d(TAG, "num_of_decks saved!")
    }

    fun loadNumDeck()
    {
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        num_of_decks = pref.getInt("NUM_OF_DECKS",0)

    }


}
