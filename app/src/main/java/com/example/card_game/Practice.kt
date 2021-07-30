package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import android.widget.Toast

class Practice(file: SharedPreferences)
{
    private val data: SharedPreferences = file
    val editor = data.edit()
    var currentMethod = 0
    var dek : Deck = Deck()
    var methodLevels: IntArray = intArrayOf(0,0,0,0,0)

    init{
        methodLevels[0] = data.getInt("M1_LVL", 1)
        methodLevels[1] = data.getInt("M2_LVL", 1)
        methodLevels[2] = data.getInt("M3_LVL", 1)
        methodLevels[3] = data.getInt("M4_LVL", 1)
        methodLevels[4] = data.getInt("M5_LVL", 1)

        currentMethod = data.getInt("METHOD", 0)
    }

    fun generateDeck()
    {
        dek.deck.shuffle()
    }

    fun setMethodlvl(num: Int, lvl: Int)
    {
        if(num == 1) {
            editor.putInt("M1_LVL", lvl)
            editor.commit()
        }
        else if(num == 2) {
            editor.putInt("M2_LVL", lvl)
            editor.commit()
        }
        else if(num == 3){
            editor.putInt("M3_LVL", lvl)
            editor.commit()
        }
        else if(num == 4){
            editor.putInt("M4_LVL", lvl)
            editor.commit()
        }
        else if(num == 5){
            editor.putInt("M5_LVL", lvl)
            editor.commit()
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

    fun getSum(broj_karata: Int): Int
    {
        var num_of_cards: Int = broj_karata
        var sum: Int = 0
        if(currentMethod == 1){
            for(i in 0..num_of_cards)
            {
                if(dek.deck[i]?.number == 1){sum += (-1)}
                else if(dek.deck[i]?.number == 2){sum += 1}
                else if(dek.deck[i]?.number == 3){sum += 1}
                else if(dek.deck[i]?.number == 4){sum += 1}
                else if(dek.deck[i]?.number == 5){sum += 1}
                else if(dek.deck[i]?.number == 6){sum += 1}
                else if(dek.deck[i]?.number == 7){sum += 0}
                else if(dek.deck[i]?.number == 8){sum += 1}
                else if(dek.deck[i]?.number == 9){sum += 1}
                else if(dek.deck[i]?.number == 10){sum += (-1)}
                else if(dek.deck[i]?.number == 11){sum += (-1)}
                else if(dek.deck[i]?.number == 12){sum += (-1)}
                else if(dek.deck[i]?.number == 13){sum += (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        if(currentMethod == 2){
            for(i in 0..num_of_cards)
            {
                if(dek.deck[i]?.number == 1){sum += (-1)}
                else if(dek.deck[i]?.number == 2){sum += 1}
                else if(dek.deck[i]?.number == 3){sum += 1}
                else if(dek.deck[i]?.number == 4){sum += 1}
                else if(dek.deck[i]?.number == 5){sum += 1}
                else if(dek.deck[i]?.number == 6){sum += 1}
                else if(dek.deck[i]?.number == 7){sum += 1}
                else if(dek.deck[i]?.number == 8){sum += 1}
                else if(dek.deck[i]?.number == 9){sum += 1}
                else if(dek.deck[i]?.number == 10){sum += (-1)}
                else if(dek.deck[i]?.number == 11){sum += (-1)}
                else if(dek.deck[i]?.number == 12){sum += (-1)}
                else if(dek.deck[i]?.number == 13){sum += (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        if(currentMethod == 3){
            for(i in 0..num_of_cards)
            {
                if(dek.deck[i]?.number == 1){sum += 0}
                else if(dek.deck[i]?.number == 2){sum += 0}
                else if(dek.deck[i]?.number == 3){sum += 1}
                else if(dek.deck[i]?.number == 4){sum += 1}
                else if(dek.deck[i]?.number == 5){sum += 1}
                else if(dek.deck[i]?.number == 6){sum += 1}
                else if(dek.deck[i]?.number == 7){sum += 0}
                else if(dek.deck[i]?.number == 8){sum += 0}
                else if(dek.deck[i]?.number == 9){sum += 0}
                else if(dek.deck[i]?.number == 10){sum += (-1)}
                else if(dek.deck[i]?.number == 11){sum += (-1)}
                else if(dek.deck[i]?.number == 12){sum += (-1)}
                else if(dek.deck[i]?.number == 13){sum += (-1)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        if(currentMethod == 4){
            for(i in 0..num_of_cards)
            {
                if(dek.deck[i]?.number == 1){sum += 0}
                else if(dek.deck[i]?.number == 2){sum += 1}
                else if(dek.deck[i]?.number == 3){sum += 1}
                else if(dek.deck[i]?.number == 4){sum += 2}
                else if(dek.deck[i]?.number == 5){sum += 2}
                else if(dek.deck[i]?.number == 6){sum += 1}
                else if(dek.deck[i]?.number == 7){sum += 1}
                else if(dek.deck[i]?.number == 8){sum += 0}
                else if(dek.deck[i]?.number == 9){sum += 0}
                else if(dek.deck[i]?.number == 10){sum += (-2)}
                else if(dek.deck[i]?.number == 11){sum += (-2)}
                else if(dek.deck[i]?.number == 12){sum += (-2)}
                else if(dek.deck[i]?.number == 13){sum += (-2)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }
        if(currentMethod == 5){
            for(i in 0..num_of_cards)
            {
                if(dek.deck[i]?.number == 1){sum += (-1)}
                else if(dek.deck[i]?.number == 2){sum += 1}
                else if(dek.deck[i]?.number == 3){sum += 1}
                else if(dek.deck[i]?.number == 4){sum += 2}
                else if(dek.deck[i]?.number == 5){sum += 2}
                else if(dek.deck[i]?.number == 6){sum += 2}
                else if(dek.deck[i]?.number == 7){sum += 1}
                else if(dek.deck[i]?.number == 8){sum += 0}
                else if(dek.deck[i]?.number == 9){sum += 0}
                else if(dek.deck[i]?.number == 10){sum += (-2)}
                else if(dek.deck[i]?.number == 11){sum += (-2)}
                else if(dek.deck[i]?.number == 12){sum += (-2)}
                else if(dek.deck[i]?.number == 13){sum += (-2)}
                else{Log.d("TAG","Error: class Practice, fun getSum")}
            }
            return sum
        }

        return 0
    }


}