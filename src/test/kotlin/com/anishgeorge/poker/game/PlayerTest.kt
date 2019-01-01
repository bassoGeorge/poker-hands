package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.*
import com.anishgeorge.poker.exceptions.MoreCardsThanAllowedException
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
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

    @Test
    fun shouldFigureOutTheirOwnHand() {
        val player = Player("Someone")
        player.deal("AS".toCard())
        player.deal("5C".toCard())

        val community = mockk<Community>()
        every { community.cards } returns cardListOf("KC", "AH", "5S")

        player.figureBestHand(community)

        assertEquals(HandType.TWO_PAIR, player.hand.type)
    }

    @Test
    fun shouldBeAbleToNotifyPlayerOfCommunityChanges() {
        val player = spyk(Player("player"))

        player.deal("KS".toCard(), "2H".toCard())
        val community = mockk<Community>()
        every { community.cards } returns cardListOf("3D", "2S", "5C")

        player.notifyCommunityChange(community)
        verify { player.figureBestHand(community) }
    }
}
