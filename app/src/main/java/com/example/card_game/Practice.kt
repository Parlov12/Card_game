package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import android.widget.Toast

class Practice(file: SharedPreferences, listOfCards : List<ImageView>, defCard: ImageView, pocetnaKarta : ImageView)
{
    private val data: SharedPreferences = file
    var editor = data.edit()
    var currentMethod : Int
    var dek : Deck = Deck()
    var methodLevels: IntArray = intArrayOf(0,0,0,0,0)
    var sumCards = ArrayList<ImageView>()
    var defaultCard : ImageView
    var startCard : ImageView

    init{
        methodLevels[0] = data.getInt("M1_LVL", 3)
        println("1st method level = ${methodLevels[0]}")
        methodLevels[1] = data.getInt("M2_LVL", 1)
        println("2nd method level = ${methodLevels[1]}")
        methodLevels[2] = data.getInt("M3_LVL", 1)
        println("3rd method level = ${methodLevels[2]}")
        methodLevels[3] = data.getInt("M4_LVL", 1)
        println("4th method level = ${methodLevels[3]}")
        methodLevels[4] = data.getInt("M5_LVL", 1)
        println("5th method level = ${methodLevels[4]}")

        currentMethod = data.getInt("METHOD", 0)

        for(i in 0..listOfCards.size-1)
        {
            sumCards.add(listOfCards[i])
        }
        defaultCard = defCard
        startCard = pocetnaKarta
    }

    fun generateDeck()
    {
        dek.deck.shuffle()
    }

    fun setMethodlvl(num: Int, lvl: Int)
    {
        editor = data.edit()
        if(num == 1) {
            editor.putInt("M1_LVL", lvl)
            editor.commit()
            methodLevels[0] = lvl
        }
        else if(num == 2) {
            editor.putInt("M2_LVL", lvl)
            editor.commit()
            methodLevels[1] = lvl
        }
        else if(num == 3){
            editor.putInt("M3_LVL", lvl)
            editor.commit()
            methodLevels[2] = lvl
        }
        else if(num == 4){
            editor.putInt("M4_LVL", lvl)
            editor.commit()
            methodLevels[3] = lvl
        }
        else if(num == 5){
            editor.putInt("M5_LVL", lvl)
            editor.commit()
            methodLevels[4] = lvl
        }
        else{
            Log.d("PRACTICE CLASS", "ERROR! -> Practice class -> setMethodLvL fun")
        }
    }

    fun getMethodLvl(num: Int) : Int
    {
        if(num == 1) {
            return data.getInt("M1_LVL", 0)
        }
        else if(num == 2) {
            return data.getInt("M2_LVL", 0)
        }
        else if(num == 3){
            return data.getInt("M3_LVL", 0)
        }
        else if(num == 4){
            return data.getInt("M4_LVL", 0)
        }
        else if(num == 5){
            return data.getInt("M5_LVL", 0)
        }
        else{
            return 0
        }
    }

    fun dealCards()
    {
        var pomak = 0
        for(i in 0..(methodLevels[currentMethod-1]-1))
        {
            sumCards[i].setImageResource(dek.deck[i]!!.pic)
            slideCard(sumCards[i], defaultCard, pomak)
            pomak = pomak + 200
            println("Dealing ${i+1}. card")
        }
    }

    fun undealCards()
    {
        unSlideCards(startCard, sumCards)
    }

