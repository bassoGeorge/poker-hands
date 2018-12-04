package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CardSetTest {
    @Test
    fun shouldCreateCardSetWithGivenCards() {
        val set = cardSetOf(
                Card(Value.KING, Suit.HEARTS),
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.SEVEN, Suit.HEARTS)
        )

        val expectedArray = arrayOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.KING, Suit.HEARTS),
                Card(Value.SEVEN, Suit.HEARTS)
        )
        assertArrayEquals(expectedArray, set.toTypedArray())
    }
}
