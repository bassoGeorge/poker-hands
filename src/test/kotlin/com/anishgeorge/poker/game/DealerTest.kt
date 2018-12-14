package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Suit
import com.anishgeorge.poker.core.Value
import com.anishgeorge.poker.exceptions.NoPlayersPlayingException
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DealerTest {

    @MockK
    lateinit var community: Community

    @MockK
    lateinit var deck: Deck

    @MockK
    lateinit var burns: Burns

    @Test
    fun shouldBeAbleToAddPlayersToGame() {
        val game = Dealer(deck, community, burns)
        assertTrue(game.players.isEmpty())
        val p1 = mockk<Player>()
        val p2 = mockk<Player>()

        game.addPlayer(p1).addPlayer(p2)

        assertEquals(listOf(p1, p2), game.players)
    }

    @Test
    fun dealPlayersShouldThrowWhenLessThan2PlayersAreAddedToGame() {
        val game = Dealer(deck, community, burns)
        assertThrows(NoPlayersPlayingException::class.java) {
            game.dealPlayers()
        }
        val player = mockk<Player>()
        game.addPlayer(player)
        assertThrows(NoPlayersPlayingException::class.java) {
            game.dealPlayers()
        }
    }

    @Test
    fun dealPlayersShouldDealOut2CardsInRoundRobinFashionToEachPlayer() {
        val c1 = Card(Value.ACE, Suit.SPADES)
        val c2 = Card(Value.KING, Suit.CLUBS)
        val c3 = Card(Value.SEVEN, Suit.SPADES)
        val c4 = Card(Value.TWO, Suit.DIAMONDS)

        val deck = spyk(
                Deck(listOf(c1, c2, c3, c4))
        )

        val game = Dealer(deck, community, burns)

        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)

        game.addPlayer(p1).addPlayer(p2)

        game.dealPlayers()

        verify { p1.deal(c1) }
        verify { p1.deal(c3) }

        verify { p2.deal(c2) }
        verify { p2.deal(c4) }
    }
}
