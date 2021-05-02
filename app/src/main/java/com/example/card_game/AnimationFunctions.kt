package com.example.card_game

import android.widget.ImageView

open class AnimationFunctions {


    fun slideCard(m1: ImageView, m2: ImageView)
    {
        m1.animate()
            .x(m2.x)
            .y(m2.y)
            .setDuration(1000)
            .withEndAction{
                m1.x = m2.x
                m1.y = m2.y
            }
            .start()
    }

}