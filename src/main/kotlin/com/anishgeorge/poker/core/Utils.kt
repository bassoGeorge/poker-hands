package com.anishgeorge.poker.core

import kotlin.math.pow

object Utils {

    fun uniqueCards(cards: Cards): Cards = cards.fold(emptyList()) { currentList, card ->
        if (currentList.find { it.rank == card.rank } == null) currentList + card else currentList
    }

    private fun areCardsStraightInGivenRanker(cards: Cards, getRank: (Card) -> Int): Boolean {
        var currentRank = getRank(cards.first())
        for (card in cards.takeLast(cards.size - 1)) {
            if (currentRank != getRank(card) + 1) return false
            currentRank = getRank(card)
        }
        return true
    }

    fun areCardsStraightInAceHighRank(cards: Cards) = areCardsStraightInGivenRanker(cards) { it.rank }
    fun areCardsStraightInAceLowRank(cards: Cards) = areCardsStraightInGivenRanker(cards) { it.aceLowRank }

    private fun totalRankByRanker(cards: Cards, getRank: (Card) -> Int) = cards.fold(0) { acc, card -> acc + getRank(card) }
    fun totalRank(cards: Cards) = totalRankByRanker(cards) { it.rank }
    fun totalAceLowRank(cards: Cards) = totalRankByRanker(cards) { it.aceLowRank }

    // NOTE: Just coz this logic was repeated, had to move it to utility... code smell?
    fun flushesInCards(cards: Cards): List<Cards> = cards
            .groupBy { it.suit }
            .filterValues { it.size >= 5 }
            .values
            .flatMap { flushCards -> (0..(flushCards.size - 5)).map { flushCards.subList(it, it + 5) } }

    fun repeat(times: Int, fn: (Int) -> Unit) = (1..times).forEach(fn)

    fun geometricProgression(a: Int, r: Int): (Int) -> Long {
        val rd = r.toDouble()
        return fun(n: Int):Long {
            return (a * (rd.pow(n - 1))).toLong()
        }
    }

    val longRankComparator = Comparator<Long> { r1, r2 ->
        val diff = r1 - r2
        when {
            diff > 0 -> 1
            diff < 0 -> -1
            else -> 0
        }
    }
}
