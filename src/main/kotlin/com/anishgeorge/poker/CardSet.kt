package com.anishgeorge.poker

import java.util.*

typealias CardSet = SortedSet<Card>

fun cardSetOf(vararg cards: Card): CardSet {
    val set = TreeSet<Card>()
    for (card in cards) set.add(card)
    return set
}
