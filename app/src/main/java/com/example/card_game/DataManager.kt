package com.example.card_game

import android.content.SharedPreferences
import android.util.Log

class DataManager(file : SharedPreferences) {
    private val editor : SharedPreferences.Editor = file.edit()
    private var lastBet : Int = 0
    private var currentBet : Int = 0
    private var balance : Float = 10000.0f
    private var currentPlayerSum : Int = 0
    private var currentPcSum : Int = 0
    private var maxBet : Int = 10000
    private var pDeck : Int = 0
    private var numberOfDecks : Int = 0
    private var method : Int = 1


    // pictures
    private var backgroundPic : Int = 0
    private var deckPic : Int = 0

    // current check state variables
    private var checkVuci: Boolean = false
    private var checkDosta = false
    private var checkDijeli: Boolean = true
    private var checkDouble: Boolean = false
    private var checkSplit: Boolean = false
    private var splitEnabled: Boolean = false
    private var checkRepeat: Boolean = false
    private var checkState: Boolean = false
    private var checkCancel: Boolean = false
    private var showPDeck: Boolean = true
    private var showCounter: Boolean = true
    private var ulogCheck : Boolean = true

    // pointer variables
    private var pHandPlayer: Int = 0
    private var pHandPc: Int = 0
    private var pHandSplitPlayer: Int = 0
    private var pHandChip: Int = 0

    // game state variables
    private var gameState : Boolean
    private var deckString : String

    // online
    private var username : String = ""

    // chip manager
    private var chipList : Array<Int> = arrayOf(0,0,0,0,0,0,0,0,0,0)
    private var chipCounter : Int = 0
    private var backgroundList : Array<Boolean> = arrayOf(false, false, false, false, false)
    private var deckList : Array<Boolean> = arrayOf(false, false, false, false, false, false)



    init {
        // all required data
        lastBet = file.getInt("LAST_BET", 0)
        currentBet = file.getInt("CURRENT_BET", 0)
        balance = file.getFloat("BALANCE", 10000.0f)
        currentPlayerSum = file.getInt("CURRENT_PLAYER_SUM",0)
        currentPcSum = file.getInt("CURRENT_PC_CUM", 0)
        maxBet = file.getInt("MAX_BET", 10000)
        pDeck = file.getInt("POINTER_ON_DECK", 0)
        numberOfDecks = file.getInt("NUMBER_OF_DECKS", 1)
        if(numberOfDecks == 0){Log.d("TAG", "Number of decks == 0!!!")}
        backgroundPic = file.getInt("BACKGROUND_PIC", R.drawable.background2_white)
        deckPic = file.getInt("DECK_BACKSIDE", R.drawable.deck1_backside)
        method = file.getInt("METHOD", 1)
        // CHECK VARIABLES
        checkVuci = file.getBoolean("CHECK_VUCI", false)
        checkDosta = file.getBoolean("CHECK_DOSTA", false)
        checkDijeli = file.getBoolean("CHECK_DIJELI", false)
        checkDouble = file.getBoolean("CHECK_DOUBLE", false)
        checkSplit = file.getBoolean("CHECK_SPLIT", false)
        splitEnabled = file.getBoolean("SPLIT_ENABLED", false)
        checkRepeat = file.getBoolean("CHECK_REPEAT", false)
        checkState = file.getBoolean("CHECK_STATE", false)
        checkCancel = file.getBoolean("CHECK_CANCEL", false)
        showPDeck = file.getBoolean("SHOW_P_DECK", false)
        showCounter = file.getBoolean("SHOW_COUNTER", false)
        ulogCheck = file.getBoolean("ULOG_CHECK", false)

        // LIST OF CHIPS AND COUNTER
        chipList[0] = file.getInt("CHIP1", 0)
        chipList[1] = file.getInt("CHIP2", 0)
        chipList[2] = file.getInt("CHIP3", 0)
        chipList[3] = file.getInt("CHIP4", 0)
        chipList[4] = file.getInt("CHIP5", 0)
        chipList[5] = file.getInt("CHIP6", 0)
        chipList[6] = file.getInt("CHIP7", 0)
        chipList[7] = file.getInt("CHIP8", 0)
        chipList[8] = file.getInt("CHIP9", 0)
        chipList[9] = file.getInt("CHIP10", 0)
        chipCounter = file.getInt("CHIP_COUNTER", 0)
        // LIST OF BACKGROUND CHECK VARIABLES
        backgroundList[0] = file.getBoolean("BACK1", false)
        backgroundList[1] = file.getBoolean("BACK2", false)
        backgroundList[2] = file.getBoolean("BACK3", false)
        backgroundList[3] = file.getBoolean("BACK4", false)
        backgroundList[4] = file.getBoolean("BACK5", false)
        // LIST OF DECK CHECK VARIABLES
        deckList[0] = file.getBoolean("DECK1", false)
        deckList[1] = file.getBoolean("DECK2", false)
        deckList[2] = file.getBoolean("DECK3", false)
        deckList[3] = file.getBoolean("DECK4", false)
        deckList[4] = file.getBoolean("DECK5", false)
        deckList[5] = file.getBoolean("DECK6", false)

        // GAME STATE VARIABLES
        gameState = file.getBoolean("GAME_STATE", false)
        deckString = file.getString("DECK_STRING", "")!!

    }

