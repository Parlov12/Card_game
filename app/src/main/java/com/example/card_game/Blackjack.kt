package com.example.card_game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_blackjack.*
import java.util.*
import kotlin.collections.ArrayList

class Blackjack : AppCompatActivity()
{
    // FIXING
    lateinit var file : SharedPreferences
    lateinit var data : DataManager
    lateinit var resources : Resources


    // END OF FIXING

    // start values of background and deck
    var background:Int = R.drawable.background2_white
    var deckPic: Int = R.drawable.deck1_backside


    // outside
    val timer = Timer()
    val handler = Handler()
    var repeatBet: Int = 0
    var currency: String = "$"
    val TAG: String = "TAG"
    val default_on: Int = R.drawable.deal_cust_on
    val default_off: Int = R.drawable.deal_cust
    val repeatOn: Int = android.R.drawable.stat_notify_sync_noanim
    var lastBet: Int = 0
    var pDeck: Int = 0
    var counter: Int = 0
    var pStartState: Int = 0
    var pEndState: Int = 0
    var dostaPom: Int = 0

    // NEW - pomak karte koji sluzi za odredivanje lijeve margine od prethodne karte, odnosno odreduje mjesto gdje ce otici karta prilikom animacije
    var pomak: Int = 0
    var pomakPc: Int = 0
    var pomakBet: Int = 0

    // check variables
    var showPDeck: Boolean = true
    var showCounter: Boolean = true

    // pointer variables
    var pHandPlayer: Int = 0
    var pHandPc: Int = 0
    var pHandSplitPlayer: Int = 0
    var pHandChip: Int = 0


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
    var bj1: Int = 0
    var bj2: Int = 0
    var bj3: Int = 0
    var bj4: Int = 0
    var deck: String = ""
    var char: Char = 'l'
    var betString: String = ""


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

        // FIXING
        var bets = arrayOf<Int>(0,0,0,0,0,0,0,0,0,0)
        file = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        data = DataManager(file)
        resources = Resources()

        // array of bets so we can set up setOnClickListeners easier
        var betsList : Array<Button> = arrayOf(ulog1, ulog2, ulog3, ulog4, ulog5, ulog6)
        // value of each bet so it can be assigned to bet value through "for" loop
        var betValue : Array<Int> = arrayOf(10, 25, 50, 100, 500, 1000)
        // END OF FIXING

        // postavljanje pozadine
        loadBackground()
        background_shared.setBackgroundResource(background)
        deck_background.setImageResource(deckPic)
        var lilDeck : ImageView = findViewById(R.id.lil_deck)
        lilDeck.setImageResource(deckPic)

        lilDeck.visibility = View.INVISIBLE
        cards_left.visibility = View.INVISIBLE
        counter_text.visibility = View.INVISIBLE


        // učitavanje odabrane metode te ostalih checkiranih kućica iz customize-a
        loadCheckBox()

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        loadPDeck()


        // sound variables
        var dealing_cards: MediaPlayer? = MediaPlayer.create(this, R.raw.dealing_cards_fix)
        var chipsound1: MediaPlayer? = MediaPlayer.create(this, R.raw.chip1_sound_fix)
        var winSound: MediaPlayer? = MediaPlayer.create(this, R.raw.allin_win)


        // image/color variables
        val show_suma: Int = R.drawable.custom_text
        val  overbet: Int = R.drawable.between_color

        // other variables
        val handler = Handler()
        var no_card: Int = R.drawable.no_card
        var i: Int = 0
        var j: Int = 0
        var pCounter: Int = 0
        var pom: Int = 0
        var brojac: Int = 0


        // button variables
        var stand: Button = findViewById(R.id.dosta_button)
        var pull: Button =  findViewById(R.id.vuci_button)



        // deklariranje niza te odredivanje njegove velicine
        // velicina iznosi broj dekova * 52 s obzirom da svaki dek ima 52 karte
        var bjNiz = IntArray(data.getNumberOfDecks() * 52)



        var bj_cards = arrayOfNulls<Card>(52)
        for(i in 0..51)
        {
            bj_cards[i] = Card(0,"default",0,0)
        }

