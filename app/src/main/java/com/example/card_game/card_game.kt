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

    var bets = ArrayList<Int>()
    var betsPic = ArrayList<ImageView>()

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
    var betList = ArrayList<ImageView>()

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
    val cards = ArrayList<Card>()
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
    val deck = ArrayList<Int>()
    var stringDeck: String
    var i = 0
    var j = 0
    var numberOfDecks: Int = 0
    var playerCards = ArrayList<Card>()
    var pcCards = ArrayList<Card>()

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

class Deck{

    var deck = arrayOfNulls<Card>(52)

    init {

        for (i in 0..51) {
            deck[i] = Card(0, "default", 0, 0)
        }

        deck[0]?.setCard(1, "tref", 1, R.drawable.tref_1)
        deck[1]?.setCard(2, "tref", 2, R.drawable.tref_2)
        deck[2]?.setCard(3, "tref", 3, R.drawable.tref_3)
        deck[3]?.setCard(4, "tref", 4, R.drawable.tref_4)
        deck[4]?.setCard(5, "tref", 5, R.drawable.tref_5)
        deck[5]?.setCard(6, "tref", 6, R.drawable.tref_6)
        deck[6]?.setCard(7, "tref", 7, R.drawable.tref_7)
        deck[7]?.setCard(8, "tref", 8, R.drawable.tref_8)
        deck[8]?.setCard(9, "tref", 9, R.drawable.tref_9)
        deck[9]?.setCard(10, "tref", 10, R.drawable.tref_10)
        deck[10]?.setCard(11, "tref", 10, R.drawable.tref_11)
        deck[11]?.setCard(12, "tref", 10, R.drawable.tref_12)
        deck[12]?.setCard(13, "tref", 10, R.drawable.tref_13)
        deck[13]?.setCard(1, "pik", 1, R.drawable.pik_1)
        deck[14]?.setCard(2, "pik", 2, R.drawable.pik_2)
        deck[15]?.setCard(3, "pik", 3, R.drawable.pik_3)
        deck[16]?.setCard(4, "pik", 4, R.drawable.pik_4)
        deck[17]?.setCard(5, "pik", 5, R.drawable.pik_5)
        deck[18]?.setCard(6, "pik", 6, R.drawable.pik_6)
        deck[19]?.setCard(7, "pik", 7, R.drawable.pik_7)
        deck[20]?.setCard(8, "pik", 8, R.drawable.pik_8)
        deck[21]?.setCard(9, "pik", 9, R.drawable.pik_9)
        deck[22]?.setCard(10, "pik", 10, R.drawable.pik_10)
        deck[23]?.setCard(11, "pik", 10, R.drawable.pik_11)
        deck[24]?.setCard(12, "pik", 10, R.drawable.pik_12)
        deck[25]?.setCard(13, "pik", 10, R.drawable.pik_13)
        deck[26]?.setCard(1, "karo", 1, R.drawable.karo_1)
        deck[27]?.setCard(2, "karo", 2, R.drawable.karo_2)
        deck[28]?.setCard(3, "karo", 3, R.drawable.karo_3)
        deck[29]?.setCard(4, "karo", 4, R.drawable.karo_4)
        deck[30]?.setCard(5, "karo", 5, R.drawable.karo_5)
        deck[31]?.setCard(6, "karo", 6, R.drawable.karo_6)
        deck[32]?.setCard(7, "karo", 7, R.drawable.karo_7)
        deck[33]?.setCard(8, "karo", 8, R.drawable.karo_8)
        deck[34]?.setCard(9, "karo", 9, R.drawable.karo_9)
        deck[35]?.setCard(10, "karo", 10, R.drawable.karo_10)
        deck[36]?.setCard(11, "karo", 10, R.drawable.karo_11)
        deck[37]?.setCard(12, "karo", 10, R.drawable.karo_12)
        deck[38]?.setCard(13, "karo", 10, R.drawable.karo_13)
        deck[39]?.setCard(1, "herc", 1, R.drawable.herc_1)
        deck[40]?.setCard(2, "herc", 2, R.drawable.herc_2)
        deck[41]?.setCard(3, "herc", 3, R.drawable.herc_3)
        deck[42]?.setCard(4, "herc", 4, R.drawable.herc_4)
        deck[43]?.setCard(5, "herc", 5, R.drawable.herc_5)
        deck[44]?.setCard(6, "herc", 6, R.drawable.herc_6)
        deck[45]?.setCard(7, "herc", 7, R.drawable.herc_7)
        deck[46]?.setCard(8, "herc", 8, R.drawable.herc_8)
        deck[47]?.setCard(9, "herc", 9, R.drawable.herc_9)
        deck[48]?.setCard(10, "herc", 10, R.drawable.herc_10)
        deck[49]?.setCard(11, "herc", 10, R.drawable.herc_11)
        deck[50]?.setCard(12, "herc", 10, R.drawable.herc_12)
        deck[51]?.setCard(13, "herc", 10, R.drawable.herc_13)

    }
}


