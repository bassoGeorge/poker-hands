package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Cards
import com.anishgeorge.poker.core.Suit
import com.anishgeorge.poker.core.Value
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun returnsADeckOf52Cards() {
        val deck = Deck.shuffled()
        assertEquals(52, deck.size)
    }

    @Test
    fun shouldBuildOutBasicListOfCards() {
        val allCards = Deck.getAllCards()
        for (suit in Suit.values()) {
            for (value in Value.values()) {
                assertTrue(allCards[suit]?.contains(Card(value, suit)) ?: false)
            }
        }
    }

    // TODO. test that shuffle is being called

    @Test
    fun shouldBeAbleToDrawACard() {
        val deck = Deck.shuffled()
        val card: Card = deck.draw()
        assertEquals(51, deck.size)
    }

    @Test
    fun shouldBeAbleToDrawMultipleCards() {
        val deck = Deck.shuffled()
        val cards: Cards = deck.draw(3)
        assertEquals(3, cards.size)
        assertEquals(52 - 3, deck.size)
    }
}
