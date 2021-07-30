package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        var file : SharedPreferences = getSharedPreferences("practiceShared", android.content.Context.MODE_PRIVATE)


        val firstMethod : Button = findViewById(R.id.first_method)
        val secondMethod : Button = findViewById(R.id.second_method)
        val thirdMethod : Button = findViewById(R.id.third_method)
        val forthMethod : Button = findViewById(R.id.forth_method)
        val fifthMethod : Button = findViewById(R.id.fifth_method)

        val startPlay : Intent = Intent(this, PracticePlay::class.java)

        firstMethod.setOnClickListener {
            saveMethod(1, "HI-LO")
            startActivity(startPlay)
        }
        secondMethod.setOnClickListener {
            saveMethod(2, "K-O")
            startActivity(startPlay)
        }
        thirdMethod.setOnClickListener {
            saveMethod(3, "HI-OPT I")
            startActivity(startPlay)
        }
        forthMethod.setOnClickListener {
            saveMethod(4, "HI-OPT II")
            startActivity(startPlay)
        }
        fifthMethod.setOnClickListener {
            saveMethod(5, "ZEN")
            startActivity(startPlay)
        }

    }

    fun saveMethod(meth: Int, meth_name: String)
    {
        val sharedPref : SharedPreferences = getSharedPreferences("practiceShared", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("METHOD_NAME", meth_name)
        editor.putInt("METHOD", meth)
        editor.commit()
    }

}