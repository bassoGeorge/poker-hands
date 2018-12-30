package com.anishgeorge.poker.core

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
    fun shouldGiveTheAceLowRankOfTheCard() {
        assertEquals(10, Card(Value.TEN, Suit.CLUBS).aceLowRank)
        assertEquals(1, Card(Value.ACE, Suit.CLUBS).aceLowRank)
    }


    @Test
    fun shouldHaveImplicitRankBasedDescendingOrdering() {
        val card1 = Card(Value.TWO, Suit.CLUBS)
        val card2 = Card(Value.ACE, Suit.CLUBS)

        assertTrue(card2 > card1)
    }


    @Test
    fun shouldBeAbleToCreateFromShort() {
        assertEquals(Card(Value.TWO, Suit.CLUBS), Card.fromShort("2C"))
        assertEquals(Card(Value.TEN, Suit.HEARTS), Card.fromShort("10H"))
        assertEquals(Card(Value.ACE, Suit.DIAMONDS), Card.fromShort("AD"))
    }

    @Test
    fun shouldBeAbleToCreateFromShortUsingString() {
        assertEquals(Card(Value.TWO, Suit.CLUBS), "2C".toCard())
        assertEquals(Card(Value.TEN, Suit.HEARTS), "10H".toCard())
        assertEquals(Card(Value.ACE, Suit.DIAMONDS), "AD".toCard())
    }

    @Test
    fun shouldBeAbleToCreateListOfCardsUsingStringToCard() {
        assertEquals(
                listOf(Card(Value.TWO, Suit.CLUBS), Card(Value.TEN, Suit.HEARTS)),
                cardListOf("2C", "10H")
        )
    }

}
