package com.example.card_game

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class PracticePlay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_play)

        val file: SharedPreferences = getSharedPreferences("practiceShared", Context.MODE_PRIVATE)

        val pref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val deck: ImageView = findViewById(R.id.deck_practice)
        var deckBack = pref.getInt("DECK_PICK", R.drawable.deck1_backside)
        deck.setImageResource(deckBack)
        val result1 : TextView = findViewById(R.id.result1)
        val result2 : TextView = findViewById(R.id.result2)
        val result3 : TextView = findViewById(R.id.result3)
        var result = 0
        var rand : IntArray = intArrayOf(0, 1, 2)
        var randBool = booleanArrayOf(true,true,true)

        val methodNameText : TextView = findViewById(R.id.method_name)
        val levelText : TextView = findViewById(R.id.level)

        var defaultCard : ImageView = findViewById(R.id.null_card)
        var startCard : ImageView = findViewById(R.id.deck_practice)

        defaultCard.visibility = View.INVISIBLE

        var pressText : TextView = findViewById(R.id.start_text)

        // adding ImageView-s that will represent each card
        var img1 : ImageView = findViewById(R.id.firstCard)
        var img2 : ImageView = findViewById(R.id.secondCard)
        var img3 : ImageView = findViewById(R.id.thirdCard)
        var img4 : ImageView = findViewById(R.id.forthCard)
        var img5 : ImageView = findViewById(R.id.fifthCard)
        var img6 : ImageView = findViewById(R.id.sixthCard)
        var img7 : ImageView = findViewById(R.id.seventhCard)
        var img8 : ImageView = findViewById(R.id.eighthCard)
        var img9 : ImageView = findViewById(R.id.ninethCard)
        var img10 : ImageView = findViewById(R.id.tenthCard)

        // adding each ImageView to list
        var sumCards = ArrayList<ImageView>()
            sumCards.add(img1)
            sumCards.add(img2)
            sumCards.add(img3)
            sumCards.add(img4)
            sumCards.add(img5)
            sumCards.add(img6)
            sumCards.add(img7)
            sumCards.add(img8)
            sumCards.add(img9)
            sumCards.add(img10)


        // adding TextView-s that will represent each result
        var res1 : TextView = findViewById(R.id.result1)
        var res2 : TextView = findViewById(R.id.result2)
        var res3 : TextView = findViewById(R.id.result3)

        var results = ArrayList<TextView>()
            results.add(res1)
            results.add(res2)
            results.add(res3)


        var methodName : String? = "null"
        var method = 1
        method = file.getInt("METHOD", 0)
        methodName = file.getString("METHOD_NAME", "null")

        if(method == 0){
            Log.d("PracticePLay","Error while loading method!")
        }

        val play: Practice = Practice(file, sumCards, defaultCard, startCard)

        val tap : TextView = findViewById(R.id.tap)

        setMethodName(file, methodNameText)
        setMethodLevel(play, levelText)


        tap.setOnClickListener{
            startGame(play)
            result = play.getSum()
            tap.isClickable = false
            pressText.text = ""
            rand.shuffle()
            // fill result textViews
            for(i in 0..2)
            {
                if(rand[i] == 0)
                {
                    results[i].text = "$result"
                    randBool[i] = true
                }
                else {
                    var pom = 0
                    pom = (1..5).random()
                    results[i].text = "${result+pom}"
                    randBool[i] = false
                }
            }

        }

        res1.setOnClickListener{
            if(randBool[0]){
                tap.text = "LEVEL UP"
                var pomLvl = play.getMethodLvl(play.currentMethod)
                pomLvl = pomLvl + 1
                play.setMethodlvl(play.currentMethod, pomLvl)
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                play.undealCards()
                tap.isClickable = true
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            else {
                tap.text = "FAIL!!!"
                play.undealCards()
                tap.isClickable = true
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            Log.d("METHOD LVL", "${play.currentMethod}. method level = ${play.getMethodLvl(play.currentMethod)}")
        }
        res2.setOnClickListener {
            if(randBool[1]){
                tap.text = "LEVEL UP"
                var pomLvl = play.getMethodLvl(play.currentMethod)
                pomLvl = pomLvl + 1
                play.setMethodlvl(play.currentMethod, pomLvl)
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                play.undealCards()
                tap.isClickable = true
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            else {
                tap.text = "FAIL!!!"
                play.undealCards()
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                tap.isClickable = true
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            Log.d("METHOD LVL", "${play.currentMethod}. method level = ${play.getMethodLvl(play.currentMethod)}")
        }
        res3.setOnClickListener {
            if(randBool[2]){
                tap.text = "LEVEL UP"
                var pomLvl = play.getMethodLvl(play.currentMethod)
                pomLvl = pomLvl + 1
                play.setMethodlvl(play.currentMethod, pomLvl)
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                play.undealCards()
                tap.isClickable = true
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            else {
                tap.text = "FAIL!!!"
                play.undealCards()
                tap.isClickable = true
                println("Current method lvl = ${play.getMethodLvl(play.currentMethod)}")
                setMethodName(file, methodNameText)
                setMethodLevel(play, levelText)

            }
            Log.d("METHOD LVL", "${play.currentMethod}. method level = ${play.getMethodLvl(play.currentMethod)}")
        }


    }

    }

    fun startGame(play: Practice)
    {
        play.generateDeck()
        play.dealCards()
    }

    fun setMethodName(file: SharedPreferences, textView: TextView)
    {
        textView.text = file.getString("METHOD_NAME","NULL")
    }

    fun setMethodLevel(play: Practice, textView: TextView)
    {
        textView.text = play.methodLevels[play.currentMethod-1].toString()
    }

