package com.anishgeorge.poker

object HandUtils {

    fun highCard(hand: CardSet): Card? {
        return hand.highest()
    }

    fun pairs(hand: CardSet): List<List<Card>> {
        return hand
                .toList()
                .groupBy { it.value }
                .filter { entry -> entry.value.size == 2 }
                .values
                .toList()
    }

}
