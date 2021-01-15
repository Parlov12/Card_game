package com.example.card_game

import android.media.Image

class blackjack_class(var number: Int = 0, var type: String = "", var value: Int = 0, var pic: Int){

}

class GameState(var firstPDeck: Int = 0, var lastPDeck: Int = 0, var numberPlayerCards: Int = 0, var numberPcCards: Int = 0, var status: Boolean = false, var pcSum: Int = 0, var playerSum: Int = 0, var pomPlayerSum: Int = 0, var pomPcSum: Int = 0)
{
    fun reset()
    {
        firstPDeck = 0
        lastPDeck = 0
        numberPlayerCards = 0
        status = false
        pcSum = 0
        playerSum = 0
        pomPcSum = 0
        pomPlayerSum = 0
    }

}
class player(var igrac: Int = 0, var bodovi: Int = 0, var ime: String = "Unknown")
{


}


















