package com.example.card_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_playing.*

class PlayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        var cards = arrayOfNulls<game>(40)
        var i = 0
        val tag:String = "HOHO"
        //stvaranje karata

        cards[0] =  game(1, "kupe", 11, 10) // as
        cards[1] = game(2, "kupe", 0, 1)    // duja
        cards[2] = game(3, "kupe", 10, 9)   // trica
        cards[3] = game(4, "kupe", 0, 2)    // cetvorka
        cards[4] = game(5, "kupe", 0, 3)    // petica
        cards[5] = game(6, "kupe", 0, 4)    // sestica
        cards[6] = game(7, "kupe", 0, 5)    // sedmica
        cards[7] = game(11, "kupe", 2, 6)    // fanat
        cards[8] = game(12, "kupe", 3, 7)   // konj
        cards[9] = game(13, "kupe", 4, 8)   // kralj
        cards[10] =  game(1, "spade", 11, 10) // as
        cards[11] = game(2, "spade", 0, 1)    // duja
        cards[12] = game(3, "spade", 10, 9)   // trica
        cards[13] = game(4, "spade", 0, 2)    // cetvorka
        cards[14] = game(5, "spade", 0, 3)    // petica
        cards[15] = game(6, "spade", 0, 4)    // sestica
        cards[16] = game(7, "spade", 0, 5)    // sedmica
        cards[17] = game(11, "spade", 2, 6)    // fanat
        cards[18] = game(12, "spade", 3, 7)   // konj
        cards[19] = game(13, "spade", 4, 8)   // kralj
        cards[20] =  game(1, "dinari", 11, 10) // as
        cards[21] = game(2, "dinari", 0, 1)    // duja
        cards[22] = game(3, "dinari", 10, 9)   // trica
        cards[23] = game(4, "dinari", 0, 2)    // cetvorka
        cards[24] = game(5, "dinari", 0, 3)    // petica
        cards[25] = game(6, "dinari", 0, 4)    // sestica
        cards[26] = game(7, "dinari", 0, 5)    // sedmica
        cards[27] = game(11, "dinari", 2, 6)    // fanat
        cards[28] = game(12, "dinari", 3, 7)   // konj
        cards[29] = game(13, "dinari", 4, 8)   // kralj
        cards[30] =  game(1, "bastoni", 11, 10) // as
        cards[31] = game(2, "bastoni", 0, 1)    // duja
        cards[32] = game(3, "bastoni", 10, 9)   // trica
        cards[33] = game(4, "bastoni", 0, 2)    // cetvorka
        cards[34] = game(5, "bastoni", 0, 3)    // petica
        cards[35] = game(6, "bastoni", 0, 4)    // sestica
        cards[36] = game(7, "bastoni", 0, 5)    // sedmica
        cards[37] = game(11, "bastoni", 2, 6)    // fanat
        cards[38] = game(12, "bastoni", 3, 7)   // konj
        cards[39] = game(13, "bastoni", 4, 8)   // kralj


       var niz:IntArray = intArrayOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39)


        start_game.setOnClickListener {
            niz.shuffle()

            player_text1.text = "${cards[niz[0]]?.number} ${cards[niz[0]]?.type}"
            player_text2.text = "${cards[niz[1]]?.number} ${cards[niz[1]]?.type}"
            player_text3.text = "${cards[niz[2]]?.number} ${cards[niz[2]]?.type}"
            player_text4.text = "${cards[niz[3]]?.number} ${cards[niz[3]]?.type}"

            adut_text.text =  "${cards[niz[39]]?.number} ${cards[niz[39]]?.type}"
        }



    } // end of fun onCreate()


    //funkcija koja vraca objekt klase result_of_hand_win, odnosno vraca koji je igrac pobijedio te broj bodova pridjeljen tom igracu




}   // end of class