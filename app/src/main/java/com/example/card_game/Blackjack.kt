package com.example.card_game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_blackjack.*

class Blackjack : AppCompatActivity() {


    val handler = Handler()
    var ulog: Int = 0
    var result_background = Color.parseColor("#ffffff")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blackjack)

        val vuci_on:Int = R.drawable.vuci_button_on
        val vuci_off:Int = R.drawable.vuci_button_off
        val vuci_disabled:Int = R.drawable.vuci_button_disabled
        val dosta_on: Int = R.drawable.dosta_button_on
        val dosta_off: Int = R.drawable.dosta_button_off
        val dosta_disabled: Int = R.drawable.dosta_button_disabled
        val tag: String = "provjera"
        var ulogCheck: Boolean = true
        var checkVuci: Boolean = false
        var checkDosta: Boolean = false
        var checkDijeli: Boolean = true
        var pHandPc: Int = 0
        var pDeck: Int = 0
        var pHandPlayer: Int = 0
        val handler = Handler()
        var no_card: Int = R.drawable.no_card
        var pomSum: Int = 0
        var playerSum: Int = 0
        var pcSum: Int = 0

        var stanje: Int = 1000
        var bjNiz: IntArray = intArrayOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51)
        stanje_text.text = "STANJE: $stanje kn"


        var bj_cards = arrayOfNulls<blackjack_class>(52)
        bj_cards[0] = blackjack_class(1,"tref",11, pic = R.drawable.tref_1)
        bj_cards[1] = blackjack_class(2,"tref", 2, pic = R.drawable.tref_2)
        bj_cards[2] = blackjack_class(3,"tref", 3, pic = R.drawable.tref_3)
        bj_cards[3] = blackjack_class(4,"tref",4, pic = R.drawable.tref_4)
        bj_cards[4] = blackjack_class(5,"tref",5, pic = R.drawable.tref_5)
        bj_cards[5] = blackjack_class(6,"tref",6, pic = R.drawable.tref_6)
        bj_cards[6] = blackjack_class(7,"tref",7,pic = R.drawable.tref_7)
        bj_cards[7] = blackjack_class(8,"tref",8, pic = R.drawable.tref_8)
        bj_cards[8] = blackjack_class(9,"tref", 9,  pic = R.drawable.tref_9)
        bj_cards[9] = blackjack_class(10,"tref", 10, pic = R.drawable.tref_10)
        bj_cards[10] = blackjack_class(11, "tref", 10, pic = R.drawable.tref_11)
        bj_cards[11] = blackjack_class(12,"tref",10, pic = R.drawable.tref_12)
        bj_cards[12] = blackjack_class(13,"tref",10, pic = R.drawable.tref_13)
        bj_cards[13] = blackjack_class(1,"pik",11, pic = R.drawable.pik_1)
        bj_cards[14] = blackjack_class(2,"pik", 2, pic = R.drawable.pik_2)
        bj_cards[15] = blackjack_class(3,"pik", 3, pic = R.drawable.pik_3)
        bj_cards[16] = blackjack_class(4,"pik",4, pic = R.drawable.pik_4)
        bj_cards[17] = blackjack_class(5,"pik",5, pic = R.drawable.pik_5)
        bj_cards[18] = blackjack_class(6,"pik",6, pic = R.drawable.pik_6)
        bj_cards[19] = blackjack_class(7,"pik",7, pic = R.drawable.pik_7)
        bj_cards[20] = blackjack_class(8,"pik",8, pic = R.drawable.pik_8)
        bj_cards[21] = blackjack_class(9,"pik", 9, pic = R.drawable.pik_9)
        bj_cards[22] = blackjack_class(10,"pik", 10, pic = R.drawable.pik_10)
        bj_cards[23] = blackjack_class(11, "pik", 10, pic = R.drawable.pik_11)
        bj_cards[24] = blackjack_class(12,"pik",10, pic = R.drawable.pik_12)
        bj_cards[25] = blackjack_class(13,"pik",10, pic = R.drawable.pik_13)
        bj_cards[26] = blackjack_class(1,"karo",11, pic = R.drawable.karo_1)
        bj_cards[27] = blackjack_class(2,"karo", 2, pic = R.drawable.karo_2)
        bj_cards[28] = blackjack_class(3,"karo", 3, pic = R.drawable.karo_3)
        bj_cards[29] = blackjack_class(4,"karo",4, pic = R.drawable.karo_4)
        bj_cards[30] = blackjack_class(5,"karo",5, pic = R.drawable.karo_5)
        bj_cards[31] = blackjack_class(6,"karo",6, pic = R.drawable.karo_6)
        bj_cards[32] = blackjack_class(7,"karo",7, pic = R.drawable.karo_7)
        bj_cards[33] = blackjack_class(8,"karo",8, pic = R.drawable.karo_8)
        bj_cards[34] = blackjack_class(9,"karo", 9, pic = R.drawable.karo_9)
        bj_cards[35] = blackjack_class(10,"karo", 10, pic = R.drawable.karo_10)
        bj_cards[36] = blackjack_class(11, "karo", 10, pic = R.drawable.karo_11)
        bj_cards[37] = blackjack_class(12,"karo",10, pic = R.drawable.karo_12)
        bj_cards[38] = blackjack_class(13,"karo",10, pic = R.drawable.karo_13)
        bj_cards[39] = blackjack_class(1,"herc",11, pic = R.drawable.herc_1)
        bj_cards[40] = blackjack_class(2,"herc", 2, pic = R.drawable.herc_2)
        bj_cards[41] = blackjack_class(3,"herc", 3, pic = R.drawable.herc_3)
        bj_cards[42] = blackjack_class(4,"herc",4, pic = R.drawable.herc_4)
        bj_cards[43] = blackjack_class(5,"herc",5, pic = R.drawable.herc_5)
        bj_cards[44] = blackjack_class(6,"herc",6, pic = R.drawable.herc_6)
        bj_cards[45] = blackjack_class(7,"herc",7, pic = R.drawable.herc_7)
        bj_cards[46] = blackjack_class(8,"herc",8, pic = R.drawable.herc_8)
        bj_cards[47] = blackjack_class(9,"herc", 9, pic = R.drawable.herc_9)
        bj_cards[48] = blackjack_class(10,"herc", 10, pic = R.drawable.herc_10)
        bj_cards[49] = blackjack_class(11, "herc", 10, pic = R.drawable.herc_11)
        bj_cards[50] = blackjack_class(12,"herc",10, pic = R.drawable.herc_12)
        bj_cards[51] = blackjack_class(13,"herc",10, pic = R.drawable.herc_13)


        bjNiz.shuffle()
        vuci_button.setBackgroundResource(vuci_disabled)
        dosta_button.setBackgroundResource(dosta_disabled)

        dijeli_button.setOnClickListener {
                    if (checkDijeli == false) {
                        show_text.text = "IGRA U TIJEKU..."
                         handler.postDelayed({
                            show_text.text = ""
                             }, 2000)
                     }
                    else if (((ulog1.isChecked == false) && (ulog2.isChecked == false) && (ulog3.isChecked == false))||(ulogCheck == false)) {
                        Toast.makeText(this, "Odaberi ulog, najbolje na 500!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else if (stanje <= 0 || ulog > stanje) {
                        Toast.makeText(this, "Nedovoljno stanje na računu!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        checkDijeli = false
                        Log.d(
                            tag,
                            "\nBefore\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n"
                        )


                        dosta_button.setBackgroundResource(dosta_off)
                        vuci_button.setBackgroundResource(vuci_off)
                        pcSum = 0
                        checkDosta = true
                        checkVuci = true

                        reset_views(
                            pc_card_background,
                            pc_second_card,
                            pc_third_card,
                            pc_forth_card,
                            pc_fifth_card,
                            pc_sixth_card,
                            no_card
                        )
                        reset_views(
                            player_card_background,
                            player_second_card,
                            player_third_card,
                            player_forth_card,
                            player_fifth_card,
                            player_sixth_card,
                            no_card
                        )

                        pDeck = position_check(pDeck, bjNiz)
                        player_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck, bjNiz)
                        pcSum = pcSum + bj_cards[bjNiz[pDeck]]!!.value
                        pc_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                        playerSum = 0
                        playerSum = playerSum + bj_cards[bjNiz[pDeck - 1]]!!.value
                        pHandPlayer = 0
                        pHandPlayer = pHandPlayer + 1
                        stanje = stanje - ulog
                        stanje_text.text = "STANJE: $stanje kn"
                        pc_sum.text = "$pcSum"


                        player_sum.text = "$playerSum"

                    } // kraj svih provjera tj kraj else-a

            Log.d(tag, "\nAfter\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n")
        } // end of dijeli_button

        vuci_button.setOnClickListener {

            Log.d(tag,"\nBefore\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n")

            if(checkVuci == false)
            {
                println("Error")
            }
            else {
                vuci_button.setBackgroundResource(vuci_on)

                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck,bjNiz)

                    if (pHandPlayer == 1) {
                        player_second_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    } else if (pHandPlayer == 2) {
                        player_third_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    } else if (pHandPlayer == 3) {
                        player_forth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    } else if (pHandPlayer == 4) {
                        player_fifth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    } else if (pHandPlayer == 5) {
                        player_sixth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                        pHandPlayer = 0
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value
                    pHandPlayer = pHandPlayer + 1

                    if (playerSum >= 0 && playerSum <=21) {

                        handler.postDelayed({
                            vuci_button.setBackgroundResource(vuci_off)
                        }, 200)

                    } else if (playerSum > 21) {
                        stanje = stanje
                        checkDosta = false
                        checkVuci = false
                        Toast.makeText(this, "Preko 21!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  -${ulog} kn", show_text)
                        vuci_button.setBackgroundResource(vuci_disabled)
                        dosta_button.setBackgroundResource(dosta_disabled)
                        checkDijeli = true
                    }
                    else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                player_sum.text = "$playerSum"
            } // end of provjera checkVuci


            Log.d(tag,"\nAfter\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n")



        } // end of vuci_button

        dosta_button.setOnClickListener {
            Log.d(tag,"\nBefore\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n")
            // dealer section
            if (checkDosta == false) {
               // Toast.makeText(this, "Podijeli prvo karte!!", Toast.LENGTH_SHORT).show()
            } else{
                dosta_button.setBackgroundResource(dosta_on)
                checkDosta = false
                pHandPc = 1
                pDeck = pDeck + 1
                pDeck = position_check(pDeck,bjNiz)

            while (pcSum < 17) {

                if (pHandPc == 1) {
                    pc_second_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                } else if (pHandPc == 2) {
                    pc_third_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                } else if (pHandPc == 3) {
                    pc_forth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                } else if (pHandPc == 4) {
                    pc_fifth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                } else if (pHandPc == 5) {
                    pc_sixth_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                }

                pHandPc = pHandPc + 1
                pcSum = pcSum + bj_cards[bjNiz[pDeck]]!!.value

                if (pcSum > 21) {
                    Toast.makeText(this, "You WIN!!!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  +${ulog*2}!", show_text)
                    stanje = stanje + ulog*2
                    stanje_text.text = "STANJE: $stanje kn"
                    pHandPc = 0
                    pHandPlayer = 0
                    checkVuci = false
                    checkDijeli = true
                } else if (pcSum == playerSum && pcSum >= 17) {
                    Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  +0 kn!", show_text)
                    stanje = stanje + ulog
                    stanje_text.text = "STANJE: $stanje kn"
                        pHandPc = 0
                    pHandPlayer = 0
                    checkVuci = false
                    checkDijeli = true
                } else if ((pcSum > playerSum) && (pcSum <= 21)) {
                    Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  -${ulog} kn", show_text)
                    stanje = stanje
                    stanje_text.text = "STANJE: $stanje kn"
                    pHandPc = 0
                    pHandPlayer = 0
                    checkVuci = false
                    checkDijeli = true

                } else if (pcSum < playerSum && pcSum >= 17) {
                    Toast.makeText(this, "Ajdeeee!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  +${ulog*2} kn!!", show_text)
                    stanje = stanje + ulog*2
                    stanje_text.text = "STANJE: $stanje kn"
                    pHandPc = 0
                    pHandPlayer = 0
                    checkVuci = false
                    checkDijeli = true
                }

                pDeck = pDeck + 1
                pDeck = position_check(pDeck,bjNiz)
            }
                pc_sum.text = "$pcSum"

                pcSum = 0

                handler.postDelayed({
                    dosta_button.setBackgroundResource(dosta_disabled)
                    vuci_button.setBackgroundResource(vuci_disabled)
                },200)

        }// end of provjera check-a

            Log.d(tag,"\nAfter\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\n")

            handler.postDelayed({
                dosta_button.setBackgroundResource(dosta_disabled)
            },200)

        } // end of dosta_button


    } // end of onCreate()



    fun reset_views(a:ImageView,b:ImageView,c:ImageView,d:ImageView,e:ImageView,f:ImageView,m:Int)
    {
        a.setImageResource(m)
        b.setImageResource(0)
        c.setImageResource(0)
        d.setImageResource(0)
        e.setImageResource(0)
        f.setImageResource(0)
    }

    fun Ulog1Clicked(view: View)
    {
        ulog2.isChecked = false
        ulog3.isChecked = false
        ulog = 100
    }
    fun Ulog2Clicked(view: View)
    {
        ulog1.isChecked = false
        ulog3.isChecked = false
        ulog = 200
    }
    fun Ulog3Clicked(view: View)
    {
        ulog1.isChecked = false
        ulog2.isChecked = false
        ulog = 500
    }

    fun dijeli()
    {

    }

    fun show_n_disappear(message: String, show: TextView)
    {
      //  var color = Color.parseColor("#4E65DA")
      //  var color2 = Color.parseColor("#2C3FA1")
        show.text = message
       // show.setBackgroundColor(color2)
        handler.postDelayed({
                show.text = ""
               // show.setBackgroundColor(color)
        }, 3000)
    }

    // provjera pozicije u deck
    // fukcija provjerava je li deck dosao do zadnje karte
    // te ako je, pozicija se vraca na index 0 te se deck
    // ponovno mijesa
    fun position_check(deckPosition: Int, deck: IntArray): Int {
        var pos: Int = deckPosition
        if(deckPosition == 51)
        {
            pos = 0
            deck.shuffle()
        }
        return pos

    }
}   // end of class BlackJack