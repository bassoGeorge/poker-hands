package com.anishgeorge.poker.core

class Hand(
        val type: HandType = HandType.HIGH_CARD,
        val participatingCards: Cards
) : Comparable<Hand> {

    val isAceLow by lazy {
        when (type) {
            HandType.STRAIGHT, HandType.STRAIGHT_FLUSH ->
                participatingCards.isNotEmpty() && participatingCards.last().value == Value.ACE
            else -> false
        }
    }

    val rank: Long by lazy {
        val zeros = rankProgression(6) // 1 digit over the strength of the hand
        ((type.rank * zeros)
                + (strengthOfCards()))
    }

    // Should return a max 4 digit long number in strength
    private fun strengthOfCards(): Long {
        val getRank = if (isAceLow) Card::aceLowRank else Card::rank
        return participatingCards
                .reversed()         // We now go from least important to most important card
                .map(getRank)
                .foldIndexed(0L) { idx, acc, curr -> acc + (curr * rankProgression(idx + 1)) }
    }

    override fun compareTo(other: Hand) = Utils.longRankComparator.compare(rank, other.rank)

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

        internal fun bestOf(cardSet: CardSet): Hand {
            val (type, cards) = when {
                cardSet.straightFlushes.isNotEmpty() && cardSet.straightFlushes.first().first().value == Value.ACE ->
                    HandType.ROYAL_FLUSH to cardSet.straightFlushes.first()

                cardSet.straightFlushes.isNotEmpty() ->
                    HandType.STRAIGHT_FLUSH to cardSet.straightFlushes.first()

                cardSet.quadruples.isNotEmpty() ->
                    HandType.FOUR_OF_A_KIND to cardSet.quadruples.first()

                cardSet.fullHouses.isNotEmpty() ->
                    HandType.FULL_HOUSE to cardSet.fullHouses.first()

                cardSet.flushes.isNotEmpty() ->
                    HandType.FLUSH to cardSet.flushes.first()

                cardSet.straights.isNotEmpty() ->
                    HandType.STRAIGHT to cardSet.straights.first()

                cardSet.triples.isNotEmpty() ->
                    HandType.THREE_OF_A_KIND to cardSet.triples.first()

                cardSet.pairs.size >= 2 ->
                    HandType.TWO_PAIR to cardSet.pairs.take(2).flatten()

                cardSet.pairs.isNotEmpty() ->
                    HandType.ONE_PAIR to cardSet.pairs.first()

                else ->
                    HandType.HIGH_CARD to cardSet.cards
            }

            val kickers = if (cards.size == 5) emptyList() else (cardSet.cards - cards).take(5 - cards.size)
            return Hand(type, cards + kickers)
        }

        fun bestOf(vararg cards: Card): Hand = bestOf(CardSet(*cards))

        fun bestOf(cards: Cards): Hand = bestOf(CardSet(cards))

        private val rankProgression = Utils.geometricProgression(1, 100)

    }
}
