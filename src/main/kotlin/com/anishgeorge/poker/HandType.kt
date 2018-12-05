package com.anishgeorge.poker

enum class HandType(val rank: Int) {
    ONE_PAIR(0),
    HIGH_CARD(1),
    TWO_PAIR(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8),
    ROYAL_FLUSH(9)
}
