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

    fun straights(hand: CardSet): List<List<Card>> {

        fun isStraight5Cards(cards: List<Card>): Boolean {
            var currentRank = cards.first().rank
            for (card in cards.takeLast(cards.size - 1)) {
                if (currentRank != card.rank + 1) return false
                currentRank = card.rank
            }
            return true;
        }

        val cards = hand.toList() // list is always sorted descending order of rank
        if (cards.size < 5) return listOf()
        return if (isStraight5Cards(cards)) listOf(cards) else listOf();
    }

}
