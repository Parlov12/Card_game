package com.example.card_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           //settings_button.background.alpha = 200
           //start_button.background.alpha = 200

        start_button.setOnClickListener {
            val black_jack = Intent(this, Blackjack::class.java)

            startActivity(black_jack)
        }

        levels_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        settings_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()

          //  val playing_activity = Intent(this, PlayingActivity::class.java)

               //     startActivity(playing_activity)


        }

        instructions_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        about_button.setOnClickListener {

            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
        }



        } // end of onCreate()



    }
