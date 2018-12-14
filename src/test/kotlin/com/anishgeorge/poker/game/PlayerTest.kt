package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Suit
import com.anishgeorge.poker.core.Value
import com.anishgeorge.poker.exceptions.MoreCardsThanAllowedException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun shouldBeAbleToCreatePlayersWithoutCards() {
        val player1 = Player("Modi")
        assertEquals("Modi", player1.name)
        assertTrue(player1.cards.isEmpty())
    }

    @Test
    fun aPlayerIsNotReadyTillAllCardsAreDealt() {
        val player = Player("Jack")
        assertFalse(player.isReady)
    }

    @Test
    fun aPlayerIsReadyOnlyWhenAllCardsAreDealt() {
        val player = Player("Someone")
        player.deal(Card(Value.ACE, Suit.CLUBS), Card(Value.NINE, Suit.HEARTS))
        assertTrue(player.isReady)
    }

    @Test
    fun shouldBeUnableToDealMoreThan2CardsToAPlayer() {
        val player = Player("Someone")
        assertThrows(MoreCardsThanAllowedException::class.java) {
            player.deal(
                    Card(Value.ACE, Suit.CLUBS),
                    Card(Value.NINE, Suit.HEARTS),
                    Card(Value.EIGHT, Suit.HEARTS)
            )
        }
    }
}
