package com.example.card_game

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class Decks: Blackjack() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blackjack)


    }
}