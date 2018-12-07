package com.anishgeorge.poker

import com.sun.org.apache.xpath.internal.operations.Bool

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

    fun areCardsFlush(cards: Cards) = cards.fold(Pair(cards.first().suit, true))
    { (curSuit, stat), card ->
        Pair(curSuit, stat && card.suit == curSuit)
    }.second


    fun totalRank(cards: Cards) = cards.fold(0) { acc, card -> acc + card.rank }
}
