package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UtilsTest {

    @Test
    fun uniqueCardsReturnsCardsUniqueInRankToTheGivenList() {
        val cardList = listOf(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.NINE, Suit.SPADES)
        )

        val uniques = Utils.uniqueCards(cardList)
        assertEquals(listOf(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.TEN, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS)
        ), uniques)
        assertEquals(6, cardList.size)
    }

    @Test
    fun areCardsStraightInRankReturnsTrueForListOfCardsInStraightRankOrder() {
        assertTrue(Utils.areCardsStraightInRank(listOf(
         Card(Value.QUEEN, Suit.HEARTS),
         Card(Value.JACK, Suit.HEARTS),
         Card(Value.TEN, Suit.DIAMONDS),
         Card(Value.NINE, Suit.CLUBS),
         Card(Value.EIGHT, Suit.CLUBS),
         Card(Value.SEVEN, Suit.SPADES)
        )))

        assertFalse(Utils.areCardsStraightInRank(listOf(
         Card(Value.QUEEN, Suit.HEARTS),
         Card(Value.SEVEN, Suit.SPADES),
         Card(Value.JACK, Suit.HEARTS),
         Card(Value.NINE, Suit.CLUBS),
         Card(Value.TEN, Suit.DIAMONDS),
         Card(Value.EIGHT, Suit.CLUBS)
        )))
    }

    @Test
    fun totalRankShouldReturnTheNetRankOfTheGivenCards() {
        assertEquals(
                13,
                Utils.totalRank(listOf(Card(Value.KING, Suit.CLUBS)))
        )
        assertEquals(
                5,
                Utils.totalRank(listOf(
                        Card(Value.TWO, Suit.SPADES),
                        Card(Value.THREE, Suit.HEARTS)
                ))
        )
    }
}
