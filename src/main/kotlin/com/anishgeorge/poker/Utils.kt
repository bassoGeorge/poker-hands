package com.anishgeorge.poker

object Utils {

    fun uniqueCards(cards: Cards): Cards = cards.fold(emptyList()) {
        currentList, card ->
        if (currentList.find { it.rank == card.rank } == null) currentList + card else currentList
    }

    fun areCardsStraightInRank(cards: Cards): Boolean {
        var currentRank = cards.first().rank
        for (card in cards.takeLast(cards.size - 1)) {
            if (currentRank != card.rank + 1) return false
            currentRank = card.rank
        }
        return true
    }
}
