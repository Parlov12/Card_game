package com.example.card_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_instructions.*

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)

        val title: TextView = findViewById(R.id.username)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var savedUsername = pref.getString("username","")

        title.text = "USERNAME: $savedUsername"

    }
}