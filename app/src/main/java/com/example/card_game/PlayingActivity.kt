package com.example.card_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_playing.*

class PlayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        var cards = arrayOfNulls<game>(40)
        var i = 0
        val tag:String = "HOHO"

        val handler = Handler()

        var player: player = player(1, 0, "Player")
        var computer: player = player(2, 0, "Computer")
        var position1: Int = 0
        var position2: Int = 0
        var faza: Int = 0


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


            // default/start postavke
            player.bodovi = 0
            computer.bodovi = 0
            position1 = 0
            position2 = 20
            faza = 0
            stol1_text.text = ""
            stol2_text.text = ""
            stol2_text.text = ""
            stol2_text.text = ""
            player_card1.text = player_text1.text
            player_card2.text = player_text2.text
            player_card3.text = player_text3.text
            player_card4.text = player_text4.text
        }

        player_card1.setOnClickListener {

            faza = faza + 1

            if(stol1_text.text == "")
            {
                stol1_text.text = player_text1.text
                player_card1.text = ""
            }
            else if(stol2_text.text == "")
            {
                stol2_text.text = player_text1.text
                player_card1.text = ""
            }
            else if(stol3_text.text == "")
            {
                stol3_text.text = player_text1.text
                player_card1.text = ""
            }
            else if(stol4_text.text == "")
            {
                stol4_text.text = player_text1.text
                player_card1.text = ""
            }

            if(faza == 4)
            {
                faza = 0

                position1 = position1 + 1

                handler.postDelayed({
                    // do something after 1000ms

                    stol1_text.text = ""
                    stol2_text.text = ""
                    stol3_text.text = ""
                    stol4_text.text = ""

                }, 1000)
            }
        }

        player_card2.setOnClickListener {
            faza = faza + 1

            if(stol1_text.text == "")
            {
                stol1_text.text = player_text2.text
                player_card2.text = ""
            }
            else if(stol2_text.text == "")
            {
                stol2_text.text = player_text2.text
                player_card2.text = ""
            }
            else if(stol3_text.text == "")
            {
                stol3_text.text = player_text2.text
                player_card2.text = ""
            }
            else if(stol4_text.text == "")
            {
                stol4_text.text = player_text2.text
                player_card2.text = ""
            }

            if(faza == 4)
            {
                faza = 0
                handler.postDelayed({
                    // do something after 1000ms

                    stol1_text.text = ""
                    stol2_text.text = ""
                    stol3_text.text = ""
                    stol4_text.text = ""

                }, 1000)
            }
        }

        player_card3.setOnClickListener {
            faza = faza + 1

            if(stol1_text.text == "")
            {
                stol1_text.text = player_text3.text
                player_card3.text = ""
            }
            else if(stol2_text.text == "")
            {
                stol2_text.text = player_text3.text
                player_card3.text = ""
            }
            else if(stol3_text.text == "")
            {
                stol3_text.text = player_text3.text
                player_card3.text = ""
            }
            else if(stol4_text.text == "")
            {
                stol4_text.text = player_text3.text
                player_card3.text = ""
            }

            if(faza == 4)
            {
                faza = 0
                handler.postDelayed({
                    // do something after 1000ms

                    stol1_text.text = ""
                    stol2_text.text = ""
                    stol3_text.text = ""
                    stol4_text.text = ""

                }, 1000)
            }
        }

        player_card4.setOnClickListener {
            faza = faza + 1

            if(stol1_text.text == "")
            {
                stol1_text.text = player_text4.text
                player_card4.text = ""
            }
            else if(stol2_text.text == "")
            {
                stol2_text.text = player_text4.text
                player_card4.text = ""
            }
            else if(stol3_text.text == "")
            {
                stol3_text.text = player_text4.text
                player_card4.text = ""
            }
            else if(stol4_text.text == "")
            {
                stol4_text.text = player_text4.text
                player_card4.text = ""
            }



            if(faza == 4)
            {
                faza = 0
                handler.postDelayed({
                    // do something after 1000ms

                    stol1_text.text = ""
                    stol2_text.text = ""
                    stol3_text.text = ""
                    stol4_text.text = ""

                }, 1000)
            }
        }




    } // end of fun onCreate()


    //funkcija koja vraca objekt klase result_of_hand_win, odnosno vraca koji je igrac pobijedio te broj bodova pridjeljen tom igracu
    fun hand_winner(first_card: game, second_card: game, third_card: game, forth_card: game, adut: game, first_player: player, second_player: player):player
    {
        var cardWinner: game = game()
        var handWinner: player = player()
        var bodovi: Int


        var hand_adut: game = first_card

        // deklaracija aduta u tenutnoj ruci, odnosno provjera je li adut prve karte jednak glavnom adutu u igri
        // ako nije jednak, hand_adut cemo postaviti na adut jednak adutu prve karte


        // prva provjera -> provjeravamo je li karta ima tim glavnog aduta i ako je je li veća od prethodne karte s tim da početnu prvu kartu
        // postavljamo kao da je pobjednik prvi igrac
        // ako nijedan uvjet od ovih nije zadovoljen, znači da nijedna karta nema tip glavnog aduta u igri
        // te trebamo samo provjeriti
        if(hand_adut.type != adut.type)
        {
            hand_adut.type = adut.type
        }

        if(first_card.type == adut.type)
        {
            handWinner = first_player
        }
        else if((second_card.type == adut.type) && (second_card.value > first_card.value))
        {
            handWinner = second_player
        }
        else if((third_card.type == adut.type) && (third_card.value > second_card.value))
        {
            handWinner = first_player
        }
        else if((forth_card.type == adut.type) && (forth_card.value > third_card.value))
        {
            handWinner = second_player
        }

        // druga provjera -> provjeravamo je li karta ima tim hand_aduta i ako je je li veća od prethodne karte s tim da početnu prvu kartu
        // postavljamo kao da je pobjednik prvi igrac
        // ako nijedan uvjet od ovih nije zadovoljen, znači da nijedna karta nema tip glavnog aduta u igri
        // te trebamo samo provjeriti
        if(first_card.type == hand_adut.type)
        {
            handWinner = first_player
        }
        else if((second_card.type == hand_adut.type) && (second_card.value > first_card.value))
        {
            handWinner = second_player
        }
        else if((third_card.type == hand_adut.type) && (third_card.value > second_card.value))
        {
            handWinner = first_player
        }
        else if((forth_card.type == hand_adut.type) && (forth_card.value > third_card.value))
        {
            handWinner = second_player
        }
        else
        {
            handWinner = first_player
        }

        bodovi = first_card.points + second_card.points + third_card.points + forth_card.points

        handWinner.bodovi = bodovi


        return handWinner

    } // end of fun hand_winner



    fun test_hand()
    {

    }


}   // end of class