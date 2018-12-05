package com.anishgeorge.poker

class Hand(val type: HandType = HandType.ONE_PAIR) {

    companion object {
        fun findBestHandOf(cardSet: CardSet): Hand {
            val triples = HandUtils.triples(cardSet)
            if (triples.isNotEmpty()) return Hand(HandType.THREE_OF_A_KIND)

            val pairs = HandUtils.pairs(cardSet)
            if (pairs.size == 1) return Hand(HandType.ONE_PAIR)
            else if (pairs.size == 2) return Hand(HandType.TWO_PAIR)

            return Hand(HandType.HIGH_CARD)
        }
    }
}
