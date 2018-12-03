package com.anishgeorge.poker

data class Card(val value: Value, val suit: Suit) {
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
