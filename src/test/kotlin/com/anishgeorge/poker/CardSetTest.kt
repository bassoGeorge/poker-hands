package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class CardSetTest {

    @Test
    fun returnsThePairsInASet() {
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
    fun returnsTheTriplesInASet() {
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
    fun returnsTheQuadruplesInASet() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.SPADES),
                Card(Value.JACK, Suit.DIAMONDS)
        )

        assertEquals(
                listOf(
                        listOf(
                                Card(Value.JACK, Suit.DIAMONDS),
                                Card(Value.JACK, Suit.HEARTS),
                                Card(Value.JACK, Suit.SPADES),
                                Card(Value.JACK, Suit.DIAMONDS)
                        )
                ),
                set.quadruples
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

        assertEquals(emptyList<Cards>(), set.straights)
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

    @Test
    fun returnTheOrderedFlushesInACardSet() {
        val set = CardSet(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.TWO, Suit.DIAMONDS),
                Card(Value.ACE, Suit.DIAMONDS),
                Card(Value.FIVE, Suit.DIAMONDS),
                Card(Value.EIGHT, Suit.DIAMONDS),
                Card(Value.NINE, Suit.CLUBS)
        )

        val flush1 = listOf(
                Card(Value.ACE, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.EIGHT, Suit.DIAMONDS),
                Card(Value.FIVE, Suit.DIAMONDS)
        )
        val flush2 = listOf(
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.EIGHT, Suit.DIAMONDS),
                Card(Value.FIVE, Suit.DIAMONDS),
                Card(Value.TWO, Suit.DIAMONDS)
        )

        assertEquals(
                listOf(flush1, flush2),
                set.flushes
        )
    }

    @Test
    fun returnTheStraightFlushesInACardSet() {
        val set = CardSet(
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.SEVEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.SIX, Suit.SPADES),
                Card(Value.JACK, Suit.HEARTS)
        )

        assertEquals(
                listOf(
                        listOf(
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.SPADES),
                                Card(Value.SIX, Suit.SPADES),
                                Card(Value.FIVE, Suit.SPADES),
                                Card(Value.FOUR, Suit.SPADES)
                        )
                ),
                set.straightFlushes
        )
    }
    
    @Test
    fun returnTheFullHousesInACardSet() {
        val set = CardSet(
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.SEVEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.SIX, Suit.SPADES),
                Card(Value.JACK, Suit.HEARTS)
        )

        assertEquals(
                listOf(
                        listOf(
                                Card(Value.EIGHT, Suit.SPADES),
                                Card(Value.SEVEN, Suit.SPADES),
                                Card(Value.SIX, Suit.SPADES),
                                Card(Value.FIVE, Suit.SPADES),
                                Card(Value.FOUR, Suit.SPADES)
                        )
                ),
                set.straightFlushes
        )
    }
}
