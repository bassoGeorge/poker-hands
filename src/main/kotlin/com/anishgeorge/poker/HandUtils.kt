package com.anishgeorge.poker

object HandUtils {

    fun uniqueCards(cards: List<Card>): List<Card> = cards.fold(listOf()) { currentList, card ->
        if (currentList.find { it.rank == card.rank } == null) currentList + card else currentList
    }

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

        val isStraight5Cards = fun(cards: List<Card>): Boolean {        // Anonymous function
            var currentRank = cards.first().rank
            for (card in cards.takeLast(cards.size - 1)) {
                if (currentRank != card.rank + 1) return false
                currentRank = card.rank
            }
            return true
        }

        val cards = uniqueCards(hand.toList())                  // list is always sorted descending order of rank
        if (cards.size < 5) return listOf()
        return (0..(cards.size - 5))
                .map { cards.subList(it, it + 5) }
                .filter(isStraight5Cards)                              // When using anonymous functions
    }

}
