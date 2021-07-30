package com.example.card_game

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class PracticePlay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_play)

        val file: SharedPreferences = getSharedPreferences("practiceShared", Context.MODE_PRIVATE)
        val play: Practice = Practice(file)

        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val deck: ImageView = findViewById(R.id.deck_practice)
        deck.setImageResource(pref.getInt("DECK_PICK", 0))
        val result1 : TextView = findViewById(R.id.result1)
        val result2 : TextView = findViewById(R.id.result2)
        val result3 : TextView = findViewById(R.id.result3)


        var methodName : String? = "null"
        var method = 1
        method = file.getInt("METHOD", 0)
        methodName = file.getString("METHOD_NAME", "null")

        if(method == 0){
            Log.d("PracticePLay","Error while loading method!")
        }

    }

    }

    fun startGame(play: Practice)
    {

        play.generateDeck()

    }

