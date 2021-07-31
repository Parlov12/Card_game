package com.example.card_game

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class PracticePlay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_play)

        val file: SharedPreferences = getSharedPreferences("practiceShared", Context.MODE_PRIVATE)

        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val deck: ImageView = findViewById(R.id.deck_practice)
        deck.setImageResource(pref.getInt("DECK_PICK", 0))
        val result1 : TextView = findViewById(R.id.result1)
        val result2 : TextView = findViewById(R.id.result2)
        val result3 : TextView = findViewById(R.id.result3)

        var defaultCard : ImageView = findViewById(R.id.null_card)
        var startCard : ImageView = findViewById(R.id.deck_practice)

        // adding ImageView-s that will represent each card
        var img1 : ImageView = findViewById(R.id.firstCard)
        var img2 : ImageView = findViewById(R.id.secondCard)
        var img3 : ImageView = findViewById(R.id.thirdCard)
        var img4 : ImageView = findViewById(R.id.forthCard)
        var img5 : ImageView = findViewById(R.id.fifthCard)
        var img6 : ImageView = findViewById(R.id.sixthCard)
        var img7 : ImageView = findViewById(R.id.seventhCard)
        var img8 : ImageView = findViewById(R.id.eighthCard)
        var img9 : ImageView = findViewById(R.id.ninethCard)
        var img10 : ImageView = findViewById(R.id.tenthCard)

        // adding each ImageView to list
        var sumCards = listOf<ImageView>(
            img1,
            img2,
            img3,
            img4,
            img5,
            img6,
            img7,
            img8,
            img9,
            img10
        )

        var methodName : String? = "null"
        var method = 1
        method = file.getInt("METHOD", 0)
        methodName = file.getString("METHOD_NAME", "null")

        if(method == 0){
            Log.d("PracticePLay","Error while loading method!")
        }

        val play: Practice = Practice(file, sumCards, defaultCard, startCard)

        val tap : TextView = findViewById(R.id.tap)

        tap.setOnClickListener{
            startGame(play)
        }

    }

    }

    fun startGame(play: Practice)
    {
        play.generateDeck()
        play.dealCards()
    }

