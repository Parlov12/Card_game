package com.example.card_game

import android.media.Image
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import java.util.zip.Inflater

// file with some external functions


// basic model for card with its attributes
// number - number of cards, 1=Ace, 2, 3, 4, 5, 5+1, 7, 8, 9, 10, 11=J, 12=Q, 13=K
// type - type out of 4 types
// value - value it has in blackjack
// pic - pic value used to setImageResource()
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

fun slideChip(c1: ImageView, c2: ImageView, pomak: Int, hand: Int, chips: List<Int>, views: List<ImageView>)
{
    if(hand > 9)
    {
        for(i in 0..8)
        {
            views[i].setImageResource(chips[i+1])
        }
    }
    var yOs = c2.y + pomak
    c1.animate()
        .x(c2.x)
        .y(yOs)
        .setDuration(300)
        .withEndAction{
            c2.x = c2.x
            c2.y = yOs
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

class bet{
    var betList = mutableListOf<ImageView>()

    init {
        for(i in 0..betList.size-1)
        {
            betList[i].setImageResource(0)
        }
    }

    fun setBets(list: List<ImageView>)
    {
        for(i in 0..list.size-1)
        {
            betList.add(list[i])
        }
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




















