package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_blackjack.*



//class Blackjack : AppCompatActivity(), RewardedVideoAdListener {

open class Blackjack : AppCompatActivity() {

    // outside

    val handler = Handler()
    var ulog: Int = 0
    val ulog_10: Int = R.drawable.ulog_10_off
    val ulog_25: Int = R.drawable.ulog_25_off
    val ulog_50: Int = R.drawable.ulog_50_off
    val ulog_100: Int = R.drawable.ulog_100_off
    val cancel_background: Int = R.drawable.cancel_button
    var maxBet: Int = 10000
    var currency: String = "$"
    val TAG: String = "TAG"
    var ulogCheck: Boolean = true
    var stanje: Int = 5000
    val default_on: Int = R.drawable.deal_cust_on
    val default_off: Int = R.drawable.deal_cust
    val repeatOn: Int = android.R.drawable.stat_notify_sync_noanim
    var lastBet: Int = 0
    var pDeck: Int = 0
    var counter: Int = 0
    var num_of_decks: Int = 0
    var deck: String = ""

    // variable where true = IN GAME and false = NEW GAME
    var game_state: Boolean = false

    override fun onBackPressed() {
       val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blackjack)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        loadData()
        loadNumDeck()
        loadPDeck()

        // sound variables
        var dealing_cards: MediaPlayer? = MediaPlayer.create(this, R.raw.dealing_cards_fix)
        var chipsound1: MediaPlayer? = MediaPlayer.create(this, R.raw.chip1_sound_fix)
        var winSound: MediaPlayer? = MediaPlayer.create(this, R.raw.allin_win)


        // image/color variables
        val show_suma: Int = R.drawable.custom_text
        val  overbet: Int = R.drawable.between_color


        // check variables
        var ulogCheck: Boolean = true
        var checkVuci: Boolean = false
        var checkDosta: Boolean = false
        var checkDijeli: Boolean = true
        var checkDouble: Boolean = false
        var checkSplit: Boolean = false
        var splitEnabled: Boolean = false
        var checkRepeat: Boolean = false

        // pointer variables
        var pHandPlayer: Int = 0
        var pHandPc: Int = 0
        var pHandSplitPlayer: Int = 0


        // sum variables
        var splitSum: Int = 0
        var pomSplitSum: Int = 0
        var mainSplitSum: Int = 0
        var playerSum: Int = 0
        var pomPlayerSum: Int = 0
        var pcSum: Int = 0
        var mainPlayerSum: Int = 0
        var pomPcSum: Int = 0
        var mainPcSum: Int = 0
        var bj1: Int
        var bj2: Int
        var bj3: Int
        var bj4: Int

        // other variables
        val handler = Handler()
        var no_card: Int = R.drawable.no_card
        var i: Int = 0
        var j: Int = 0
        var pCounter: Int = 0
        var repeatBet: Int = 0
        var pom: Int = 0
        var brojac: Int = 0

        // game state storage
        var current_state : GameState = GameState(0,0,0,0, false)


        // button/view variables

        // false = new true = continue
        loadGameState()


        // deklariranje niza te odredivanje njegove velicine
        // velicina iznosi broj dekova * 52 s obzirom da svaki dek ima 52 karte
        var bjNiz = IntArray(num_of_decks * 52)



        var bj_cards = arrayOfNulls<blackjack_class>(52)

        bj_cards[0] = blackjack_class(1,"tref",1, pic = R.drawable.tref_1)
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
        bj_cards[13] = blackjack_class(1,"pik",1, pic = R.drawable.pik_1)
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
        bj_cards[26] = blackjack_class(1,"karo",1, pic = R.drawable.karo_1)
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
        bj_cards[39] = blackjack_class(1,"herc",1, pic = R.drawable.herc_1)
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


        // setting up screen on the very start
        dijeli_button.setBackgroundResource(default_on)
        cancel_bet.setBackgroundResource(0)
        repeat_bet.setImageResource(0)
        balance_text.text = "$stanje"
        cards_left.text = "${pDeck}/${num_of_decks*52}"

        if(game_state == false) {

            deck = newGameState(num_of_decks, bjNiz)
            saveDeckString()
            Log.d(TAG,"game_state = false $deck")
        }
        else if(game_state == true)
        {
            loadDeckString()
            fillDeck(deck, bjNiz)
            if(current_state.status == true)
            {
                pHandPlayer = current_state.lastPDeck - current_state.firstPDeck - 2
                pom = current_state.firstPDeck
                player_card_background.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                pom++
                player_second_card.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                pom++
                pc_card_background.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                pom++
                pc_second_card.setImageResource(no_card)
                pom++

                if(pom != current_state.lastPDeck)
                {
                    player_third_card.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                    pom++
                }
                if(pom != current_state.lastPDeck)
                {
                    player_forth_card.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                    pom++
                }
                if(pom != current_state.lastPDeck)
                {
                    player_fifth_card.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                    pom++
                }
                if(pom != current_state.lastPDeck)
                {
                    player_sixth_card.setImageResource(bj_cards[bjNiz[pom]]!!.pic)
                    pom++
                }
                checkDosta = true
                checkVuci = true
                pcSum = current_state.pcSum
                playerSum = current_state.playerSum

            }


            Log.d(TAG,"game_state = true $deck")
        }


