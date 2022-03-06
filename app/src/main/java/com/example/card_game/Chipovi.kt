
package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.widget.ImageView
import android.widget.Toast


class Chipovi (file: SharedPreferences){

    private var counter : Int = 0

    // bets - sadrži podatke koji bet je na kojoj poziciji -> 1 - bet10, 2 - bet25, 3 - bet50, 4 - bet100, 5 - bet500, 6 - bet1000
    private var bets = arrayOf<Int>(0,0,0,0,0,0,0,0,0,0)
    // SharedPreference file
    private val data : SharedPreferences = file
    private val editor : SharedPreferences.Editor = data.edit()

    // slike betove predstavljene kao Integer
    private val bet10: Int = R.drawable.ulog_10_off
    private val bet25: Int = R.drawable.ulog_25_off
    private val bet50: Int = R.drawable.ulog_50_off
    private val bet100: Int = R.drawable.ulog_100_off
    private val bet500: Int = R.drawable.ulog_500
    private val bet1000: Int = R.drawable.ulog_1000



    // slike gore navedenih betova
   var pics = arrayOf<Int>(
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

            counter = data.getInt("BET_COUNTER", 0)
        }


    }

    fun getCounter() : Int
    {
        return counter
    }

    fun getCorrectBet(bet: Int) : Int
    {

            // ako je - popunjave se niz bets s odgovarajućim brojem
            bets[counter] = bet


            // povećava se counter za 1
            counter += 1

            // zapisivanje u SharedPreference file
            editor.putInt("${counter}BET", bet)
            editor.putInt("BET_COUNTER", counter)
            editor.commit()

            return pics[bet]
    }

    fun getPicsBetImage(num : Int) : Int
    {
        return pics[bets[num]]
    }

    fun putBet(bet: Int)
    {
        if(counter == 9)
        {

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
