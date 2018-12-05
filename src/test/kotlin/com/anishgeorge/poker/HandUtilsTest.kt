package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HandUtilsTest {

    @Test
    fun returnsHighCardFromGivenHand() {
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

    @Test
    fun returnsTheTriplesInAHand() {
        val hand = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.JACK, Suit.DIAMONDS)
        )

        val triples = HandUtils.triples(hand)
        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS), Card(Value.JACK, Suit.HEARTS), Card(Value.JACK, Suit.DIAMONDS))
                ),
                triples
        )
    }

    @Test
    fun returnsTheStraightsInAHand() {
        val hand = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS)
        )

        val straights = HandUtils.straights(hand)
        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS),
                                Card(Value.TEN, Suit.CLUBS),
                                Card(Value.NINE, Suit.HEARTS),
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.DIAMONDS))
                ),
                straights
        )
    }

    @Test
    fun returnsTheStraightsInAHandNegative() {
        val hand = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.NINE, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS)
        )

        val straights = HandUtils.straights(hand)
        assertEquals(listOf<List<Card>>(), straights)
    }
}