    // SETTERS
    fun setChipCounter(count : Int) {
        chipCounter = count
    }
    fun plusChipCounter() {
        chipCounter += 1
        if(chipCounter >= 10){
            chipCounter = 0
        }
    }
    fun setChipList(index : Int, res : Int) {
        chipList[index] = res
        putValue("CHIP${index}", res)
    }
    fun setLastBet(last_bet : Int) {
        lastBet = last_bet
        putValue("LAST_BET", last_bet)
    }
    fun setCurrentBet(current_bet : Int) {
        currentBet = current_bet
        putValue("CURRENT_BET", current_bet)
    }
    fun setBalance(stanje: Float) {
        balance = balance + stanje
        putValue("BALANCE", stanje)
    }
    fun setJustBalance(stanje : Float)
    {
        balance = stanje
        putValue("BALANCE", stanje)
    }
    fun setCurrentPlayerSum(playerSum : Int) {
        currentPlayerSum = playerSum
        putValue("CURRENT_PLAYER_SUM", playerSum)
    }
    fun setCurrentPcSum(pcSum : Int){
        currentPcSum = pcSum
        putValue("CURRENT_PC_SUM", pcSum)
    }
    fun setMaxBet(max_bet : Int)
    {
        maxBet = max_bet
        putValue("MAX_BET", max_bet)
    }
    fun setCounter(count : Int){
        pDeck = count
        putValue("COUNTER", count)
    }
    fun setDeckPic(pic : Int){
        deckPic = 0
        putValue("DECK_BACKSIDE", pic)
    }
    fun setMethod(met : Int){
        method = met
        putValue("METHOD", met)
    }
    fun setUsername(name : String){
        username = name
        putValue("USERNAME", name)
    }
    fun setCheckVuci(check : Boolean){
        checkVuci = check
        putValue("CHECK_VUCI", check)
    }
    fun setCheckDosta(check : Boolean){
        checkDosta = check
        putValue("CHECK_DOSTA", check)
    }
    fun setCheckDijeli(check : Boolean){
        checkDijeli = check
        putValue("CHECK_DIJELI", check)
    }
    fun setCheckDouble(check : Boolean){
        checkDouble = check
        putValue("CHECK_DOUBLE", check)
    }
    fun setCheckSplit(check : Boolean){
        checkSplit = check
        putValue("CHECK_SPLIT", check)
    }
    fun setSplitEnabled(check : Boolean){
        splitEnabled = check
        putValue("SPLIT_ENABLED", check)
    }
    fun setCheckRepeat(check : Boolean)
    {
        checkRepeat = check
        putValue("CHECK_REPEAT", check)
    }
    fun setCheckState(check : Boolean)
    {
        checkState = check
        putValue("CHECK_STATE", check)
    }
    fun setCheckCancel(check : Boolean){
        checkCancel = check
        putValue("CHECK_CANCEL", check)
    }
    fun setUlogCheck(check : Boolean){
        ulogCheck = check
        putValue("ULOG_CHECK", check)
    }
    fun setShowPDeck(check : Boolean){
        showPDeck = check
        putValue("SHOW_P_DECK", check)
    }
    fun setShowCounter(check : Boolean){
        showCounter = check
        putValue("SHOW_COUNTER", check)
    }
    fun setNumberOfDecks(num : Int) {
        numberOfDecks = num
        putValue("NUMBER_OF_DECKS", num)
    }
    fun setBackgroundList(num : Int, check : Boolean) {
        backgroundList[num] = check
        putValue("BACK${num}", check)
    }
    fun setDeckList(num : Int, check : Boolean) {
        deckList[num] = check
        putValue("DECK${num+1}", check)
    }
    fun setGameState(check : Boolean) {
        gameState = check
        putValue("GAME_STATE", check)
    }
    fun setDeckString(deck_string : String) {
        deckString = deck_string
        putValue("DECK_STRING", deck_string)
    }
    fun addDeckString(deck_string : String) {
        deckString = "$deck_string$deck_string"
        putValue("DECK_STIRNG", deckString)
    }

