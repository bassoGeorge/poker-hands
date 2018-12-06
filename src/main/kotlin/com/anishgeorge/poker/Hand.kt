package com.anishgeorge.poker

class Hand(
        val type: HandType = HandType.HIGH_CARD
) : Comparable<Hand> {
    val rank get() = type.rank

    override fun compareTo(other: Hand): Int = rank - other.rank

    /* Standard overrides */
    override fun toString(): String {
        return "Hand(type=$type)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hand

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }


    companion object {
        fun findBestHandOf(cardSet: CardSet): Hand {
            if (cardSet.straights.isNotEmpty()) return Hand(HandType.STRAIGHT)
            if (cardSet.triples.isNotEmpty()) return Hand(HandType.THREE_OF_A_KIND)
            if (cardSet.pairs.size == 1) return Hand(HandType.ONE_PAIR)
            else if (cardSet.pairs.size == 2) return Hand(HandType.TWO_PAIR)

            return Hand(HandType.HIGH_CARD)
        }
    }
}
