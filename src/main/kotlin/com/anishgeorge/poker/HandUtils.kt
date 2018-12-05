package com.anishgeorge.poker

object HandUtils {

    fun highCard(hand: CardSet): Card? {
        return hand.highest()
    }

    fun pairs(hand: CardSet): List<List<Card>> {
        return hand
                .inRankGroups()
                .filter { entry -> entry.value.size == 2 }
                .values
                .toList()
    }

    fun triples(hand: CardSet): List<List<Card>> {
        return hand
                .inRankGroups()
                .filter { entry -> entry.value.size == 3 }
                .values
                .toList()
    }

}
