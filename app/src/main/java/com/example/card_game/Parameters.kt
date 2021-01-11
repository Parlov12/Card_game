package com.example.card_game

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parameters.*
import kotlinx.android.synthetic.main.activity_username_activtiy.*


class Parameters : AppCompatActivity() {

    var num_of_decks: Int = 0
    var method: Int = 0

    val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parameters)

        var checkDeck: Boolean
        var checkMethod: Boolean

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val intent = Intent(this, Blackjack::class.java)

        right_arrow.setOnClickListener{


            if(one_deck.isChecked == true)
            {
                num_of_decks = 1
                saveNumDeck()
                startActivity(intent)
            }
            else if(two_decks.isChecked == true)
            {
                num_of_decks = 2
                saveNumDeck()
                startActivity(intent)
            }
            else if(three_decks.isChecked == true)
            {
                num_of_decks = 3
                saveNumDeck()
                startActivity(intent)
            }
            else if(four_decks.isChecked == true)
            {
                num_of_decks = 4
                saveNumDeck()
                startActivity(intent)
            }
            else if(five_decks.isChecked == true)
            {
                num_of_decks = 5
                saveNumDeck()
                startActivity(intent)
            }
            else if(six_decks.isChecked == true)
            {
                num_of_decks = 6
                saveNumDeck()
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Something missing", Toast.LENGTH_SHORT).show()
            }


        }

    } // end of onCreate()



    // fun deckClick -> when one deck is clicked, the other five decks are turned off(isChecked = fale)
    fun deckClick(
        deck1: ToggleButton,
        deck2: ToggleButton,
        deck3: ToggleButton,
        deck4: ToggleButton,
        deck5: ToggleButton
    )
    {
        deck1.isChecked = false
        deck2.isChecked = false
        deck3.isChecked = false
        deck4.isChecked = false
        deck5.isChecked = false
    }
    fun oneClick(view: View)
    {
        deckClick(two_decks, three_decks, four_decks, five_decks, six_decks)
    }
    fun twoClick(view: View)
    {
        deckClick(one_deck, three_decks, four_decks, five_decks, six_decks)
    }
    fun threeClick(view: View)
    {
        deckClick(one_deck, two_decks, four_decks, five_decks, six_decks)
    }
    fun fourClick(view: View)
    {
        deckClick(one_deck, two_decks, three_decks, five_decks, six_decks)
    }
    fun fiveClick(view: View)
    {
        deckClick(one_deck, two_decks, three_decks, four_decks, six_decks)
    }
    fun sixClick(view: View)
    {
        deckClick(one_deck, two_decks, three_decks, four_decks, five_decks)
    }

    fun methodClick(m1: ToggleButton, m2: ToggleButton, m3: ToggleButton, m4: ToggleButton)
    {
        m1.isChecked = false
        m2.isChecked = false
        m3.isChecked = false
        m4.isChecked = false
    }
    fun m1Click(view: View)
    {
        methodClick(secondMethod, thirdMethod, fourthMethod, fifthMethod)
    }
    fun m2Click(view: View)
    {
        methodClick(firstMethod, thirdMethod, fourthMethod, fifthMethod)
    }
    fun m3Click(view: View)
    {
        methodClick(firstMethod, secondMethod, fourthMethod, fifthMethod)
    }
    fun m4Click(view: View)
    {
        methodClick(firstMethod, secondMethod, thirdMethod, fifthMethod)
    }
    fun m5Click(view: View)
    {
        methodClick(firstMethod, secondMethod, thirdMethod, fourthMethod)
    }

    fun saveNumDeck()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putInt("NUM_OF_DECKS", num_of_decks)
        editor.commit()

        Log.d(TAG, "num_of_decks saved!")
    }

}