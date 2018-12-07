package com.anishgeorge.poker

class CardSet(unsortedCards: Cards) {

    private val cards = unsortedCards.sortedDescending()
    private val cardGroups = cards.groupBy { it.value }

    constructor(vararg unsortedCards: Card) : this(unsortedCards.toList())

    fun highest(): Card = cards.first()
    fun toList(): Cards = cards
    fun inRankGroups(): Map<Value, Cards> = cardGroups

    // TODO: do we need to give pair even if its a triple? or stuff like that.. need to do some checks
    private fun getSameValueCardsByCount(count: Int): List<Cards> = inRankGroups()
            .filter { (_, value) -> value.size == count }
            .values.toList()

    /* Stat variables */
    val pairs: List<Cards> by lazy { getSameValueCardsByCount(2) }

    val triples: List<Cards> by lazy { getSameValueCardsByCount(3) }

    val quadruples: List<Cards> by lazy { getSameValueCardsByCount(4) }

    val straights: List<Cards> by lazy {
        val cards = Utils.uniqueCards(toList())
        if (cards.size < 5) emptyList()
        else (0..(cards.size - 5))
                .map { cards.subList(it, it + 5) }
                .filter(Utils::areCardsStraightInRank)
    }

    // TODO: these flushes don't consider uniqueness of cards, so cannot be used for straight flushes
    // OR WAIT
    // Maybe it can be used. Uniqueness in card value is guaranteed as per card deck, so can be checked for straights
    val flushes: List<Cards> by lazy {
        toList()
                .groupBy { it.suit }
                .filterValues { it.size >= 5 }
                .values
                .flatMap { flushCards -> (0..(flushCards.size - 5)).map { flushCards.subList(it, it + 5) } }
    }

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