        handler.postDelayed({
            background_text.setText("")
        }, 1500)



        /* test split button
        bjNiz[0] = 0
        bjNiz[1] = 0 */

        dijeli_button.setOnClickListener {

            if (checkDijeli == false) {
                show_text.text = "IN GAME..."
                handler.postDelayed({
                    show_text.text = ""
                }, 2000)
            }
            else if (ulog == 0) {
                Toast.makeText(this, "Place your bet!", Toast.LENGTH_SHORT)
                    .show()
            }
            else if(stanje == 0 || stanje < ulog)
            {
                Toast.makeText(this, "Insuficient balance!", Toast.LENGTH_SHORT).show()
            }
            else {

                Log.d(
                    TAG,
                    "\nBefore - DIJELI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )

                current_state.firstPDeck = pDeck
                current_state.numberPlayerCards = 2
                current_state.numberPcCards = 2

                repeatBet = ulog
                checkRepeat = false
                checkDijeli = false
                ulogCheck = false
                checkDosta = true
                checkVuci = true

                repeat_bet.setImageResource(0)
                cancel_bet.setBackgroundResource(0)
                dijeli_button.setBackgroundResource(default_off)
                dosta_button.setBackgroundResource(default_on)
                vuci_button.setBackgroundResource(default_on)

                dealing_cards?.start() // sound



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


                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                //setting up player sum
                playerSum = 0
                playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value
                //setting up pomPlayer sum
                pomPlayerSum = 0
                pomPlayerSum = pomPlayerSum + bj_cards[bjNiz[pDeck]]!!.value

                bj1 = bj_cards[bjNiz[pDeck]]!!.value
                bj3 = bj_cards[bjNiz[pDeck]]!!.number


                // counter part
                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                counter_text.text = "$counter"
                // end of counter part

                if(bj_cards[bjNiz[pDeck]]!!.number == 1)
                {
                    pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                // setting up player's first card instead of player's background card(no_card)
                player_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)


                // setting up sum text above cards - player
                sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                Log.d(
                    TAG,
                    "\nBefore - DIJELI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )

                // fix - player must have two cards at start
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value

                Log.d(
                    "Nakon 357" ,
                    "\nBefore - DIJELI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )
                //setting up pomPlayer sum
                pomPlayerSum = pomPlayerSum + bj_cards[bjNiz[pDeck]]!!.value
                if(bj_cards[bjNiz[pDeck]]!!.number == 1)
                {
                    pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                // setting up player's second card
                player_second_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)


                // setting up sum text above cards - player
                sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                bj2 = bj_cards[bjNiz[pDeck]]!!.value
                bj4 = bj_cards[bjNiz[pDeck]]!!.number


                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                counter_text.text = "$counter"
                // end of counter part

                //increasing "pointer" to deck +1 and that will be pc's first card
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)

                //setting up pc sum
                pcSum = 0
                pcSum = pcSum + bj_cards[bjNiz[pDeck]]!!.value

                //setting up pomPc sum
                pomPcSum = 0
                pomPcSum = pomPcSum + bj_cards[bjNiz[pDeck]]!!.value
                if(bj_cards[bjNiz[pDeck]]!!.number == 1)
                {
                    pomPcSum = pomPcSum + 11 - 1 // replacing A values: A-1 -> A11
                }
                // setting up pc's first card instead of pc's background card(no_card)
                pc_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)

                // postavljanje
                pc_second_card.setImageResource(R.drawable.deck1_backside)


                // counter part
                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                counter_text.text = "$counter"
                // end of counter part

                // setting up sum text above cards - pc
                sum_text_dealer(pcSum, pomPcSum, pc_sum, pc_sum2, show_suma)


                // "pointer" to player's card, setting it to 0 and also increasing it line after to 2, because we already have two cards
                pHandPlayer = 0
                pHandPlayer = pHandPlayer + 2
                //
                stanje = stanje - ulog
                balance_text.text = "$stanje"

                // if player gets 21 in first two cards, he/she instantly wins
                if((bj1== 1 && bj2 == 10)||(bj1 == 10 && bj2 == 1))
                {
                    winSound?.start()
                    mainPlayerSum = sum_check(playerSum, pomPlayerSum)
                    Toast.makeText(this, "Blackjack!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  +${(ulog*2.5).toInt()} $currency!!", show_text)
                    stanje = stanje + ulog*2
                    balance_text.text = "$stanje"
                    pHandPc = 0
                    pHandPlayer = 0
                    checkVuci = false
                    checkDijeli = true
                    checkDosta = false
                    checkDouble = false
                    checkSplit = false
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    player_sum.text = "$mainPlayerSum"
                    vuci_button.setBackgroundResource(default_off)
                    dosta_button.setBackgroundResource(default_off)
                    dijeli_button.setBackgroundResource(default_on)
                    double_button.setBackgroundResource(default_off)
                    split_button.setBackgroundResource(default_off)
                    ulogCheck = true
                    ulog = 0
                    total_bet.text = "BET: 0 $currency"
                    bet_text_under_chip.text = ""
                    total_bet_chip.setImageResource(0)
                    if(repeatBet <= stanje) {
                        repeat_bet.setImageResource(repeatOn)
                        checkRepeat = true
                    }
                    pDeck = pDeck + 1

                    saveData()
                }

                // check checkDouble
                if(((playerSum == 9)||(playerSum == 10)||(playerSum==11))&&((pomPlayerSum == 9)||(pomPlayerSum == 10)||(pomPlayerSum==11))&&(ulog <= stanje))
                {
                    checkDouble = true
                    double_button.setBackgroundResource(default_on)
                    Log.d(TAG, "Sucessfull check")
                }

                // check checkSplit
                if((bj3 == bj4)&&(stanje >= ulog))
                {
                    split_button.setBackgroundResource(default_on)
                    checkSplit = true
                }

                Log.d(
                    TAG,
                    "\nAfter - DIJELI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )

                cards_left.text = "${pDeck+1}/${num_of_decks*52}"

                // card sound
                dealing_cards?.start()


            } // kraj svih provjera tj kraj else-a
        } // end of dijeli_button

        vuci_button.setOnClickListener {

            if(splitEnabled == false) {
                if (checkVuci == false) {
                    println("Error!")
                } else {
                    Log.d(
                        TAG,
                        "\nBefore - VUCI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                    )
                    dealing_cards?.start()
                    checkDouble = false
                    checkSplit = false
                    //splitEnabled = false
                    split_button.setBackgroundResource(default_off)
                    double_button.setBackgroundResource(default_off)
                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, num_of_decks)
                    savePDeck()
                    cards_left.text = "${pDeck+1}/${num_of_decks * 52}"

                    current_state.numberPlayerCards = current_state.numberPcCards + 1

                    // "pointer" to player's hand, used to check where card must be playes
                    if (splitEnabled == false) {
                        right_place_for_pic(
                            pDeck,
                            pHandPlayer,
                            player_second_card,
                            player_third_card,
                            player_forth_card,
                            player_fifth_card,
                            player_sixth_card,
                            player_seventh_card,
                            bjNiz,
                            bj_cards
                        )
                    } else if (splitEnabled == true) {
                        right_place_for_pic(
                            pDeck,
                            pHandPlayer,
                            split_second_card,
                            split_third_card,
                            split_forth_card,
                            split_fifth_card,
                            split_sixth_card,
                            split_seventh_card,
                            bjNiz,
                            bj_cards
                        )
                    }
                    // adding sum to playerSum
                    playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value

                    //adding that same sum to pomSum
                    pomPlayerSum = pomPlayerSum + bj_cards[bjNiz[pDeck]]!!.value
                    if (bj_cards[bjNiz[pDeck]]!!.number == 1) {
                        pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                    }
                    pHandPlayer = pHandPlayer + 1

                    if (playerSum >= 0 && playerSum <= 21 && pomPlayerSum >= 0 && pomPlayerSum <= 21) {


                    } else if (playerSum >= 0 && playerSum <= 21 && pomPlayerSum > 21) {

                    } else if ((playerSum > 21) && (pomPlayerSum > 21)) {
                        stanje = stanje
                        checkDosta = false
                        checkVuci = false
                        show_n_disappear("  -${ulog} $currency", show_text)
                        vuci_button.setBackgroundResource(default_off)
                        dosta_button.setBackgroundResource(default_off)
                        double_button.setBackgroundResource(default_off)
                        //dijeli_button.setBackgroundResource(default_on)

                        checkDijeli = true
                        ulogCheck = true
                        splitEnabled = false
                        total_bet_chip.setImageResource(0)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        saveData()
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck, bjNiz, num_of_decks)
                        savePDeck()
                        saveData()
                        current_state.lastPDeck = pDeck

                        if (repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }


                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                    // counter part
                    if (pCounter > 51) {
                        counter = 0
                    }
                    counter += cardCounter(bj_cards[bjNiz[pDeck]])
                    counter_text.text = "$counter"
                    // end of counter part

                    // regulation of what text shows between playerSum and pomPlayerSum
                    sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                    Log.d(
                        TAG,
                        "\nAfter - VUCI\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                    )
                } // end of provjera checkVuci
            }
            else if(splitEnabled == true)
            {
                dealing_cards?.start()
                checkDouble = false
                checkSplit = false
                //splitEnabled = false
                split_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                savePDeck()
                cards_left.text = "${pDeck+1}/${num_of_decks * 52}"

                right_place_for_pic(
                        pDeck,
                        pHandSplitPlayer,
                        split_second_card,
                        split_third_card,
                        split_forth_card,
                        split_fifth_card,
                        split_sixth_card,
                        split_seventh_card,
                        bjNiz,
                        bj_cards
                    )

                // adding sum to splitSum
                splitSum = splitSum + bj_cards[bjNiz[pDeck]]!!.value

                //adding that same sum to pomSplitSum
                pomSplitSum = pomSplitSum + bj_cards[bjNiz[pDeck]]!!.value
                if (bj_cards[bjNiz[pDeck]]!!.number == 1) {
                    pomSplitSum = pomSplitSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                pHandSplitPlayer = pHandSplitPlayer + 1

                if (splitSum >= 0 && splitSum <= 21 && pomSplitSum >= 0 && pomSplitSum <= 21) {


                } else if (splitSum >= 0 && splitSum <= 21 && pomSplitSum > 21) {

                } else if ((splitSum > 21) && (pomSplitSum > 21)) {
                    show_n_disappear("  -${ulog} $currency", show_text)
                    double_button.setBackgroundResource(default_off)
                    //dijeli_button.setBackgroundResource(default_on)

                    bet_text_under_chip.text = ""
                    saveData()
                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, num_of_decks)
                    savePDeck()

                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }

                // counter part
                if (pCounter > 51) {
                    counter = 0
                }
                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                counter_text.text = "$counter"
                // end of counter part

                // regulation of what text shows between splitSum and pomSplitSum
                sum_text(splitSum, pomSplitSum, player_sum, player_sum2, show_suma)
            }
            else
            {
                Log.d(TAG,"Something went wrong with splitEnabled check...")
            }


        } // end of vuci_button

        dosta_button.setOnClickListener {
            Log.d(
                TAG,
                "\nBefore - DOSTA\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
            )

            var totalWin: Int = 0

            mainPlayerSum = sum_check(playerSum, pomPlayerSum)
            mainPcSum = sum_check(pcSum, pomPcSum)

            // dealer section
            if (checkDosta == false) {
                // Toast.makeText(this, "Podijeli prvo karte!!", Toast.LENGTH_SHORT).show()
            } else{
                pc_second_card.setImageResource(0)

                // privremeno
                checkSplit = false
                splitEnabled = false
                split_button.setBackgroundResource(default_off)
                // end of privremeno


                checkDosta = false
                pHandPc = 1

                while (mainPcSum < 17) {


                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck,bjNiz, num_of_decks)
                    savePDeck()
                    cards_left.text = "${pDeck+1}/${num_of_decks*52}"


                    dealing_cards?.start()

                    // counter part
                    counter += cardCounter(bj_cards[bjNiz[pDeck]])
                    counter_text.text = "$counter"
                    // end of counter part


                    right_place_for_pic(
                        pDeck,
                        pHandPc,
                        pc_second_card,
                        pc_third_card,
                        pc_forth_card,
                        pc_fifth_card,
                        pc_sixth_card,
                        pc_seventh_card,
                        bjNiz,
                        bj_cards
                    )

                    pHandPc = pHandPc + 1
                    pcSum = pcSum + bj_cards[bjNiz[pDeck]]!!.value
                    Log.d(TAG, "pcSum = $pcSum")
                    // pomocna suma, koja sluzi za prikaz asa kao +1, a ne kao +11
                    pomPcSum = pomPcSum + bj_cards[bjNiz[pDeck]]!!.value
                    if (bj_cards[bjNiz[pDeck]]!!.number == 1) {
                        pomPcSum = pomPcSum - 1 + 11 // zamjena vrijednosti asa sa 1 na 11
                    }
                    sum_text_dealer(pcSum, pomPcSum, pc_sum, pc_sum2, show_suma)

                    mainPcSum = sum_check(pcSum, pomPcSum)

                    if (mainPcSum > 21) {
                        winSound?.start()
                        show_n_disappear("  +${ulog * 2} $currency!", show_text)
                        stanje = stanje + ulog * 2
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        saveData()
                        if (repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    } else if (mainPcSum >= 17 && mainPcSum <= 21 && mainPcSum > mainPlayerSum) {
                        show_n_disappear("  -${ulog} $currency", show_text)
                        stanje = stanje
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        saveData()
                        if (repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    } else if (mainPcSum == mainPlayerSum && mainPcSum >= 17) {
                        //  Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +0 $currency!", show_text)
                        stanje = stanje + ulog
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        saveData()
                        if (repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    } else if (mainPcSum < mainPlayerSum && mainPcSum >= 17) {
                        //Toast.makeText(this, "Ajdeeee!", Toast.LENGTH_SHORT).show()
                        winSound?.start()
                        show_n_disappear("  +${ulog * 2} $currency!!", show_text)
                        stanje = stanje + ulog * 2
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        saveData()
                        if (repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    }

                } // end of while(pcSum <17)


                dosta_button.setBackgroundResource(default_off)
                vuci_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)


            }// end of provjera check-a

            Log.d(
                TAG,
                "\nAfter - DOSTA\ncheckVuci = $checkVuci\ncheckDosta = $checkDosta\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
            )



        } // end of dosta_button

        double_button.setOnClickListener {

            if (checkDouble == false) {
                Log.d(TAG, "Double is not available")
            }
            else {

                stanje = stanje - ulog
                ulog = ulog*2

                // part of code from vuci_button

                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                cards_left.text = "${pDeck+1}/${num_of_decks * 52}"
                savePDeck()



                // "pointer" to player's hand, used to check where card must be playes
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

                // adding sum to playerSum
                playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value

                //adding that same sum to pomSum
                pomPlayerSum = pomPlayerSum + bj_cards[bjNiz[pDeck]]!!.value
                if (bj_cards[bjNiz[pDeck]]!!.number == 1) {
                    pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                pHandPlayer = pHandPlayer + 1

                if (playerSum >= 0 && playerSum <= 21 && pomPlayerSum >= 0 && pomPlayerSum <= 21) {


                } else if (playerSum >= 0 && playerSum <= 21 && pomPlayerSum > 21) {

                }  else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }


                // regulation of what text shows between playerSum and pomPlayerSum
                sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)
                // end of part of code from vuci_button

                // start of part of dosta_button

                pc_second_card.setImageResource(0)
                dosta_button.setBackgroundResource(default_off)
                checkDosta = false
                pHandPc = 1
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                savePDeck()

                mainPcSum = sum_check(pcSum, pomPcSum)
                mainPlayerSum = sum_check(playerSum, pomPlayerSum)

                while (mainPcSum < 17) {

                    Log.d(TAG, "Ulaz u petlju!")

                    right_place_for_pic(
                        pDeck,
                        pHandPc,
                        pc_second_card,
                        pc_third_card,
                        pc_forth_card,
                        pc_fifth_card,
                        pc_second_card,
                        pc_seventh_card,
                        bjNiz,
                        bj_cards
                    )

                    pHandPc = pHandPc + 1
                    pcSum = pcSum + bj_cards[bjNiz[pDeck]]!!.value
                    Log.d(TAG, "pcSum = $pcSum")
                    // pomocna suma, koja sluzi za prikaz asa kao +1, a ne kao +11
                    pomPcSum = pomPcSum + bj_cards[bjNiz[pDeck]]!!.value
                    if (bj_cards[bjNiz[pDeck]]!!.number == 1) {
                        pomPcSum = pomPcSum - 1 + 11 // zamjena vrijednosti asa sa 11 na 1
                    }
                    sum_text_dealer(pcSum, pomPcSum, pc_sum, pc_sum2, show_suma)

                    mainPcSum = sum_check(pcSum, pomPcSum)


                    if (mainPcSum > 21) {
                        // Toast.makeText(this, "You WIN!!!", Toast.LENGTH_SHORT).show()
                        winSound?.start()
                        show_n_disappear("  +${ulog * 2}!", show_text)
                        stanje = stanje + ulog * 2
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    } else if (mainPcSum >= 17 && mainPcSum <= 21 && mainPcSum > mainPlayerSum) {
                        //Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  -${ulog} $currency", show_text)
                        stanje = stanje
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    } else if (mainPcSum == mainPlayerSum && mainPcSum >= 17) {
                        winSound?.start()
                        //  Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +0 $currency!", show_text)
                        stanje = stanje + ulog
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        bet_text_under_chip.text = ""
                        if(repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }



                    } else if (mainPcSum < mainPlayerSum && mainPcSum >= 17) {
                        winSound?.start()
                        //Toast.makeText(this, "Ajdeeee!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +${ulog * 2} $currency!!", show_text)
                        stanje = stanje + ulog * 2
                        balance_text.text = "$stanje"
                        pHandPc = 0
                        pHandPlayer = 0
                        checkVuci = false
                        checkDijeli = true
                        ulogCheck = true
                        total_bet_chip.setImageResource(0)
                        ulog = 0
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= stanje) {
                            repeat_bet.setImageResource(repeatOn)
                            checkRepeat = true
                        }
                    }

                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, num_of_decks)
                    savePDeck()



                }

                saveData()

                dosta_button.setBackgroundResource(default_off)
                vuci_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)
                //dijeli_button.setBackgroundResource(default_on)
                checkDouble = false

            } // end of else
        }   // end of double_button

        split_button.setOnClickListener {
            if(checkSplit == false)
            {
                Log.d("SPLIT", "Split = $checkSplit")
            }
            else
            {
                splitEnabled = true
                playerSum = playerSum/2
                splitSum = playerSum
                pomSplitSum = splitSum
                pHandSplitPlayer = 1
                split_card_background.setImageResource(bj_cards[bjNiz[pDeck-1]]!!.pic)
                player_second_card.setImageResource(0)
                split_button.setBackgroundResource(default_off)
                stanje = stanje - ulog
                ulog = ulog*2
                bet_text_under_chip.text = "${currency}${ulog}"
                total_bet.text = "${currency}${ulog}"
            }


        }

        repeat_bet.setOnClickListener{
            if(repeatBet <= stanje && checkRepeat == true) {
                ulog = repeatBet
                total_bet.text = "BET: $currency$ulog"
                bet_text_under_chip.text = "$currency$ulog"
                dijeli_button.setBackgroundResource(default_on)
                repeat_bet.setImageResource(0)
                cancel_bet.setBackgroundResource(cancel_background)
                total_bet_chip.setImageResource(lastBet)

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
                reset_views(
                    split_card_background,
                    split_second_card,
                    split_third_card,
                    split_forth_card,
                    split_fifth_card,
                    split_sixth_card,
                    0
                )

                show_text.text = ""


                sum_reset(pc_sum, pc_sum2, player_sum, player_sum2)

            }
        }

        ///// DECKS ON/OFF FUNCTIONS //////
        // Deck fun, deck1 will be clicked and the other buttons will change their state_clicked to false

        ////// END OF DECKS ON/OFF FUNCTIONS //////

        ulog1.setOnClickListener {
            if(ulogCheck == true)
            {
                if((ulog + 10 <= maxBet)&&(ulog + 10 <= stanje)) {
                    chipsound1?.start()
                    cancel_bet.setBackgroundResource(cancel_background)
                    total_bet_chip.setImageResource(ulog_10)
                    ulog = ulog + 10
                    total_bet.text = "BET: $currency$ulog"
                    bet_text_under_chip.text = "$currency$ulog"
                    show_text.text = ""
                    background_text.text = ""
                    pc_sum.text = "0"
                    pc_sum2.text = ""
                    pc_sum2.setBackgroundResource(0)
                    player_sum.text = "0"
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    dijeli_button.setBackgroundResource(default_on)
                    repeat_bet.setImageResource(0)
                    lastBet = ulog_10


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
                    reset_views(
                        split_card_background,
                        split_second_card,
                        split_third_card,
                        split_forth_card,
                        split_fifth_card,
                        split_sixth_card,
                        0
                    )
                }
                else{
                    between_bet.setBackgroundResource(overbet)
                    handler.postDelayed({
                        between_bet.setBackgroundResource(0)
                    }, 100)
                }
            }
            else
            {
                Log.d(TAG, "Error with Ulog1Clicked")
            }

        }

        ulog2.setOnClickListener {
            if(ulogCheck == true)
            {
                if((ulog + 25 <= maxBet)&&(ulog + 25 <= stanje)) {
                    chipsound1?.start()
                    cancel_bet.setBackgroundResource(cancel_background)
                    total_bet_chip.setImageResource(ulog_25)
                    ulog = ulog + 25
                    total_bet.text = "BET: $currency$ulog"
                    bet_text_under_chip.text = "$currency$ulog"
                    show_text.text = ""
                    background_text.text = ""
                    pc_sum.text = "0"
                    pc_sum2.text = ""
                    pc_sum2.setBackgroundResource(0)
                    player_sum.text = "0"
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    dijeli_button.setBackgroundResource(default_on)
                    repeat_bet.setImageResource(0)
                    lastBet = ulog_25


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
                    reset_views(
                        split_card_background,
                        split_second_card,
                        split_third_card,
                        split_forth_card,
                        split_fifth_card,
                        split_sixth_card,
                        0
                    )
                }
                else{
                    between_bet.setBackgroundResource(overbet)
                    handler.postDelayed({
                        between_bet.setBackgroundResource(0)
                    }, 100)                }
            }
            else
            {
                Log.d(TAG, "Error with Ulog2Clicked")
            }

        }

        ulog3.setOnClickListener {
            if(ulogCheck == true)
            {
                if((ulog + 50 <= maxBet)&&(ulog + 50 <= stanje)) {
                    chipsound1?.start()
                    cancel_bet.setBackgroundResource(cancel_background)
                    total_bet_chip.setImageResource(ulog_50)
                    ulog = ulog + 50
                    total_bet.text = "BET: $currency$ulog"
                    bet_text_under_chip.text = "$currency$ulog"
                    show_text.text = ""
                    background_text.text = ""
                    pc_sum.text = "0"
                    pc_sum2.text = ""
                    pc_sum2.setBackgroundResource(0)
                    player_sum.text = "0"
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    dijeli_button.setBackgroundResource(default_on)
                    repeat_bet.setImageResource(0)
                    lastBet = ulog_50


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
                    reset_views(
                        split_card_background,
                        split_second_card,
                        split_third_card,
                        split_forth_card,
                        split_fifth_card,
                        split_sixth_card,
                        0
                    )
                }
                else{
                    between_bet.setBackgroundResource(overbet)
                    handler.postDelayed({
                        between_bet.setBackgroundResource(0)
                    }, 100)                }
            }
            else
            {
                Log.d(TAG, "Error with Ulog3Clicked")
            }
        }

        ulog4.setOnClickListener {
            if(ulogCheck == true)
            {
                if((ulog + 100 <= maxBet)&&(ulog + 100 <= stanje)) {
                    chipsound1?.start()
                    cancel_bet.setBackgroundResource(cancel_background)
                    total_bet_chip.setImageResource(ulog_100)
                    ulog = ulog + 100
                    total_bet.text = "BET: $currency$ulog"
                    bet_text_under_chip.text = "$currency$ulog"
                    show_text.text = ""
                    background_text.text = ""
                    pc_sum.text = "0"
                    pc_sum2.text = ""
                    pc_sum2.setBackgroundResource(0)
                    player_sum.text = "0"
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    dijeli_button.setBackgroundResource(default_on)
                    repeat_bet.setImageResource(0)
                    lastBet = ulog_100


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
                    reset_views(
                        split_card_background,
                        split_second_card,
                        split_third_card,
                        split_forth_card,
                        split_fifth_card,
                        split_sixth_card,
                        0
                    )

                }
                else{
                    between_bet.setBackgroundResource(overbet)
                    handler.postDelayed({
                        between_bet.setBackgroundResource(0)
                    }, 100)                }
            }
            else
            {
                Log.d(TAG, "Error with Ulog4Clicked")
            }
        }


    } // end of onCreate()


