package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CardSetTest {

    @Test
    fun returnsThePairsInAHand() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.THREE, Suit.DIAMONDS)
        )

        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS), Card(Value.JACK, Suit.HEARTS)),
                        listOf(Card(Value.THREE, Suit.CLUBS), Card(Value.THREE, Suit.DIAMONDS))
                ),
                set.pairs
        )
    }


    @Test
    fun returnsTheTriplesInAHand() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.JACK, Suit.DIAMONDS)
        )

        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS), Card(Value.JACK, Suit.HEARTS), Card(Value.JACK, Suit.DIAMONDS))
                ),
                set.triples
        )
    }


    @Test
    fun returnsTheStraightsInASet() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS)
        )

        assertEquals(
                listOf(
                        listOf(Card(Value.JACK, Suit.DIAMONDS),
                                Card(Value.TEN, Suit.CLUBS),
                                Card(Value.NINE, Suit.HEARTS),
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.DIAMONDS))
                ),
                set.straights
        )
    }

    @Test
    fun returnsEmptyStraightsWhenNonInSet() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.NINE, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS)
        )

        assertEquals(emptyList<List<Card>>(), set.straights)
    }

    @Test
    fun returnsTheStraightsInASetOfMoreThan5Cards() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.SIX, Suit.SPADES)
        )

        assertEquals(
                listOf(
                        listOf(
                                Card(Value.JACK, Suit.DIAMONDS),
                                Card(Value.TEN, Suit.CLUBS),
                                Card(Value.NINE, Suit.HEARTS),
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.DIAMONDS)),
                        listOf(
                                Card(Value.TEN, Suit.CLUBS),
                                Card(Value.NINE, Suit.HEARTS),
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.DIAMONDS),
                                Card(Value.SIX, Suit.SPADES))
                ),
                set.straights
        )
    }


    @Test
    fun returnsTheStraightsInASetOfMoreThan5CardsWhenThereArePairsOrTriplesInTheMix() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.NINE, Suit.SPADES)
        )

        assertEquals(
                listOf(
                        listOf(
                                Card(Value.JACK, Suit.DIAMONDS),
                                Card(Value.TEN, Suit.CLUBS),
                                Card(Value.NINE, Suit.HEARTS),
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.DIAMONDS))
                ),
                set.straights
        )
    }
}
