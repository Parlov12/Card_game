package com.example.card_game

class game(var number: Int = 0, var type: String = "", var points: Int = 0, var value: Int = 0){

    var cards = arrayOfNulls<game>(40)


}
class player(var igrac: Int = 0, var bodovi: Int = 0, var ime: String = "Unknown")
{


}

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

} // end of fun hand winner














