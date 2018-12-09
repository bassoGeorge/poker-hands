package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Suit
import com.anishgeorge.poker.core.Value
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CommunityTest {

    @Test
    fun shouldBeAbleToDealToCommunity() {
        val community = Community()
        val card = Card(Value.ACE, Suit.CLUBS)
        community.deal(card)
        assertEquals(listOf(card), community.cards)
    }

    @Test
    fun shouldBeUnableToDealMoreThan2CardsToAPlayer() {
        val community = Community()
        community.deal(
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.NINE, Suit.HEARTS),
                Card(Value.EIGHT, Suit.HEARTS),
                Card(Value.TEN, Suit.DIAMONDS),
                Card(Value.EIGHT, Suit.CLUBS)
        )

        assertThrows(IllegalStateException::class.java) {
            community.deal(Card(Value.FOUR, Suit.SPADES))
        }
    }
}
