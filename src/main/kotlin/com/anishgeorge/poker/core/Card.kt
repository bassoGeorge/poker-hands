package com.anishgeorge.poker.core

data class Card(val value: Value, val suit: Suit): Comparable<Card> {
    override fun compareTo(other: Card): Int = rank - other.rank

    val rank get() = value.rank

    companion object {

        fun areSameRanks(vararg cards: Card): Boolean {
            if (cards.isEmpty()) return true

            val targetRank = cards[0].rank
            for (c in cards) {
                if (c.rank != targetRank) return false
            }
            return true
        }
    }
}
