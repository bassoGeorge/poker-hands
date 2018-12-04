package com.anishgeorge.poker

import java.util.*

object HandUtils {

    fun highCard(hand: CardSet): Card? {
        return hand.first()
    }
}
