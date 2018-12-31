package com.anishgeorge.poker.core.hand

import com.anishgeorge.poker.core.Cards
import com.anishgeorge.poker.core.HandType

class FullHouse(participatingCards: Cards): Hand(HandType.FULL_HOUSE, participatingCards) {

    override fun participatingCardsStrength(): Int {
        val tripleRank = participatingCards.first().rank
        val pairRank = participatingCards.last().rank
        return (tripleRank * 100) + pairRank
    }
}
