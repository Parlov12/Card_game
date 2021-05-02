package com.example.card_game

import android.media.Image
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import java.util.zip.Inflater


open class Card(var number: Int = 0, var type: String = "", var value: Int = 0, var pic: Int){

    fun setCard(num: Int, tip: String, vrijednost: Int, karta: Int)
    {
        number = num
        type = tip
        value = vrijednost
        pic = karta
    }

}

fun slideCard(m1: ImageView, m2: ImageView, pomak: Int)
{
    var xOs = m2.x + pomak
    m1.animate()
        .x(xOs)
        .y(m2.y)
        .setDuration(1000)
        .withEndAction{
            m1.x = xOs
            m1.y = m2.y
        }
        .start()
}

fun unSlideCards(d1: ImageView, cards: List<ImageView>)
{
    Log.d("VELIČINA LISTE","${cards.size}")
    for(i in 0..(cards.size-1))
    {
        cards[i].setImageResource(0)
    }

    for(i in 0..(cards.size-1))
    {
        cards[i].animate()
            .x(d1.x)
            .y(d1.y)
            .setDuration(100)
            .withEndAction{
                cards[i].x = d1.x
                cards[i].y = d1.y
            }
            .start()
    }
}


class Player ()
{
    val cards = mutableListOf<Card>()
    var zero_card = Card(0,"",0,0)
    var sum = 0


    fun addCard(new_card: Card)
    {
        cards.add(new_card)
    }

    fun clearCards()
    {
       cards.clear()
    }

    fun resetSum()
    {
        sum = 0
    }


}

class Game()
{
    var pDeck: Int
    val deck = mutableListOf<Int>()
    var stringDeck: String
    var i = 0
    var j = 0
    var numberOfDecks: Int = 0
    var playerCards = mutableListOf<Card>()
    var pcCards = mutableListOf<Card>()

    init {
        pDeck = 0
        stringDeck = ""
    } // end of init


    // FUNKCIJE
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

    fun addPlayerCard(karta: Card)
    {
        playerCards.add(karta)
    }


}




















