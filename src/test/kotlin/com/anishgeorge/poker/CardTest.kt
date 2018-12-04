package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun shouldHaveBasicProperties() {
        val card = Card(Value.KING, Suit.HEARTS)
        assertEquals(Value.KING, card.value)
        assertEquals(Suit.HEARTS, card.suit)
    }

    @Test
    fun shouldBehaveLikeAValueObject() {
        assertTrue(Card(Value.KING, Suit.HEARTS) == Card(Value.KING, Suit.HEARTS))
        assertTrue(Card(Value.KING, Suit.HEARTS) != Card(Value.ACE, Suit.CLUBS))
    }

    @Test
    fun shouldGiveTheRankOfTheCard() {
        assertEquals(10, Card(Value.TEN, Suit.CLUBS).rank)
        assertEquals(14, Card(Value.ACE, Suit.CLUBS).rank)
    }

    @Test
    fun shouldBeAbleToFindTheSameRankedCards() {
        assertTrue(Card.areSameRanks(Card(Value.TWO, Suit.CLUBS), Card(Value.TWO, Suit.HEARTS)))
        assertFalse(Card.areSameRanks(Card(Value.TWO, Suit.CLUBS), Card(Value.ACE, Suit.HEARTS)))
    }

    @Test
    fun shouldBeAbleToFindTheSameRankedCardsInMoreThanTwo() {
        val card1 = Card(Value.TWO, Suit.CLUBS)
        val card2 = Card(Value.TWO, Suit.HEARTS)
        val card3 = Card(Value.TWO, Suit.SPADES)
        assertTrue(Card.areSameRanks(card1, card2, card3))
    }

    @Test
    fun shouldHaveImplicitRankBasedDescendingOrdering() {
        val card1 = Card(Value.TWO, Suit.CLUBS)
        val card2 = Card(Value.ACE, Suit.CLUBS)

        assertTrue(card2 > card1)
    }

}