        bj_cards[0]?.setCard(1,"tref",1, R.drawable.tref_1)
        bj_cards[1]?.setCard(2,"tref", 2,  R.drawable.tref_2)
        bj_cards[2]?.setCard(3,"tref", 3,  R.drawable.tref_3)
        bj_cards[3]?.setCard(4,"tref",4,  R.drawable.tref_4)
        bj_cards[4]?.setCard(5,"tref",5,  R.drawable.tref_5)
        bj_cards[5]?.setCard(6,"tref",6,  R.drawable.tref_6)
        bj_cards[6]?.setCard(7,"tref",7, R.drawable.tref_7)
        bj_cards[7]?.setCard(8,"tref",8,  R.drawable.tref_8)
        bj_cards[8]?.setCard(9,"tref", 9,   R.drawable.tref_9)
        bj_cards[9]?.setCard(10,"tref", 10,  R.drawable.tref_10)
        bj_cards[10]?.setCard(11, "tref", 10,  R.drawable.tref_11)
        bj_cards[11]?.setCard(12,"tref",10,  R.drawable.tref_12)
        bj_cards[12]?.setCard(13,"tref",10,  R.drawable.tref_13)
        bj_cards[13]?.setCard(1,"pik",1,  R.drawable.pik_1)
        bj_cards[14]?.setCard(2,"pik", 2,  R.drawable.pik_2)
        bj_cards[15]?.setCard(3,"pik", 3,  R.drawable.pik_3)
        bj_cards[16]?.setCard(4,"pik",4,  R.drawable.pik_4)
        bj_cards[17]?.setCard(5,"pik",5,  R.drawable.pik_5)
        bj_cards[18]?.setCard(6,"pik",6,  R.drawable.pik_6)
        bj_cards[19]?.setCard(7,"pik",7,  R.drawable.pik_7)
        bj_cards[20]?.setCard(8,"pik",8,  R.drawable.pik_8)
        bj_cards[21]?.setCard(9,"pik", 9,  R.drawable.pik_9)
        bj_cards[22]?.setCard(10,"pik", 10,  R.drawable.pik_10)
        bj_cards[23]?.setCard(11, "pik", 10,  R.drawable.pik_11)
        bj_cards[24]?.setCard(12,"pik",10,  R.drawable.pik_12)
        bj_cards[25]?.setCard(13,"pik",10,  R.drawable.pik_13)
        bj_cards[26]?.setCard(1,"karo",1,  R.drawable.karo_1)
        bj_cards[27]?.setCard(2,"karo", 2,  R.drawable.karo_2)
        bj_cards[28]?.setCard(3,"karo", 3,  R.drawable.karo_3)
        bj_cards[29]?.setCard(4,"karo",4,  R.drawable.karo_4)
        bj_cards[30]?.setCard(5,"karo",5,  R.drawable.karo_5)
        bj_cards[31]?.setCard(6,"karo",6,  R.drawable.karo_6)
        bj_cards[32]?.setCard(7,"karo",7,  R.drawable.karo_7)
        bj_cards[33]?.setCard(8,"karo",8,  R.drawable.karo_8)
        bj_cards[34]?.setCard(9,"karo", 9,  R.drawable.karo_9)
        bj_cards[35]?.setCard(10,"karo", 10,  R.drawable.karo_10)
        bj_cards[36]?.setCard(11, "karo", 10,  R.drawable.karo_11)
        bj_cards[37]?.setCard(12,"karo",10,  R.drawable.karo_12)
        bj_cards[38]?.setCard(13,"karo",10,  R.drawable.karo_13)
        bj_cards[39]?.setCard(1,"herc",1,  R.drawable.herc_1)
        bj_cards[40]?.setCard(2,"herc", 2,  R.drawable.herc_2)
        bj_cards[41]?.setCard(3,"herc", 3,  R.drawable.herc_3)
        bj_cards[42]?.setCard(4,"herc",4,  R.drawable.herc_4)
        bj_cards[43]?.setCard(5,"herc",5,  R.drawable.herc_5)
        bj_cards[44]?.setCard(6,"herc",6,  R.drawable.herc_6)
        bj_cards[45]?.setCard(7,"herc",7,  R.drawable.herc_7)
        bj_cards[46]?.setCard(8,"herc",8,  R.drawable.herc_8)
        bj_cards[47]?.setCard(9,"herc", 9,  R.drawable.herc_9)
        bj_cards[48]?.setCard(10,"herc", 10,  R.drawable.herc_10)
        bj_cards[49]?.setCard(11, "herc", 10,  R.drawable.herc_11)
        bj_cards[50]?.setCard(12,"herc",10,  R.drawable.herc_12)
        bj_cards[51]?.setCard(13,"herc",10,  R.drawable.herc_13)

        vuci_button.visibility = View.INVISIBLE
        dosta_button.visibility = View.INVISIBLE
        double_button.visibility = View.INVISIBLE


        Log.d("TAG", "ALL CARDS")
        for(i in 0..51)
        {
            println("${bj_cards[i]!!.type}-${bj_cards[i]!!.number}\n")
        }

        // liste ImageView karata
        //LISTA PC KARATA
        var listPcCards = mutableListOf<ImageView>(
                pc_card_background,
                pc_second_card,
                pc_third_card,
                pc_forth_card,
                pc_fifth_card,
                pc_sixth_card,
                pc_seventh_card
        )
        //LISTA PLAYER KARATA
        var listPlayerCards = mutableListOf<ImageView>(
            player_card_background,
            player_second_card,
            player_third_card,
            player_forth_card,
            player_fifth_card,
            player_sixth_card,
            player_seventh_card
        )


        // LISTA CHIPOVA
        var listChips = mutableListOf<ImageView>(
            bet1,
            bet2,
            bet3,
            bet4,
            bet5,
            bet6,
            bet7,
            bet8,
            bet9,
            bet10
        )
        

        var storeChips = ArrayList<Int>()

        var betManager : Chipovi = Chipovi(file)

