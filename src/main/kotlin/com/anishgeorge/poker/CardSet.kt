package com.anishgeorge.poker

import java.util.*

class CardSet(unsortedCards: List<Card>) {

    private val cards = unsortedCards.sortedDescending()
    private val cardGroups = cards.groupBy { it.value }

    constructor(vararg unsortedCards: Card): this(unsortedCards.toList())

    fun highest(): Card = cards.first()
    fun toList(): List<Card> = cards
    fun inRankGroups(): Map<Value, List<Card>> = cardGroups

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
