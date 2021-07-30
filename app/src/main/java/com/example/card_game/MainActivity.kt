package com.example.card_game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


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

// play_button
        play_button.setOnClickListener { v -> playButton()
            play_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            handler.postDelayed({
                play_button.animate().scaleX(1f).scaleY(1f).setDuration(30)
            }, 50)
            true
        }
        play_button.setOnLongClickListener { v ->
            play_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            true
        }
        play_button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                play_button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(30)
            }
            false
        }
        
        // practice_button end

        // practice_button
        practice_button.setOnClickListener { v -> practiceButton()
            practice_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            handler.postDelayed({
                practice_button.animate().scaleX(1f).scaleY(1f).setDuration(30)
            }, 50)
            true
        }
        practice_button.setOnLongClickListener { v ->
            practice_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            true
        }
        practice_button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                practice_button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(30)
            }
            false
        }

        // practice_button end

        // methods_button
        methods_button.setOnClickListener { v -> methodsButton()
            methods_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            handler.postDelayed({
                methods_button.animate().scaleX(1f).scaleY(1f).setDuration(30)
            }, 50)
            true
        }
        methods_button.setOnLongClickListener { v ->
            methods_button.animate().scaleX(0.9f).scaleY(0.9f).setDuration(30)
            true
        }
        methods_button.setOnTouchListener { v, event ->
            if (event.getAction() === MotionEvent.ACTION_UP) {
                methods_button.animate().scaleX(1.0f).scaleY(1.0f).setDuration(30)
            }
            false
        }

        // methods_button end
        

        settings_button.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }


        about_button.setOnClickListener {
            val aboutIntent = Intent(this, AboutActivity::class.java)
            startActivity(aboutIntent)
        }
        7





    } // end of onCreate()


    fun startButton()
    {
        savePDeck()
        game_state = false
        saveGameState()
        val customize = Intent(this, Parameters::class.java)
        startActivity(customize)
    }

    fun playButton()
    {
        val black_jack = Intent(this, Blackjack::class.java)
        val customize = Intent(this, Parameters::class.java)

        if(game_state == true) {
            startActivity(black_jack)
        }
        else if(game_state == false){
            startActivity(customize)
        }
        else
        {
            Toast.makeText(this, "Error - game_state",Toast.LENGTH_SHORT).show()
        }
    }

    fun practiceButton()
    {
        //Toast.makeText(this,"Coming soon...", Toast.LENGTH_SHORT).show()
        val startPractice: Intent = Intent(this, PracticeActivity::class.java)
        startActivity(startPractice)
    }

    fun methodsButton()
    {
        //Toast.makeText(this,"Coming soon...", Toast.LENGTH_SHORT).show()
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
