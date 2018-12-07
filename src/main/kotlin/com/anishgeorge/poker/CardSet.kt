package com.anishgeorge.poker

class CardSet(unsortedCards: Cards) {

    private val cards = unsortedCards.sortedDescending()
    private val cardGroups = cards.groupBy { it.value }

    constructor(vararg unsortedCards: Card) : this(unsortedCards.toList())

    fun highest(): Card = cards.first()
    fun toList(): Cards = cards
    fun inRankGroups(): Map<Value, Cards> = cardGroups

    /* Stat variables */
    val pairs: List<Cards> by lazy {
        inRankGroups()
                .filter { (_, value) -> value.size == 2 }
                .values.toList()
    }

    val triples: List<Cards> by lazy {
        inRankGroups()
                .filter { (_, value) -> value.size == 3 }
                .values.toList()
    }

    val straights: List<Cards> by lazy {
        val cards = Utils.uniqueCards(toList());
        if (cards.size < 5) emptyList()
        else (0..(cards.size - 5))
                .map { cards.subList(it, it + 5) }
                .filter(Utils::areCardsStraightInRank)
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
