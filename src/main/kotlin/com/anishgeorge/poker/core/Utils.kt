package com.anishgeorge.poker.core

object Utils {

    fun uniqueCards(cards: Cards): Cards = cards.fold(emptyList()) { currentList, card ->
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

    fun totalRank(cards: Cards) = cards.fold(0) { acc, card -> acc + card.rank }

    fun repeat(times: Int, fn: (Int) -> Unit) = (1..times).forEach(fn)
}