    fun sum_reset(pcsum: TextView, pcsum2: TextView, playersum: TextView, playersum2: TextView)
    {
        pcsum.text = "0"
        pcsum2.setBackgroundResource(0)
        pcsum2.text = ""

        playersum.text = "0"
        playersum2.setBackgroundResource(0)
        playersum2.text = ""



    }

    //function which resets ImageViews's resource to 0, and also first card is set as no_card pic(m)
    fun reset_views(a:ImageView,b:ImageView,c:ImageView,d:ImageView,e:ImageView,f:ImageView,m:Int)
    {
        a.setImageResource(m)
        b.setImageResource(0)
        c.setImageResource(0)
        d.setImageResource(0)
        e.setImageResource(0)
        f.setImageResource(0)
    }

    fun right_place_for_pic(pDeck: Int, pHand: Int , view1: ImageView, view2: ImageView, view3: ImageView, view4: ImageView, view5: ImageView, view6: ImageView, niz: IntArray, nizKarata: Array<blackjack_class?>)
    {
        if (pHand == 1) {
            view1.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
        else if (pHand == 2) {
            view2.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
        else if (pHand == 3) {
            view3.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
        else if (pHand == 4) {
            view4.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
        else if (pHand == 5) {
            view5.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
        else if (pHand == 6) {
            view6.setImageResource(nizKarata[niz[pDeck]]!!.pic)
        }
    }

    //  function sum_text -> arguments(first sum, second sum - first sum but changed if there is an ace in deck,
    //  TextView1 above cards which shows first sum , TextView2 which is one layer below TextView2(has to be
    //  in front of TextView1) and shows both sums if conditions are satisfed
    fun sum_text(sum: Int, pomSum: Int, view_sum1: TextView,view_sum2: TextView, showSuma: Int)
    {
        if((sum != pomSum)&&(pomSum <= 21))
        {
            view_sum2.setBackgroundResource(showSuma)
            view_sum2.text = "$sum/$pomSum"
        }
        else if((sum != pomSum)&&(sum > 21))
        {
            view_sum2.text = ""
            view_sum2.setBackgroundResource(0)
            view_sum1.text = "$sum"
        }
        else
        {
            view_sum2.text = ""
            view_sum2.setBackgroundResource(0)
            view_sum1.text = "$sum"
        }
    }

    // function sum_text but customized for dealer
    fun sum_text_dealer(sum: Int, pomSum: Int, view_sum1: TextView,view_sum2: TextView, showSuma: Int)
    {
        if((sum != pomSum)&&(pomSum <= 21)&&(pomSum > sum))
        {
            view_sum2.setBackgroundResource(0)
            view_sum2.text = ""
            view_sum1.text = "$pomSum"
        }
        else if((sum != pomSum)&&(sum > 21))
        {
            view_sum2.text = ""
            view_sum2.setBackgroundResource(0)
            view_sum1.text = "$sum"
        }
        else
        {
            view_sum2.text = ""
            view_sum2.setBackgroundResource(0)
            view_sum1.text = "$sum"
        }
    }


    // function required to calculate a final sum
    fun sum_check(sum: Int, pomSum: Int) : Int {
        var mainSum: Int
        if (pomSum <= 21 && pomSum > sum) {
            mainSum = pomSum
        } else {
            mainSum = sum
        }

        return mainSum
    }

    // functions below are changed with setOnClickListener{} but they r still here if something in future changes
    fun Ulog1Clicked(view: View)
    {
        if(ulogCheck == true)
        {
            if((ulog + 10 <= maxBet)&&(ulog + 10 <= stanje)) {
                cancel_bet.setBackgroundResource(cancel_background)
                total_bet_chip.setImageResource(ulog_10)
                ulog = ulog + 10
                total_bet.text = "BET: $currency$ulog"
                bet_text_under_chip.text = "$currency$ulog"
            }
            else{
                // Toast.makeText(this, "MAX BET: 500", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Log.d(TAG, "Error with Ulog1Clicked")
        }
    }
    fun Ulog2Clicked(view: View)
    {
        if(ulogCheck == true)
        {
            if((ulog + 25 <= maxBet)&&(ulog + 25 <= stanje)) {
                cancel_bet.setBackgroundResource(cancel_background)
                total_bet_chip.setImageResource(ulog_25)
                ulog = ulog + 25
                total_bet.text = "BET: $currency$ulog"
                bet_text_under_chip.text = "$currency$ulog"
            }
            else{
                Toast.makeText(this, "MAX BET: 500", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Log.d(TAG, "Error with Ulog2Clicked")
        }

    }
    fun Ulog3Clicked(view: View)
    {
        if(ulogCheck == true)
        {
            if((ulog + 50 <= maxBet)&&(ulog + 50 <= stanje)) {
                cancel_bet.setBackgroundResource(cancel_background)
                total_bet_chip.setImageResource(ulog_50)
                ulog = ulog + 50
                total_bet.text = "BET: $currency$ulog"
                bet_text_under_chip.text = "$currency$ulog"
            }
            else{
                Toast.makeText(this, "MAX BET: 500", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Log.d(TAG, "Error with Ulog3Clicked")
        }
    }
    fun Ulog4Clicked(view: View)
    {
        if(ulogCheck == true)
        {
            if((ulog + 100 <= maxBet)&&(ulog + 100 <= stanje)) {
                cancel_bet.setBackgroundResource(cancel_background)
                total_bet_chip.setImageResource(ulog_100)
                ulog = ulog + 100
                total_bet.text = "BET: $currency$ulog"
                bet_text_under_chip.text = "$currency$ulog"
            }
            else{
                Toast.makeText(this, "MAX BET: 500", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Log.d(TAG, "Error with Ulog4Clicked")
        }
    }


    // its pretty obvious what this fun does
    fun cancelCliked(view: View)
    {
        ulog = 0
        total_bet.text = "BET: $currency$ulog"
        bet_text_under_chip.text = "$currency$ulog"
        total_bet_chip.setImageResource(0)
        cancel_bet.setBackgroundResource(0)
        dijeli_button.setBackgroundResource(default_off)


    }

    // do I have to explain?
    fun show_n_disappear(message: String, show: TextView)
    {
        //  var color = Color.parseColor("#4E65DA")
        //  var color2 = Color.parseColor("#2C3FA1")
        show.text = message
        // show.setBackgroundColor(color2)
        handler.postDelayed({
            show.text = ""
            // show.setBackgroundColor(color)
        }, 2000)
    }

    // this one checks position in a deck
    // checks if pDeck get to last card
    // so if pDeck = 51, it sets it up to 0
    // also shuffles deck again
    fun position_check(deckPosition: Int, deck: IntArray, number_of_decks: Int): Int {
        var pos: Int = deckPosition
        if(deckPosition > (number_of_decks*52 - 1))
        {
            pos = 0
            counter = 0
            deck.shuffle()
        }
        savePDeck()
        return pos

    }

    //fun pause
    fun pause1(time: Int)
    {
        var vrijeme = 0
        while(vrijeme == 0)
        {

        }

    }

    // cardCounter
    // 2,3,4,5,6,7 -> +1
    // A,J,Q,K,10 ->  -1
    // 7,8,9      ->  +0
    fun cardCounter(card: blackjack_class?) :Int
    {
        var count: Int
        if(card!!.value >=2 && card!!.value < 7)
        {
            count = 1
        }
        else if(card!!.value >= 7 && card!!.value <= 9)
        {
            count = 0
        }
        else if(card!!.value == 10 || card!!.value == 1)
        {
            count = -1
        }
        else
        {
            count = 0
        }

        return count
    }

    // do nothing!
    fun doNothing(view: View)
    {
        Log.d(TAG, "Do nothing!")
    }

    // a one I do not understand at all
    fun saveData()
    {
        val pref_stanje: SharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = pref_stanje.edit()
        editor.putInt("STANJE", stanje)
        editor.commit()

        Log.d(TAG, "Stanje saved!")
    }


    // a second one I do not understand at all
    fun loadData()
    {
        var pref_stanje = getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
        stanje = pref_stanje.getInt("STANJE", stanje)


    }

    fun onLogin(view: View) {
        MyCustomDialog().show(supportFragmentManager, "MyCustomFragment")
        stanje = stanje + 2000
    }

   fun savePDeck()
    {
        var pref_pdeck = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref_pdeck.edit()
        editor.putInt("POINTER_ON_DECK", pDeck)
        editor.commit()

        Log.d(TAG, "pDeck saved!")
    }
    fun loadPDeck()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        pDeck = pref.getInt("POINTER_ON_DECK", 0)

    }

    fun loadNumDeck()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        num_of_decks = pref.getInt("NUM_OF_DECKS",0)

    }

    fun saveDeckString()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putString("DECK_STRING", deck)
        editor.commit()
    }

    fun loadDeckString()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        deck = pref.getString("DECK_STRING","").toString()
    }

    fun saveGameState()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putBoolean("GAME_STATE", game_state)
        editor.commit()
    }

    fun loadGameState()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        game_state = pref.getBoolean("GAME_STATE",false)
    }

    fun newGameState(num_of_decks: Int, bjNiz: IntArray) : String
    {
        pDeck = 0
        var deck: String = ""
        var i = 0
        var j = 0
        // for petljom se niz popuni elementima 0-51 s obzirom da niz tipa klase blackjack_class pocinje indexom 0
        // kada j dode do 52, resetira se ponovno na 0 i puni ostatak bjNiz niza
        for (i in 0..(num_of_decks * 52 - 1)) {
            bjNiz[i] = j
            j++
            if (j == 52) {
                j = 0
            }
        }

        bjNiz.shuffle()
        bjNiz.shuffle()

        for(i in 0..(num_of_decks*52-1))
        {
            deck = "${deck}n${bjNiz[i]}"
            if(i == (num_of_decks*52-1))
            {
                deck = "${deck}e"
            }
        }

        return deck
    }

    fun fillDeck(deck: String, bjNiz: IntArray)
    {
        var char: Char
        var i: Int = 0
        var j: Int = 0
        i = 0
        j = 0
        char = deck[0]
        while(char != 'e')
        {
            if(char == 'n')
            {
                if((deck[i+1] != 'n') && (deck[i+2] != 'n') && (deck[i+2] != 'e'))
                {
                    bjNiz[j] = deck[i+1].toInt()*10 + deck[i+2].toInt()
                    j++
                }
                if((deck[i+1] != 'n') && (deck[i+2] == 'n'))
                {
                    bjNiz[j] = deck[i+1].toInt()
                    j++
                }

            }

            i++
            char = deck[i]
        }
    }

    fun saveState(currentState: GameState, pcSum: Int, playerSum: Int, pomPcSum: Int, pomPlayerSum: Int)
    {
        currentState.pcSum = pcSum
        currentState.playerSum = playerSum
        currentState.pomPlayerSum = pomPlayerSum
        currentState.pomPcSum = pomPcSum
    }

}   // end of class BlackJack