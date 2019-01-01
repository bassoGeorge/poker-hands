package com.anishgeorge.poker.core

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
        assertTrue(Utils.areCardsStraightInAceHighRank(listOf(
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TEN, Suit.DIAMONDS),
                Card(Value.NINE, Suit.CLUBS),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.SEVEN, Suit.SPADES)
        )))

        assertFalse(Utils.areCardsStraightInAceHighRank(listOf(
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.SPADES),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.NINE, Suit.CLUBS),
                Card(Value.TEN, Suit.DIAMONDS),
                Card(Value.EIGHT, Suit.CLUBS)
        )))
    }

    @Test
    fun areCardsStraightInAceLowRankReturnsTrueForListOfCardsInStraightAceLowRankOrder() {
        assertTrue(Utils.areCardsStraightInAceLowRank(listOf(
                Card(Value.FIVE, Suit.HEARTS),
                Card(Value.FOUR, Suit.HEARTS),
                Card(Value.THREE, Suit.DIAMONDS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.ACE, Suit.CLUBS)
        )))

        assertFalse(Utils.areCardsStraightInAceLowRank(listOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.KING, Suit.SPADES),
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.NINE, Suit.DIAMONDS)
        )))
    }

    @Test
    fun totalRankShouldReturnTheNetRankOfTheGivenCards() {
        assertEquals(
                14,
                Utils.totalRank(listOf(Card(Value.ACE, Suit.CLUBS)))
        )
        assertEquals(
                17,
                Utils.totalRank(listOf(
                        Card(Value.ACE, Suit.SPADES),
                        Card(Value.THREE, Suit.HEARTS)
                ))
        )
    }

    @Test
    fun totalAceLowRankShouldReturnTheNetRankOfTheGivenCardsByAceLowRanking() {
        assertEquals(
                1,
                Utils.totalAceLowRank(listOf(Card(Value.ACE, Suit.CLUBS)))
        )
        assertEquals(
                4,
                Utils.totalAceLowRank(listOf(
                        Card(Value.ACE, Suit.SPADES),
                        Card(Value.THREE, Suit.HEARTS)
                ))
        )
    }

    @Test
    fun geometricProgressionWorks() {
        assertEquals(1, Utils.geometricProgression(1, 100)(1))
        assertEquals(100, Utils.geometricProgression(1, 100)(2))
        assertEquals(10000, Utils.geometricProgression(1, 100)(3))
    }

    @Test
    fun longRankComparatorFixesTheOverflowError() {
        assertEquals(1, Utils.longRankComparator.compare(4504030201L, 1000000505L))
        assertEquals(-1, Utils.longRankComparator.compare(1000000505L, 4504030201L))
        assertEquals(0, Utils.longRankComparator.compare(4504030201L, 4504030201L))
    }
}
