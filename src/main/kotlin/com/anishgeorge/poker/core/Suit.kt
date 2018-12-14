package com.anishgeorge.poker.core

enum class Suit {
    SPADES, CLUBS, DIAMONDS, HEARTS;

    val short = name[0].toString()

    companion object {
        private val shortHandMap = values().map { Pair(it.short, it) }.toMap()
        fun fromShort(short: String): Suit? = shortHandMap[short]
    }
}
