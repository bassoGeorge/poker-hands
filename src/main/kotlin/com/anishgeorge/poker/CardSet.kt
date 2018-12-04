package com.anishgeorge.poker

import java.util.*

class CardSet(unsortedCards: List<Card>) {

    private val cards = unsortedCards.sortedDescending()
    constructor(vararg unsortedCards: Card): this(unsortedCards.toList())

    fun highest() = cards.first()
    fun toList() = cards

    /* Standard overrides */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardSet

        if (cards != other.cards) return false

        return true
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }

    override fun toString(): String {
        return "CardSet(cards=$cards)"
    }

}
