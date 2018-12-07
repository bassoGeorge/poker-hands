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

    @Test
    fun areCardsFlushSouldReturnTrueForFlushCards() {
        assertTrue(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.FOUR, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS)
        )))
        assertTrue(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.SPADES),
                Card(Value.NINE, Suit.SPADES),
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.TWO, Suit.SPADES)
        )))
        assertTrue(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.NINE, Suit.CLUBS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )))
        assertTrue(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.DIAMONDS),
                Card(Value.NINE, Suit.DIAMONDS),
                Card(Value.FOUR, Suit.DIAMONDS),
                Card(Value.TWO, Suit.DIAMONDS)
        )))
        assertFalse(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.NINE, Suit.DIAMONDS),
                Card(Value.FOUR, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS)
        )))
        assertFalse(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.TWO, Suit.HEARTS)
        )))
        assertFalse(Utils.areCardsFlush(listOf(
                Card(Value.ACE, Suit.SPADES),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.FOUR, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS)
        )))
    }
}
