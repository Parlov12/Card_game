package com.example.card_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        briskula_button.setOnClickListener {

            val playing_activity = Intent(this, PlayingActivity::class.java)

                    startActivity(playing_activity)


        }

        blackjack_button.setOnClickListener {
            val black_jack = Intent(this, Blackjack::class.java)

            startActivity(black_jack)
        }
        }



    }
