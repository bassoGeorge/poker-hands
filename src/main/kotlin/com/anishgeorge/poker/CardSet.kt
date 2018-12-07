package com.anishgeorge.poker

/* Invariant
   We rely on the fact that CardSet is only going to be used with a single deck of cards.
   i.e. there wont be a situation where CardSet will get two exactly same cards, in value and set.
   Our logic for flush, straight and straight flush relies on this
 */
class CardSet(unsortedCards: Cards) {

    private val cards = unsortedCards.sortedDescending()
    private val cardGroups = cards.groupBy { it.value }

    constructor(vararg unsortedCards: Card) : this(unsortedCards.toList())

    fun highest(): Card = cards.first()
    private fun toList(): Cards = cards
    private fun inRankGroups(): Map<Value, Cards> = cardGroups

    private fun getSameValueCardsByCount(count: Int): List<Cards> = inRankGroups()
            .filter { (_, value) -> value.size >= count }
            .values.toList()
            .map { it.take(count) }

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

    val flushes: List<Cards> by lazy {
        toList()
                .groupBy { it.suit }
                .filterValues { it.size >= 5 }
                .values
                .flatMap { flushCards -> (0..(flushCards.size - 5)).map { flushCards.subList(it, it + 5) } }
    }

    val straightFlushes: List<Cards> by lazy {
        val flushSet = flushes.toSet()
        val straightSet = straights.toSet()
        flushSet.intersect(straightSet).toList()
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
