package com.example.card_game

import android.content.SharedPreferences
import android.media.Image
import android.widget.ImageView

class Bet (file: SharedPreferences, imgBetovi : List<ImageView>){
    var counter : Int = 0

    // bets - sadrži podatke koji bet je na kojoj poziciji -> 1 - bet10, 2 - bet25, 3 - bet50, 4 - bet100, 5 - bet500, 6 - bet1000
    private var bets = arrayOf<Int>(0,0,0,0,0,0,0,0,0,0)
    // SharedPreference file
    private val data : SharedPreferences = file
    val editor : SharedPreferences.Editor = data.edit()
    var listaBetova = imgBetovi

    // slike betove predstavljene kao Integer
    private val bet10: Int = R.drawable.ulog_10_off
    private val bet25: Int = R.drawable.ulog_25_off
    private val bet50: Int = R.drawable.ulog_50_off
    private val bet100: Int = R.drawable.ulog_100_off
    private val bet500: Int = R.drawable.ulog_500
    private val bet1000: Int = R.drawable.ulog_1000

    // slike gore navedenih betova
   var pics = listOf<Int>(
        bet10,
        bet25,
        bet50,
        bet100,
        bet500,
        bet1000
    )

    init{
        for(i in 0..9)
        {
            // učitavanje slika(zapravo rednog broja(1-6) pojedinog beta na pojedinoj poziciji) iz SharedPreference datoteke
            bets[i] = data.getInt("${i}BET",0)
            imgBetovi[i].setImageResource(pics[bets[i]])

            counter = data.getInt("BET_COUNTER", 0)
        }
    }

    fun putBet(bet: Int)
    {
        // provjera je li counter dosa do maksimalnog broja chipova koji se mogu prikazati na ekranu
        if(counter in 0..8) {
            // ako je - popunjave se niz bets s odgovarajućim brojem
            bets[counter] = bet
            // dodaje se slika chipa na odgovarajuće mjesto
            listaBetova[counter].setImageResource(pics[bet])

            // povećava se counter za 1
            counter += 1

            // zapisivanje u SharedPreference file
            editor.putInt("${counter}BET", bet)
            editor.putInt("BET_COUNTER", counter)
            editor.commit()
        }
        else if(counter == 9)
        {
            // ako je counter dosa do maksimalnog broja chipova odnosno do 10
            // chip na 1. mjestu puni se sa onim na 2. mjestu, chip na 2. mjestu sa onima na 3. itd...do zadnjeg chipa
            // zadnji chip se puni sa novim chipom
            listaBetova[0].setImageResource(pics[bets[1]])
            listaBetova[1].setImageResource(pics[bets[2]])
            listaBetova[2].setImageResource(pics[bets[3]])
            listaBetova[3].setImageResource(pics[bets[4]])
            listaBetova[4].setImageResource(pics[bets[5]])
            listaBetova[5].setImageResource(pics[bets[6]])
            listaBetova[6].setImageResource(pics[bets[7]])
            listaBetova[7].setImageResource(pics[bets[8]])
            listaBetova[8].setImageResource(pics[bets[9]])

            listaBetova[9].setImageResource(pics[bet])
            editor.putInt("9BET", bet)

            for(i in 0..8)
            {
                editor.putInt("${i}BET", bets[i+1])
            }

        }
        else{
            println("Bet class - error within putBet -> counter out of range")
        }
    }

}