        // setting up screen on the very start
        dijeli_button.setBackgroundResource(default_on)
        cancel_bet.setBackgroundResource(0)
        repeat_bet.setImageResource(0)
        balance_text.text = "${data.getBalance()}"
        showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showPDeck)
        showCounter(counter_text, counter, showCounter)

        if(!data.getGameState()) {
            data.setGameState(true)
            newGameState(bjNiz)
            data.setDeckString("")
            total_bet.text = "BET: $currency${data.getCurrentBet()}"
            Log.d(TAG,"game_state = false \n $deck")
            background_text.text = "BLACKJACK"
            handler.postDelayed({
                background_text.setText("")
            }, 1000)
            dijeli_button.visibility = View.VISIBLE
            vuci_button.visibility = View.INVISIBLE
            dosta_button.visibility = View.INVISIBLE
            double_button.visibility = View.INVISIBLE
        }
        else if(data.getGameState())
        {
            dijeli_button.setBackgroundResource(default_off)
            showCounter(counter_text, counter, showCounter)
            total_bet.text = "BET: $currency${data.getCurrentBet()}"
            //loadDeckString()
            loadCounter()
            showCounter(counter_text, counter, showCounter)

            // FILL DECK //
            i = 0
            j = 0
            char = deck[0]
            while(char != 'e')
            {
                if(char == 'n')
                {
                    if((deck[i+1] != 'n') && (deck[i+2] != 'n') && (deck[i+2] != 'e'))
                    {
                        char = deck[i+1]
                        bjNiz[j] = Character.getNumericValue(char)*10
                        char = deck[i+2]
                        bjNiz[j] = bjNiz[j] + Character.getNumericValue(char)
                        Log.d(TAG, "bjNiz[$j] = ${bjNiz[j]}")
                        j++

                    }
                    if((deck[i+1] != 'n') && (deck[i+2] == 'n'))
                    {
                        char = deck[i+1]
                        bjNiz[j] = Character.getNumericValue(char)
                        Log.d(TAG, "bjNiz[$j] = ${bjNiz[j]}")
                        j++
                    }

                }
                i++
                char = deck[i]
            }
            Log.d("TAG", "ALL CARDS LOADED")
            for(i in 0..(data.getNumberOfDecks()*51-1))
            {
                println("${bj_cards[bjNiz[i]]!!.type}-${bj_cards[bjNiz[i]]!!.number}\n")
            }
            // FILL DECK END //

            if(data.getCheckState()) {
                dijeli_button.visibility = View.INVISIBLE
                vuci_button.visibility = View.VISIBLE
                dosta_button.visibility = View.VISIBLE
                double_button.visibility = View.VISIBLE

                handler.postDelayed({

                    dijeli_button.setBackgroundResource(default_off)
                    vuci_button.setBackgroundResource(default_on)
                    dosta_button.setBackgroundResource(default_on)
                    if (data.getCheckDouble()) {
                        double_button.setBackgroundResource(default_on)
                    }
                    data.setUlogCheck(false)
                    loadCurrentState()
                    pDeck = pStartState
                    listPlayerCards[0].setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    slideCard(listPlayerCards[0], player_card_background2, pomak)
                    pDeck++
                    pomak = pomak + 35
                    listPlayerCards[1].setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    slideCard(listPlayerCards[1], player_card_background2, pomak)
                    pDeck++
                    pomak = pomak + 35
                    listPcCards[0].setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                    slideCard(listPcCards[0], pc_card_background2, pomakPc)
                    listPcCards[1].setImageResource(deckPic)
                    pDeck++
                    pomakPc = pomakPc + 35

                    var k = pHandPlayer
                    var m = 2
                    handler.postDelayed({
                        while (k - 1 > 1) {
                            listPlayerCards[m].setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                            slideCard(listPlayerCards[m], player_card_background2, pomak)
                            m++
                            k--
                            pDeck++
                            pomak = pomak + 35
                        }
                        pDeck--
                    }, 100)



                    sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)
                    sum_text_dealer(pcSum, pomPcSum, pc_sum, pc_sum2, show_suma)
                    data.setCheckDijeli(false)
                    data.setCheckDosta(true)
                    data.setCheckVuci(true)
                    data.setCheckSplit(false)
                    bet_text_under_chip.text = "$${data.getCurrentBet()}"
                    showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)
                    total_bet_chip.setImageResource(resources.getBetImages(1))

                    Log.d(TAG, "game_state = true $deck")
                },2000)

            } // end of checkState
            else{
                dijeli_button.visibility = View.VISIBLE
                vuci_button.visibility = View.INVISIBLE
                dosta_button.visibility = View.INVISIBLE
                double_button.visibility = View.INVISIBLE
            }
        } // end of check current_state




        Log.d("TAG", "ALL CARDS")
        for(i in 0..(data.getNumberOfDecks()*51-1))
        {
            println("${bj_cards[bjNiz[i]]!!.type}-${bj_cards[bjNiz[i]]!!.number}\n")
        }



        dijeli_button.setOnClickListener {
            if (!data.getCheckDijeli()) {
                show_text.text = ""//IN GAME..
                handler.postDelayed({
                    show_text.text = ""
                }, 2000)
            }
            else if (data.getCurrentBet() == 0) {
                Toast.makeText(this, "Place your bet!", Toast.LENGTH_SHORT)
                    .show()
            }
            else if(data.getBalance() == 0.0f || data.getBalance() < data.getCurrentBet().toFloat())
            {
                Toast.makeText(this, "Insuficient balance!", Toast.LENGTH_SHORT).show()
            }
            else {

                Log.d(
                    TAG,
                    "\nBefore - DIJELI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )
                
                dijeli_button.visibility = View.INVISIBLE
                vuci_button.visibility = View.VISIBLE
                dosta_button.visibility = View.VISIBLE
                double_button.visibility = View.VISIBLE
                

                pStartState = pDeck


                repeatBet = data.getCurrentBet()
                data.setCheckRepeat(false)
                data.setCheckDijeli(false)
                data.setUlogCheck(false)
                data.setCheckDosta(true)
                data.setCheckVuci(true)
                data.setCheckCancel(false)
                pHandPlayer = 0
                pHandPc = 0
                pomak = 0
                pomakPc = 0
                pomakBet = 0
                pHandChip = 0

                data.setCheckState(true)

                data.setBalance(data.getCurrentBet().toFloat()*(-1))
                balance_text.text = "${data.getBalance()}"

                saveCurrentState()

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


                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
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
                showCounter(counter_text, counter, showCounter)
                saveCounter()
                // end of counter part

                if(bj_cards[bjNiz[pDeck]]!!.number == 1)
                {
                    pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                // setting up player's first card instead of player's background card(no_card)
                //player_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                right_place_for_pic(
                    pDeck,
                    pHandPlayer,
                    player_card_background2,
                    bjNiz,
                    bj_cards,
                    listPlayerCards,
                    pomak,
                    stand,
                    pull
                )
                pomak = pomak + 35
                pHandPlayer = pHandPlayer + 1

                // setting up sum text above cards - player
                sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                Log.d(
                    TAG,
                    "\nBefore - DIJELI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )

                // fix - player must have two cards at start
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                playerSum = playerSum + bj_cards[bjNiz[pDeck]]!!.value

                Log.d(
                    "Nakon 357" ,
                    "\nBefore - DIJELI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )
                //setting up pomPlayer sum
                pomPlayerSum = pomPlayerSum + bj_cards[bjNiz[pDeck]]!!.value
                if(bj_cards[bjNiz[pDeck]]!!.number == 1)
                {
                    pomPlayerSum = pomPlayerSum + 11 - 1 // replacing A values: A-1 -> A-11
                }
                // setting up player's second card
                //player_second_card.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                right_place_for_pic(
                    pDeck,
                    pHandPlayer,
                    player_card_background2,
                    bjNiz,
                    bj_cards,
                    listPlayerCards,
                    pomak,
                    stand,
                    pull
                )
                pomak = pomak + 35
                pHandPlayer = pHandPlayer + 1

                // setting up sum text above cards - player
                sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                bj2 = bj_cards[bjNiz[pDeck]]!!.value
                bj4 = bj_cards[bjNiz[pDeck]]!!.number


                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                showCounter(counter_text, counter, showCounter)
                saveCounter()
                // end of counter part

                //increasing "pointer" to deck +1 and that will be pc's first card
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())

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
                //pc_card_background.setImageResource(bj_cards[bjNiz[pDeck]]!!.pic)
                right_place_for_pic(
                    pDeck,
                    pHandPc,
                    pc_card_background2,
                    bjNiz,
                    bj_cards,
                    listPcCards,
                    pomakPc,
                    stand,
                    pull
                )
                pomakPc = pomakPc + 35
                pHandPc = pHandPc + 1

                // postavljanje
                pc_second_card.setImageResource(deckPic)
                slideCard(pc_second_card,pc_card_background2,pomakPc)


                // hidden pc card
                /*
                pDeck  = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, num_of_decks)
                dostaPom = pDeck
                */



                // counter part
                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                showCounter(counter_text, counter, showCounter)
                saveCounter()
                // end of counter part

                // setting up sum text above cards - pc
                sum_text_dealer(pcSum, pomPcSum, pc_sum, pc_sum2, show_suma)


                // "pointer" to player's card, setting it to 0 and also increasing it line after to 2, because we already have two cards
                pHandPlayer = 0
                pHandPlayer = pHandPlayer + 2
                //

                showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)

                // if player gets 21 in first two cards, he/she instantly wins
                if((bj1== 1 && bj2 == 10)||(bj1 == 10 && bj2 == 1))
                {
                    winSound?.start()
                    mainPlayerSum = sum_check(playerSum, pomPlayerSum)
                    Toast.makeText(this, "Blackjack!", Toast.LENGTH_SHORT).show()
                    show_n_disappear("  +${(data.getCurrentBet()*2.5).toInt()} $currency!!", show_text)
                    data.setBalance((data.getCurrentBet()).toFloat() * 2.5f)
                    balance_text.text = "${data.getBalance()}"
                    pHandPc = 0
                    pHandPlayer = 0
                    data.setCheckVuci(false)
                    data.setCheckDijeli(true)
                    data.setCheckDosta(false)
                    data.setCheckDouble(false)
                    data.setCheckDouble(false)
                    pomak = 0
                    pHandPlayer = 0
                    player_sum2.text = ""
                    player_sum2.setBackgroundResource(0)
                    player_sum.text = "$mainPlayerSum"
                    vuci_button.setBackgroundResource(default_off)
                    dosta_button.setBackgroundResource(default_off)
                    dijeli_button.setBackgroundResource(default_on)
                    double_button.setBackgroundResource(default_off)
                    //split_button.setBackgroundResource(default_off)
                    data.setUlogCheck(true)
                    data.setCurrentBet(0)
                    total_bet.text = "BET: 0 $currency"
                    bet_text_under_chip.text = ""
                    total_bet_chip.setImageResource(0)
                    if(repeatBet <= data.getBalance()) {
                        repeat_bet.setImageResource(repeatOn)
                        data.setCheckRepeat(true)
                    }
                    pDeck = pDeck + 1


                    pomak = pomak + 35
                    pHandPlayer = pHandPlayer + 1
                    pHandChip = 0
                    pomakBet = 0

                    dijeli_button.visibility = View.VISIBLE
                    vuci_button.visibility = View.INVISIBLE
                    dosta_button.visibility = View.INVISIBLE
                    double_button.visibility = View.INVISIBLE
                }

                // check checkDouble
                if(((playerSum == 9)||(playerSum == 10)||(playerSum==11))&&((pomPlayerSum == 9)||(pomPlayerSum == 10)||(pomPlayerSum==11))&&(data.getCurrentBet() <= data.getBalance()))
                {
                    data.setCheckDouble(true)
                    double_button.setBackgroundResource(default_on)
                    Log.d(TAG, "Sucessfull check")
                }

                // check checkSplit
                if((bj3 == bj4)&&(data.getBalance() >= data.getCurrentBet()))
                {
                    //split_button.setBackgroundResource(default_on)
                    data.setCheckDouble(true)
                }

                Log.d(
                    TAG,
                    "\nAfter - DIJELI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                )


                saveCurrentState()

                // card sound
                dealing_cards?.start()


            } // kraj svih provjera tj kraj else-a
        } // end of dijeli_button

        vuci_button.setOnClickListener {
            if(!data.getSplitEnabled()) {
                if (!data.getCheckVuci()) {
                    println("Error!")
                } else {
                    Log.d(
                        TAG,
                        "\nBefore - VUCI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                    )
                    dealing_cards?.start()
                    data.setCheckDouble(false)
                    data.setCheckDouble(false)
                    //splitEnabled = false
                    //split_button.setBackgroundResource(default_off)
                    double_button.setBackgroundResource(default_off)
                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                    pEndState = pDeck
                    saveCurrentState()
                    savePDeck()
                    showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)


                    // "pointer" to player's hand, used to check where card must be playes
                    if (!data.getSplitEnabled()) {
                        right_place_for_pic(
                            pDeck,
                            pHandPlayer,
                            player_card_background2,
                            bjNiz,
                            bj_cards,
                            listPlayerCards,
                            pomak,
                            stand,
                            pull
                        )
                        pomak = pomak + 35
                        pHandPlayer = pHandPlayer + 1


                    } else if (data.getSplitEnabled()) {

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
                        data.setBalance(0.0f)
                        data.setCheckDosta(false)
                        data.setCheckVuci(false)
                        show_n_disappear("  -${data.getCurrentBet()} $currency", show_text)
                        vuci_button.setBackgroundResource(default_off)
                        dosta_button.setBackgroundResource(default_off)
                        double_button.setBackgroundResource(default_off)
                        //dijeli_button.setBackgroundResource(default_on)

                        data.setCheckDijeli(true)
                        data.setUlogCheck(true)
                        data.setSplitEnabled(false)
                        data.setCheckRepeat(false)
                        total_bet_chip.setImageResource(0)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                        savePDeck()
                        pomak = 0

                        if (repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        unSlideCards(total_bet_chip, listChips)
                        pHandChip = 0
                        pomakBet = 0

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE



                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }

                    // counter part
                    counter += cardCounter(bj_cards[bjNiz[pDeck]])
                    showCounter(counter_text, counter, showCounter)
                    saveCounter()
                    // end of counter part

                    // regulation of what text shows between playerSum and pomPlayerSum
                    sum_text(playerSum, pomPlayerSum, player_sum, player_sum2, show_suma)

                    Log.d(
                        TAG,
                        "\nAfter - VUCI\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
                    )
                    saveCurrentState()
                } // end of provjera checkVuci
            }
            else if(data.getSplitEnabled())
            {
                dealing_cards?.start()
                data.setCheckDouble(false)
                data.setCheckDouble(false)
                //splitEnabled = false
                //split_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                savePDeck()
                showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)



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
                    show_n_disappear("  -${data.getCurrentBet()} $currency", show_text)
                    double_button.setBackgroundResource(default_off)
                    //dijeli_button.setBackgroundResource(default_on)

                    bet_text_under_chip.text = ""
                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                    savePDeck()

                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }

                // counter part
                counter += cardCounter(bj_cards[bjNiz[pDeck]])
                showCounter(counter_text, counter, showCounter)
                saveCounter()
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
                "\nBefore - DOSTA\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
            )

            var totalWin: Int = 0

            // dealer section
            if (!data.getCheckDosta()) {

            } else{
                pc_second_card.setImageResource(0)

                // privremeno
                data.setCheckDouble(false)
                data.setSplitEnabled(false)
                //split_button.setBackgroundResource(default_off)
                // end of privremeno

                data.setCheckRepeat(false)

                data.setCheckDosta(false)
                pHandPc = 1




                mainPlayerSum = sum_check(playerSum, pomPlayerSum)
                mainPcSum = sum_check(pcSum, pomPcSum)


                while (mainPcSum < 17) {
                    /*
                    if(dostaPom != 0)
                    {
                        pom = pDeck
                        pDeck = dostaPom
                        dostaPom = 0
                    }
                    else
                    {
                        pDeck = pom
                        pDeck = pDeck + 1
                    } */

                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck,bjNiz, data.getNumberOfDecks())
                    savePDeck()
                    showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)


                    dealing_cards?.start()

                    // counter part
                    counter += cardCounter(bj_cards[bjNiz[pDeck]])
                    showCounter(counter_text, counter, showCounter)
                    saveCounter()
                    // end of counter part

                    right_place_for_pic(
                        pDeck,
                        pHandPc,
                        pc_card_background2,
                        bjNiz,
                        bj_cards,
                        listPcCards,
                        pomakPc,
                        stand,
                        pull
                    )
                    pomakPc = pomakPc + 35
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
                        show_n_disappear("  +${data.getCurrentBet() * 2} $currency!", show_text)
                        data.setBalance((data.getCurrentBet() * 2).toFloat())
                        balance_text.text = "${data.getBalance()}"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDijeli(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck,bjNiz, data.getNumberOfDecks())

                        if (repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum >= 17 && mainPcSum <= 21 && mainPcSum > mainPlayerSum) {
                        show_n_disappear("  -${data.getCurrentBet()} $currency", show_text)
                        data.setJustBalance(data.getBalance())
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDijeli(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck,bjNiz, data.getNumberOfDecks())
                        if (repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum == mainPlayerSum && mainPcSum >= 17) {
                        //  Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +0 $currency!", show_text)
                        data.setBalance(data.getCurrentBet().toFloat())
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck,bjNiz, data.getNumberOfDecks())

                        if (repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum < mainPlayerSum && mainPcSum >= 17) {
                        //Toast.makeText(this, "Ajdeeee!", Toast.LENGTH_SHORT).show()
                        winSound?.start()
                        show_n_disappear("  +${data.getCurrentBet() * 2} $currency!!", show_text)
                        data.setBalance(data.getCurrentBet().toFloat() * 2.0f)
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        //dijeli_button.setBackgroundResource(default_on)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        pDeck = pDeck + 1
                        pDeck = position_check(pDeck,bjNiz, data.getNumberOfDecks())

                        if (repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    }
                } // end of while(pcSum <17)



                dosta_button.setBackgroundResource(default_off)
                vuci_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)

                unSlideCards(total_bet_chip, listChips)
                pHandChip = 0
                pomakBet = 0



            }// end of provjera check-a

            Log.d(
                TAG,
                "\nAfter - DOSTA\ncheckVuci = ${data.getCheckVuci()}\ncheckDosta = ${data.getCheckDosta()}\npDeck = $pDeck\nplayerSum = $playerSum\npomPlayerSum = $pomPlayerSum\npcSum = $pcSum\npomPcSum = $pomPcSum"
            )



        } // end of dosta_button

        double_button.setOnClickListener {

            if (!data.getCheckDouble()) {
                Log.d(TAG, "Double is not available")
            }
            else {

                data.setCheckRepeat(false)

                data.setBalance(data.getCurrentBet().toFloat()*(-1.0f))
                data.setCurrentBet(data.getCurrentBet()*2)
                balance_text.text = "$data.getBalance()"
                // part of code from vuci_button

                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                showPCounter(cards_left, pDeck, data.getNumberOfDecks(), showCounter)
                savePDeck()


                right_place_for_pic(
                    pDeck,
                    pHandPlayer,
                    player_card_background2,
                    bjNiz,
                    bj_cards,
                    listPlayerCards,
                    pomak,
                    stand,
                    pull
                )
                pomak = pomak + 35
                pHandPlayer = pHandPlayer + 1

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
                data.setCheckDosta(false)
                pHandPc = 1
                pDeck = pDeck + 1
                pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                savePDeck()

                mainPcSum = sum_check(pcSum, pomPcSum)
                mainPlayerSum = sum_check(playerSum, pomPlayerSum)

                while (mainPcSum < 17) {

                    Log.d(TAG, "Ulaz u petlju!")

                    right_place_for_pic(
                        pDeck,
                        pHandPc,
                        pc_card_background2,
                        bjNiz,
                        bj_cards,
                        listPcCards,
                        pomakPc,
                        stand,
                        pull
                    )
                    pomakPc = pomakPc + 35
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
                        show_n_disappear("  +${data.getCurrentBet() * 2}!", show_text)
                        data.setBalance(data.getCurrentBet().toFloat() * 2.0f)
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum >= 17 && mainPcSum <= 21 && mainPcSum > mainPlayerSum) {
                        //Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  -${data.getCurrentBet()} $currency", show_text)
                        data.setJustBalance(data.getBalance())
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum == mainPlayerSum && mainPcSum >= 17) {
                        winSound?.start()
                        //  Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +0 $currency!", show_text)
                        data.setBalance(data.getCurrentBet().toFloat())
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        bet_text_under_chip.text = ""
                        if(repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    } else if (mainPcSum < mainPlayerSum && mainPcSum >= 17) {
                        winSound?.start()
                        //Toast.makeText(this, "Ajdeeee!", Toast.LENGTH_SHORT).show()
                        show_n_disappear("  +${data.getCurrentBet() * 2} $currency!!", show_text)
                        data.setBalance(data.getCurrentBet().toFloat() * 2.0f)
                        balance_text.text = "$data.getBalance()"
                        pHandPc = 0
                        pHandPlayer = 0
                        data.setCheckVuci(false)
                        data.setCheckDosta(true)
                        data.setUlogCheck(true)
                        total_bet_chip.setImageResource(0)
                        data.setCurrentBet(0)
                        total_bet.text = "BET: 0 $currency"
                        bet_text_under_chip.text = ""
                        if(repeatBet <= data.getBalance()) {
                            repeat_bet.setImageResource(repeatOn)
                            data.setCheckRepeat(true)
                        }

                        dijeli_button.visibility = View.VISIBLE
                        vuci_button.visibility = View.INVISIBLE
                        dosta_button.visibility = View.INVISIBLE
                        double_button.visibility = View.INVISIBLE

                    }

                    pDeck = pDeck + 1
                    pDeck = position_check(pDeck, bjNiz, data.getNumberOfDecks())
                    savePDeck()



                }



                dosta_button.setBackgroundResource(default_off)
                vuci_button.setBackgroundResource(default_off)
                double_button.setBackgroundResource(default_off)
                //dijeli_button.setBackgroundResource(default_on)
                data.setCheckDouble(false)

            } // end of else
        }   // end of double_button

        /*split_button.setOnClickListener {
            if(checkSplit == false)
            {
                Log.d("SPLIT", "Split = $checkSplit")
            }
            else

                splitEnabled = true
                playerSum = playerSum/2
                splitSum = playerSum
                pomSplitSum = splitSum
                pHandSplitPlayer = 1
                split_card_background.setImageResource(bj_cards[bjNiz[pDeck-1]]!!.pic)
                player_second_card.setImageResource(0)
                //split_button.setBackgroundResource(default_off)
                stanje = stanje - ulog
                ulog = ulog*2
                bet_text_under_chip.text = "${currency}${ulog}"
                //total_bet.text = "${currency}${ulog}"
            }


        }*/

        repeat_bet.setOnClickListener{
            if(repeatBet <= data.getBalance() && data.getCheckRepeat()) {
                data.setCheckCancel(true)
                data.setCurrentBet(repeatBet)
                total_bet.text = "BET: $currency$data.getCurrentBet()"
                bet_text_under_chip.text = "$$data.getCurrentBet()"
                dijeli_button.setBackgroundResource(default_on)
                repeat_bet.setImageResource(0)
                cancel_bet.setBackgroundResource(resources.getCancelBet())
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

                unSlideCards(deck_background, listPlayerCards)
                unSlideCards(deck_background, listPcCards)

                show_text.text = ""


                sum_reset(pc_sum, pc_sum2, player_sum, player_sum2)

            }
        }

        ///// DECKS ON/OFF FUNCTIONS //////
        // Deck fun, deck1 will be clicked and the other buttons will change their state_clicked to false

        ////// END OF DECKS ON/OFF FUNCTIONS //////

        for(i in 0..5){
            betsList[i].setOnClickListener {
                if(data.getUlogCheck())
                {
                    if((data.getCurrentBet() + betValue[i] <= data.getMaxBet())&&(data.getCurrentBet() + betValue[i] <= data.getBalance())) {
                        data.setCheckCancel(true)
                        chipsound1?.start()
                        cancel_bet.setBackgroundResource(resources.getCancelBet())
                        //NEW - SLIDING CHIPS
                        // increasing chipCounter
                        // inside - if chipCounter >= 10 --> chipCounter = 0
                        data.plusChipCounter()
                        listChips[data.getChipCounter()].setImageResource(resources.getBetImages(i))

                        //END - SLIDING CHIPS
                        data.setCurrentBet(data.getCurrentBet()+betValue[i])
                        total_bet.text = "BET: $currency${data.getCurrentBet()}"
                        bet_text_under_chip.text = "$currency${data.getCurrentBet()}"
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
                        data.setLastBet(resources.getBetImages(i))

                        unSlideCards(deck_background, listPlayerCards)
                        unSlideCards(deck_background, listPcCards)

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



                    }
                    else{
                        between_bet.setBackgroundResource(overbet)
                        handler.postDelayed({
                            between_bet.setBackgroundResource(0)
                        }, 100)
                    }
                    manageString('1')
                }
                else
                {
                    Log.d(TAG, "Error with Ulog${i+1}Clicked")
                }
            }
        }

        cancel_bet.setOnClickListener{
            if(data.getCheckCancel()) {
                data.setCheckCancel(false)
                data.setCurrentBet(0)
                total_bet.text = "BET: $currency${data.getCurrentBet()}"
                bet_text_under_chip.text = ""
                //total_bet_chip.setImageResource(0)
                cancel_bet.setBackgroundResource(0)
                dijeli_button.setBackgroundResource(default_off)

                unSlideCards(total_bet_chip, listChips)
                for(i in 0..listChips.size-1)
                {
                    listChips[i].setImageResource(0)
                }
                pHandChip = 0
                pomakBet = 0
            }
        }

        watchVideo.setOnClickListener {
            data.setBalance(5000.0f)
            dialogText.visibility  = View.INVISIBLE
            watchVideo.visibility = View.INVISIBLE
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

    fun right_place_for_pic(pDeck: Int, pHand: Int , m2: ImageView, niz: IntArray, nizKarata: Array<Card?>, listaKarata: List<ImageView>, move: Int, a: Button, b: Button) {
        var m1: ImageView = listaKarata[pHand]
        a.isClickable = false
        b.isClickable = false
        var handler = Handler()
        m1.setImageResource(nizKarata[niz[pDeck]]!!.pic)
            slideCard(m1, m2, move)
        handler.postDelayed({
        a.isClickable = true
            b.isClickable = true
        }, 1000)
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
    fun cardCounter(card: Card?) :Int
    {
        var count: Int = 0
        if(data.getMethod() == 1) {
            if (card!!.value >= 2 && card!!.value < 7) {
                count = 1
            } else if (card!!.value >= 7 && card!!.value <= 9) {
                count = 0
            } else if (card!!.value == 10 || card!!.value == 1) {
                count = -1
            } else {
                count = 0
            }
        }
        else if(data.getMethod() == 2){
            if (card!!.value >= 2 && card!!.value <= 7) {
                count = 1
            } else if (card!!.value > 7 && card!!.value <= 9) {
                count = 0
            } else if (card!!.value == 10 || card!!.value == 1) {
                count = -1
            } else {
                count = 0
            }
        }
        else if(data.getMethod() == 3){
            if (card!!.value > 2 && card!!.value < 7) {
                count = 1
            } else if (card!!.value >= 7 && card!!.value <= 9) {
                count = 0
            } else if (card!!.value == 10 || card!!.value == 1) {
                count = -1
            }
            else if(card!!.value == 2)
            {
                count = 0
            }
            else {
                count = 0
            }
        }
        else if(data.getMethod() == 4){
            if ((card!!.value >= 2 && card!!.value <= 3)||(card!!.value >= 6 && card!!.value <= 7)) {
                count = 1
            } else if ((card!!.value >= 8 && card!!.value <= 9)||(card!!.value == 1)) {
                count = 0
            } else if (card!!.value == 10) {
                count = -2
            }
            else if(card!!.value >= 4 && card!!.value <=5)
            {
                count = 2
            }
            else {
                count = 0
            }
        }
        else if(data.getMethod() == 5){
            if((card!!.value >= 2 && card!!.value <= 3)||(card!!.value == 7))
            {
                count = 1
            }
            else if(card!!.value >= 4 && card!!.value <= 6)
            {
                count = 2
            }
            else if(card!!.value >= 8 && card!!.value <= 9)
            {
                count = 0
            }
            else if(card!!.value == 10)
            {
                count = -2
            }
            else if(card!!.value == 1)
            {
                count = -1
            }
            else{
                count = 0
            }
        }


        return count
    }

    // do nothing!
    fun doNothing(view: View) {
        Log.d(TAG, "Do nothing!")
    }

    fun onLogin(view: View) {
        //MyCustomDialog().show(supportFragmentManager, "MyCustomFragment")
        dialogText.visibility = View.VISIBLE
        watchVideo.visibility = View.VISIBLE
        data.setBalance(5000.0f)
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

    fun saveDeckString()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putString("DECK_STRING", deck)
        editor.commit()
        Log.d(TAG, "DeckString saved!")
    }

    fun loadDeckString()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        deck = pref.getString("DECK_STRING","").toString()
    }


    fun newGameState(bjNiz: IntArray)
    {
        pDeck = 0
        savePDeck()
        var i = 0
        var j = 0
        // for petljom se niz popuni elementima 0-51 s obzirom da niz tipa klase Card pocinje indexom 0
        // kada j dode do 52, resetira se ponovno na 0 i puni ostatak bjNiz niza
        for (i in 0..(data.getNumberOfDecks() * 52 - 1)) {
            bjNiz[i] = j
            j++
            if (j == 52) {
                j = 0
            }
        }

        bjNiz.shuffle()
        bjNiz.shuffle()

        for(i in 0..(data.getNumberOfDecks()*52-1))
        {
            deck = "${deck}n${bjNiz[i]}"
            if(i == (data.getNumberOfDecks()*52-1))
            {
                deck = "${deck}e"
            }
        }
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

    fun saveCurrentState()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putInt("P_START_STATE", pStartState)
        editor.putInt("P_END_STATE", pEndState)
        editor.putInt("PLAYER_SUM", playerSum)
        editor.putInt("POM_PLAYER_SUM", pomPlayerSum)
        editor.putInt("PC_SUM", pcSum)
        editor.putInt("POM_PC_SUM", pomPcSum)
        editor.putInt("CURRENT_BET", data.getCurrentBet())
        editor.putInt("P_HAND_PLAYER", pHandPlayer)
        editor.putInt("REPEAT", repeatBet)
        editor.putInt("LAST_BET", lastBet)
        editor.putInt("HAND_PLAYER", pHandPlayer)
        editor.putInt("HAND_PC", pHandPc)
        editor.commit()
    }
    fun loadCurrentState()
    {
        var pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        pStartState = pref.getInt("P_START_STATE", 0)
        pEndState = pref.getInt("P_END_STATE", 0)
        playerSum = pref.getInt("PLAYER_SUM", 0)
        pomPlayerSum = pref.getInt("POM_PLAYER_SUM", 0)
        pcSum = pref.getInt("PC_SUM", 0)
        pomPcSum = pref.getInt("POM_PC_SUM", 0)
        pHandPlayer = pref.getInt("P_HAND_PLAYER",0)
        repeatBet = pref.getInt("REPEAT", 0)
        lastBet = pref.getInt("LAST_BET", 0)
        pHandPlayer = pref.getInt("HAND_PLAYER", 0)
        pHandPc = pref.getInt("HAND_PC", 0)
    }

    fun saveCounter()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("COUNTER", counter)
        editor.commit()

    }
    fun loadCounter()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        counter = pref.getInt("COUNTER", counter)

    }

    fun loadCheckBox()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        showCounter = pref.getBoolean("SHOW_COUNTER", true)
        showPDeck = pref.getBoolean("SHOW_P_DECK", true)

    }

    fun showPCounter(cards_left: TextView, pDeck: Int, num_of_decks: Int, showCounter: Boolean)
    {
        if(showCounter == true)
        {
            cards_left.text = "${pDeck+1}/${data.getNumberOfDecks()*52}"
        }
        else
        {
            cards_left.text = ""
        }
    }

    fun showCounter(counter_text: TextView, counter: Int, showCounter: Boolean)
    {
        if(showCounter == true)
        {
            counter_text.text = "$counter"
        }
        else
        {
            counter_text.text = ""
        }
    }

    fun loadBackground()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        background = pref.getInt("BACKGROUND", R.drawable.background2_white)
        deckPic = pref.getInt("DECK_PIC", R.drawable.deck1_backside)
    }

    fun saveChip()
    {
        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("HAND_CHIP", pHandChip)
        editor.commit()
    }


    fun manageString(chip : Char)
    {
        betString = betString + char.toString()
        if(betString.length == 11)
        {
            var n = CharArray(10)
            for(i in 1..10)
            {
                n[i-1] = betString[i]
            }
            betString = ""
            for(i in 0..9)
            {
                betString = betString + n[i]
            }
        }
    }


}   // end of class BlackJack