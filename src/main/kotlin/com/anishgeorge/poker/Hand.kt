package com.anishgeorge.poker

class Hand(
        val type: HandType = HandType.HIGH_CARD,
        val participatingCards: Cards
) : Comparable<Hand> {
    val rank get() =
        (type.rank * 1000) + (Utils.totalRank(participatingCards) * 100)


    override fun compareTo(other: Hand): Int = rank - other.rank

    /* Standard overrides */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hand

        if (type != other.type) return false
        if (participatingCards != other.participatingCards) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + participatingCards.hashCode()
        return result
    }

    override fun toString(): String {
        return "Hand(type=$type, participatingCards=$participatingCards)"
    }


    companion object {
        fun bestOf(cardSet: CardSet): Hand {
            return when {
                cardSet.flushes.isNotEmpty() -> Hand(HandType.FLUSH, cardSet.flushes.first())
                cardSet.straights.isNotEmpty() -> Hand(HandType.STRAIGHT, cardSet.straights.first())
                cardSet.triples.isNotEmpty() -> Hand(HandType.THREE_OF_A_KIND, cardSet.triples.first())
                cardSet.pairs.size == 1 -> Hand(HandType.ONE_PAIR, cardSet.pairs.first())
                cardSet.pairs.size == 2 -> Hand(HandType.TWO_PAIR, cardSet.pairs.flatten())
                else -> return Hand(HandType.HIGH_CARD, listOf(cardSet.highest()))
            }
        }
        fun bestOf(vararg cards: Card): Hand = bestOf(CardSet(*cards))
        fun bestOf(cards: Cards): Hand = bestOf(CardSet(cards))

    }
}
