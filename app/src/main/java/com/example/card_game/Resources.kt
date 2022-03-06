package com.example.card_game

import android.widget.ImageView

class Resources {
    // podaci
    private val betImages : List<Int> = listOf(
        R.drawable.ulog_10_off,
        R.drawable.ulog_25_off,
        R.drawable.ulog_50_off,
        R.drawable.ulog_100_off,
        R.drawable.ulog_500,
        R.drawable.ulog_1000
    )

    private var cancelBet = R.drawable.cancel_button

    init {

    }

    // getters
    fun getBetImages(num : Int) : Int
    {
        return betImages[num]
    }

    fun getCancelBet() : Int
    {
        return cancelBet
    }
}