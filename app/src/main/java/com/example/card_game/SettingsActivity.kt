package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_parameters.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    var background: Int = 0
    var deckPic: Int = 0
    var savedUsername: String = ""
    var bool1: Boolean = true
    var bool2: Boolean = true

    override fun onBackPressed() {
        // do nothing
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        loadUsername()
        username.setText(savedUsername)

        val back = Intent(this, MainActivity::class.java)

        save_button.setOnClickListener{

            if((username.text.toString() == "")||(bool1 == false)||(bool2 == false))
            {
                Toast.makeText(this,"Something missing...", Toast.LENGTH_SHORT).show()
            }
            else {
                if(back1.isChecked == true)
                {
                    background = R.drawable.background1
                }
                else if(back2.isChecked == true)
                {
                    background = R.drawable.background2_white
                }
                else if(back3.isChecked == true)
                {
                    background = R.drawable.background3
                }
                else if(back4.isChecked == true)
                {
                    background = R.drawable.background4
                }
                else if(back5.isChecked == true)
                {
                    background = R.drawable.background5
                }

                if(deck1.isChecked == true)
                {
                    deckPic = R.drawable.deck1
                }
                else if(deck2.isChecked == true)
                {
                    deckPic = R.drawable.deck2
                }
                else if(deck3.isChecked == true)
                {
                    deckPic = R.drawable.deck3
                }
                else if(deck4.isChecked == true)
                {
                    deckPic = R.drawable.deck4
                }
                else if(deck5.isChecked == true)
                {
                    deckPic = R.drawable.deck5
                }
                else if(deck6.isChecked == true)
                {
                    deckPic = R.drawable.deck6
                }
                saveUsername()
                startActivity(back)
                saveBackground()
            }
        }

    }


    fun loadUsername()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        savedUsername = pref.getString("username","").toString()
    }

    fun saveUsername()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("username", username.text.toString())
        editor.commit()
    }

    fun methodClick(m1: ToggleButton, m2: ToggleButton, m3: ToggleButton, m4: ToggleButton, m5: ToggleButton)
    {
        m1.isChecked = false
        m2.isChecked = false
        m3.isChecked = false
        m4.isChecked = false
        m5.isChecked == true


    }
    fun dClick(m1: ToggleButton, m2: ToggleButton, m3: ToggleButton, m4: ToggleButton, m5: ToggleButton, m6: ToggleButton)
    {
        m1.isChecked = false
        m2.isChecked = false
        m3.isChecked = false
        m4.isChecked = false
        m5.isChecked = false
        m6.isChecked == true

    }

    fun m1Click(view: View)
    {
        methodClick(back2, back3, back4, back5,back1)
    }
    fun m2Click(view: View)
    {
        methodClick(back1, back3, back4, back5, back2)
    }
    fun m3Click(view: View)
    {
        methodClick(back1, back2, back4, back5, back3)
    }
    fun m4Click(view: View)
    {
        methodClick(back1, back2, back3, back5, back4)
    }
    fun m5Click(view: View)
    {
        methodClick(back1, back2, back3, back4, back5)
    }

    fun d1(view: View)
    {
        dClick(deck2, deck3, deck4, deck5, deck6, deck1)
    }
    fun d2(view: View)
    {
        dClick(deck1, deck3, deck4, deck5, deck6, deck2)
    }
    fun d3(view: View)
    {
        dClick(deck1, deck2, deck4, deck5, deck6, deck3)
    }
    fun d4(view: View)
    {
        dClick(deck1, deck2, deck3, deck5, deck6, deck4)
    }
    fun d5(view: View)
    {
        dClick(deck1, deck2, deck3, deck4, deck6, deck5)
    }
    fun d6(view: View)
    {
        dClick(deck1, deck2, deck3, deck4, deck5, deck6)
    }



    fun saveBackground()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("BACKGROUND", background)
        editor.putInt("DECK_PIC", deckPic)
        editor.commit()

    }
} // end of onCreate()