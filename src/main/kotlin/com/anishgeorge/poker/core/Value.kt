package com.anishgeorge.poker.core

enum class Value(val rank: Int = 1, short: String? = null) {
    ACE(14, "A") {
        override val aceLowRank = 1
    },
    KING(13, "K"),
    QUEEN(12, "Q"),
    JACK(11, "J"),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2);

    val short = short ?: rank.toString()
    open val aceLowRank get() = rank

    companion object {
        private val shortHandMap = values().map { Pair(it.short, it) }.toMap()
        fun fromShort(short: String): Value? = shortHandMap[short]
        fun fromShort(short: Int): Value? = fromShort(short.toString())
    }
}
