package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class HandUtilsTest {

    @Test
    fun returnsHighCardFromGivenHand3() {
        val hand = CardSet(
                Card(Value.TEN, Suit.HEARTS),
                Card(Value.TWO, Suit.DIAMONDS),
                Card(Value.ACE, Suit.SPADES),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.KING, Suit.HEARTS)
        )

        assertEquals(Card(Value.ACE, Suit.SPADES), HandUtils.highCard(hand))
    }

    @Test
    fun returnsThePairsInAHand() {
        val hand = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.THREE, Suit.DIAMONDS)
        )

        val pairs = HandUtils.pairs(hand)
        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS), Card(Value.JACK, Suit.HEARTS)),
                        listOf(Card(Value.THREE, Suit.CLUBS), Card(Value.THREE, Suit.DIAMONDS))
                ),
                pairs
        )
    }
}
