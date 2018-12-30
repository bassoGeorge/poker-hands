package com.anishgeorge.poker.core

import com.anishgeorge.poker.exceptions.InvalidCardShortHandException

data class Card(val value: Value, val suit: Suit) : Comparable<Card> {
    override fun compareTo(other: Card): Int = rank - other.rank

    val rank get() = value.rank
    val aceLowRank get() = value.aceLowRank

    companion object {

        private val shortHandRegex = """^(.+)(.)$""".toRegex()

        fun fromShort(short: String): Card {
            val matches = shortHandRegex.find(short)
            val exception = InvalidCardShortHandException("$short is an invalid shorthand for playing card")

            val (vShort, sShort) = matches?.destructured ?: throw exception

            val value = Value.fromShort(vShort) ?: throw exception
            val suit = Suit.fromShort(sShort) ?: throw exception

            return Card(value, suit)
        }
    }
}

fun String.toCard(): Card = Card.fromShort(this)

fun cardListOf(vararg cardShots: String) = cardShots.map(String::toCard)
