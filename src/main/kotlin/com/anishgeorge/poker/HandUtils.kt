package com.anishgeorge.poker

import java.util.*
import kotlin.Comparator

object HandUtils {

    fun highCard(hand: CardSet): Card? {
        return hand.first()
    }
}