    fun getSum(): Int
    {
        var sum: Int = 0
        println("Check ${methodLevels[currentMethod-1]}")
        if(currentMethod == 1){
            for(i in 0..methodLevels[currentMethod-1])
            {
                println("i = $i")
                if(dek.deck[i]?.number == 1){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 2){sum = sum + 1}
                else if(dek.deck[i]?.number == 3){sum = sum + 1}
                else if(dek.deck[i]?.number == 4){sum = sum + 1}
                else if(dek.deck[i]?.number == 5){sum = sum + 1}
                else if(dek.deck[i]?.number == 6){sum = sum + 1}
                else if(dek.deck[i]?.number == 7){sum = sum + 0}
                else if(dek.deck[i]?.number == 8){sum = sum + 1}
                else if(dek.deck[i]?.number == 9){sum = sum + 1}
                else if(dek.deck[i]?.number == 10){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 11){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 12){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 13){sum = sum + (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        else if(currentMethod == 2){
            for(i in 0..methodLevels[currentMethod-1])
            {
                if(dek.deck[i]?.number == 1){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 2){sum = sum + 1}
                else if(dek.deck[i]?.number == 3){sum = sum + 1}
                else if(dek.deck[i]?.number == 4){sum = sum + 1}
                else if(dek.deck[i]?.number == 5){sum = sum + 1}
                else if(dek.deck[i]?.number == 6){sum = sum + 1}
                else if(dek.deck[i]?.number == 7){sum = sum + 1}
                else if(dek.deck[i]?.number == 8){sum = sum + 1}
                else if(dek.deck[i]?.number == 9){sum = sum + 1}
                else if(dek.deck[i]?.number == 10){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 11){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 12){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 13){sum = sum + (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        else if(currentMethod == 3){
            for(i in 0..methodLevels[currentMethod-1])
            {
                if(dek.deck[i]?.number == 1){sum = sum + 0}
                else if(dek.deck[i]?.number == 2){sum = sum + 0}
                else if(dek.deck[i]?.number == 3){sum = sum + 1}
                else if(dek.deck[i]?.number == 4){sum = sum + 1}
                else if(dek.deck[i]?.number == 5){sum = sum + 1}
                else if(dek.deck[i]?.number == 6){sum = sum + 1}
                else if(dek.deck[i]?.number == 7){sum = sum + 0}
                else if(dek.deck[i]?.number == 8){sum = sum + 0}
                else if(dek.deck[i]?.number == 9){sum = sum + 0}
                else if(dek.deck[i]?.number == 10){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 11){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 12){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 13){sum = sum + (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        else if(currentMethod == 4){
            for(i in 0..methodLevels[currentMethod-1])
            {
                if(dek.deck[i]?.number == 1){sum = sum + 0}
                else if(dek.deck[i]?.number == 2){sum = sum + 1}
                else if(dek.deck[i]?.number == 3){sum = sum + 1}
                else if(dek.deck[i]?.number == 4){sum = sum + 2}
                else if(dek.deck[i]?.number == 5){sum = sum + 2}
                else if(dek.deck[i]?.number == 6){sum = sum + 1}
                else if(dek.deck[i]?.number == 7){sum = sum + 1}
                else if(dek.deck[i]?.number == 8){sum = sum + 0}
                else if(dek.deck[i]?.number == 9){sum = sum + 0}
                else if(dek.deck[i]?.number == 10){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 11){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 12){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 13){sum = sum + (-2)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        else if(currentMethod == 5){
            for(i in 0..methodLevels[currentMethod-1])
            {
                if(dek.deck[i]?.number == 1){sum = sum + (-1)}
                else if(dek.deck[i]?.number == 2){sum = sum + 1}
                else if(dek.deck[i]?.number == 3){sum = sum + 1}
                else if(dek.deck[i]?.number == 4){sum = sum + 2}
                else if(dek.deck[i]?.number == 5){sum = sum + 2}
                else if(dek.deck[i]?.number == 6){sum = sum + 2}
                else if(dek.deck[i]?.number == 7){sum = sum + 1}
                else if(dek.deck[i]?.number == 8){sum = sum + 0}
                else if(dek.deck[i]?.number == 9){sum = sum + 0}
                else if(dek.deck[i]?.number == 10){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 11){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 12){sum = sum + (-2)}
                else if(dek.deck[i]?.number == 13){sum = sum + (-2)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        else{
            println("getSum fun inside Practice - error - currentMethod out of range")
        }

        return 0
    }


}