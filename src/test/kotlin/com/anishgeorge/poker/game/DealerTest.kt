package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.toCard
import com.anishgeorge.poker.exceptions.TooFewPlayersPlayingException
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DealerTest {

    @RelaxedMockK
    lateinit var community: Community

    @MockK
    lateinit var deck: Deck

    @RelaxedMockK
    lateinit var burns: Burns

    @Test
    fun shouldBeAbleToAddPlayersToGame() {
        val dealer = Dealer(deck, community, burns)
        assertTrue(dealer.players.isEmpty())
        val p1 = mockk<Player>()
        val p2 = mockk<Player>()

        dealer.addPlayer(p1).addPlayer(p2)

        assertEquals(listOf(p1, p2), dealer.players)
    }

    @Test
    fun shouldNotBeAbleToModifyPlayersFromOutside() {
        val dealer = Dealer(deck, community, burns)
        val p1 = mockk<Player>()
        val p2 = mockk<Player>()

        dealer.addPlayer(p1)

        assertEquals(1, dealer.players.size)
        val players = dealer.players as MutableList<Player>
        assertThrows(UnsupportedOperationException::class.java) {
            players.add(p2)
        }
        assertEquals(1, dealer.players.size)
    }

    @Test
    fun dealPlayersShouldThrowWhenLessThan2PlayersAreAddedToGame() {
        val dealer = Dealer(deck, community, burns)
        assertThrows(TooFewPlayersPlayingException::class.java) {
            dealer.dealPlayers()
        }
        val player = mockk<Player>()
        dealer.addPlayer(player)
        assertThrows(TooFewPlayersPlayingException::class.java) {
            dealer.dealPlayers()
        }
    }

    @Test
    fun dealPlayersShouldDealOut2CardsInRoundRobinFashionToEachPlayer() {
        val c1 = "AS".toCard()
        val c2 = "KC".toCard()
        val c3 = "7S".toCard()
        val c4 = "2D".toCard()

        val deck = spyk(
                Deck(listOf(c1, c2, c3, c4))
        )

        val dealer = Dealer(deck, community, burns)

        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)

        dealer.addPlayer(p1).addPlayer(p2)

        dealer.dealPlayers()

        verify { p1.deal(c1) }
        verify { p1.deal(c3) }

        verify { p2.deal(c2) }
        verify { p2.deal(c4) }
    }

    @Test
    fun dealFlopShouldBurn1CardAndDeal3CardsToCommunity() {
        val pack = listOf("AS", "KC", "7S", "2D").map(String::toCard)
        val deck = spyk(Deck(pack))

        val dealer = Dealer(deck, community, burns)
        dealer.dealFlop()

        verify { burns.deal(pack[0]) }
        verify { community.deal(pack[1]) }
        verify { community.deal(pack[2]) }
        verify { community.deal(pack[3]) }

        verify(exactly = 4) { deck.drawOne() }
    }

    @Test
    fun dealRiverShouldBurn1CardAndDeal1CardToCommunity() {
        val pack = listOf("AS", "KC").map(String::toCard)
        val deck = spyk(Deck(pack))

        val dealer = Dealer(deck, community, burns)
        dealer.dealRiver()

        verify { burns.deal(pack[0]) }
        verify { community.deal(pack[1]) }

        verify(exactly = 2) { deck.drawOne() }
    }

    @Test
    fun dealTurnShouldBurn1CardAndDeal1CardToCommunity() {
        val pack = listOf("AS", "KC").map(String::toCard)
        val deck = spyk(Deck(pack))

        val dealer = Dealer(deck, community, burns)
        dealer.dealTurn()

        verify { burns.deal(pack[0]) }
        verify { community.deal(pack[1]) }

        verify(exactly = 2) { deck.drawOne() }
    }

    @Test
    fun dealingTheFlopRiverOrTurnShouldAlwaysNotifyThePlayersOfTheChangeInCommunity() {
        val dealer = Dealer(Deck.shuffled(), community, burns)
        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)
        dealer.addPlayer(p1).addPlayer(p2)

        dealer.dealFlop()
        verify { p1.notifyCommunityChange(dealer.community) }
        verify { p2.notifyCommunityChange(dealer.community) }

        dealer.dealTurn()
        verify { p1.notifyCommunityChange(dealer.community) }
        verify { p2.notifyCommunityChange(dealer.community) }

        dealer.dealRiver()
        verify { p1.notifyCommunityChange(dealer.community) }
        verify { p2.notifyCommunityChange(dealer.community) }
    }
}
