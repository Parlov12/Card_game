package com.example.card_game

import android.media.Image

open class Card(var number: Int = 0, var type: String = "", var value: Int = 0, var pic: Int){

    fun setCard(num: Int, tip: String, vrijednost: Int, karta: Int)
    {
        number = num
        type = tip
        value = vrijednost
        pic = karta
    }

}

class Player ()
{
    val cards = mutableListOf<Card>()
    var zero_card = Card(0,"",0,0)


    fun addCard(new_card: Card)
    {
        cards.add(new_card)
    }

    fun clearCards()
    {
       cards.clear()
    }


}

class Game(var numberOfDecks: Int = 0, var player1: Card)
{
    var pDeck: Int
    val deck = mutableListOf<Int>()
    var stringDeck: String
    var i = 0
    var j = 0

    init {
        pDeck = 0
        stringDeck = ""

    } // end of init

    fun generateDeck()
    {
        // filling deck with number from 0 to 51 x times(x = numberOfDecks)
        for(i in 1..numberOfDecks)
        {
            for(j in 0..51)
            {
                deck.add(j)
            }
        }

        fillStringDeck()

        deck.shuffle()
    }

    fun fillStringDeck()
    {
        for(i in 0..(numberOfDecks*52-1))
        {
            stringDeck = "${stringDeck}n${deck[i]}"
            if(i == (numberOfDecks*52-1))
            {
                stringDeck = "${stringDeck}e"
            }
        }
    }

    fun clearDeck()
    {
        deck.clear()
        stringDeck = ""
    }

    fun pDeckPlus()
    {
        pDeck++
        if(pDeck == (numberOfDecks*52-1))
        {
            pDeck = 0
        }
    }


}




