    // GETTERS
    fun getMaxBet() : Int
    {
        return maxBet
    }
    fun getBalance() : Float{
        return balance
    }
    fun getCurrentBet() : Int {
        return currentBet
    }
    fun getLastBet() : Int {
        return lastBet
    }
    fun getCurrentPlayerSum() : Int {
        return currentPlayerSum
    }
    fun getPDeck() : Int {
        return pDeck
    }
    fun getDeckPic() : Int {
        return deckPic
    }
    fun getUsername() : String {
        return username
    }
    fun getCheckVuci() : Boolean {
        return checkVuci
    }
    fun getCheckDijeli() : Boolean {
        return checkDijeli
    }
    fun getCheckDouble() : Boolean {
        return checkDouble
    }
    fun getCheckSplit() : Boolean {
        return checkSplit
    }
    fun getCheckDosta() : Boolean {
        return checkDosta
    }
    fun getSplitEnabled() : Boolean {
        return splitEnabled
    }
    fun getCheckRepeat() : Boolean {
        return checkRepeat
    }
    fun getCheckState() : Boolean {
        return checkState
    }
    fun getCheckCancel() : Boolean {
        return checkCancel
    }
    fun getShowPDeck() : Boolean {
        return showPDeck
    }
    fun getShowCounter() : Boolean {
        return showCounter
    }
    fun getMethod() : Int {
        return method
    }
    fun getNumberOfDecks() : Int {
        return numberOfDecks
    }
    fun getChipCounter() : Int {
        return chipCounter
    }
    fun getChipList(index : Int) : Int {
        return chipList[index]
    }
    fun getFullChipList() : Array<Int> {
        return chipList
    }
    fun getBackgroundList(num : Int) : Boolean {
        return backgroundList[num]
    }
    fun getDeckList(num : Int) : Boolean {
        return deckList[num]
    }
    fun getUlogCheck() : Boolean {
        return ulogCheck
    }
    fun getGameState() : Boolean {
        return gameState
    }

    // IMPORTANT FUNCTIONS
    fun pDeckPlus()
    {
        if(pDeck >= ((numberOfDecks*52)-1)) {
            pDeck = 0
            savePDeck()
        }
        else{
            pDeck += 1
            savePDeck()
        }
    }

    // SAVE FUNCTIONS
    fun savePDeck()
    {
        putValue("POINTER_ON_DECK", pDeck)
    }


    // other functions
    fun putValue(name : String, value: Int)
    {
        editor.putInt(name, value)
        editor.commit()
    }
    fun putValue(name : String, value: Float)
    {
        editor.putFloat(name, value)
        editor.commit()
    }
    fun putValue(name : String, value: Boolean)
    {
        editor.putBoolean(name, value)
        editor.commit()
    }
    fun putValue(name : String, value : String)
    {
        editor.putString(name, value)
        editor.commit()
    }


}