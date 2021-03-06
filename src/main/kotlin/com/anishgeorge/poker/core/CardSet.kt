package com.anishgeorge.poker.core

/* Invariant
   We rely on the fact that CardSet is only going to be used with a single deck of cards.
   i.e. there wont be a situation where CardSet will get two exactly same cards, in value and set.
   Our logic for flush, straight and straight flush relies on this
 */
internal class CardSet(unsortedCards: Cards) {

    val cards = unsortedCards.sortedDescending()
    private val valueGroups by lazy { cards.groupBy { it.value } }
    private val uniqueCards by lazy { Utils.uniqueCards(cards) }

    constructor(vararg unsortedCards: Card) : this(unsortedCards.toList())

    private fun getSameValueCardsByCount(count: Int): List<Cards> = valueGroups
            .filter { (_, value) -> value.size >= count }
            .values.toList()
            .map { it.take(count) }

    /* Stat variables */
    val pairs: List<Cards> by lazy { getSameValueCardsByCount(2) }

    val triples: List<Cards> by lazy { getSameValueCardsByCount(3) }

    val quadruples: List<Cards> by lazy { getSameValueCardsByCount(4) }

    val straights: List<Cards> by lazy {
        if (uniqueCards.size < 5) emptyList()
        else (0..(uniqueCards.size - 5))
                .map { uniqueCards.subList(it, it + 5) }
                .filter(Utils::areCardsStraightInAceHighRank) + aceLowStraights
    }

    private val aceLowStraights: List<Cards> by lazy {
        val aceLowCards = uniqueCards.sortedByDescending { it.aceLowRank }.takeLast(5)
        if (aceLowCards.last().value == Value.ACE && Utils.areCardsStraightInAceLowRank(aceLowCards)) listOf(aceLowCards)
        else emptyList()
    }

    val flushes: List<Cards> by lazy { Utils.flushesInCards(cards) }

    val straightFlushes: List<Cards> by lazy {
        val flushSet = flushes.toSet()
        val straightSet = straights.toSet()
        flushSet.intersect(straightSet).toList() + aceLowStraights.filter { Utils.flushesInCards(it).size == 1 }
    }

    val fullHouses: List<Cards> by lazy {
        if (triples.isEmpty() || pairs.isEmpty()) emptyList()
        else {
            triples.flatMap { triple ->
                pairs.filter { pair ->
                    pair.first().value != triple.first().value
                }.map { pair -> triple + pair }
            }
        }
    }
    /* End of stat variables */

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
