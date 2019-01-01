package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Hand
import com.anishgeorge.poker.core.HandType
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TableTest {

    @RelaxedMockK
    private
    lateinit var community: Community

    @RelaxedMockK
    private
    lateinit var dealer: Dealer

    @Test
    fun shouldSetupCorrectly() {

        val table = Table(dealer)

        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)

        table.join(p1).join(p2)

        verify { dealer.addPlayer(p1) }
        verify { dealer.addPlayer(p2) }

        assertTrue(table.isOpen)

        assertEquals(dealer.players, table.players)

        table.play()

        assertFalse(table.isOpen)
        verify { dealer.dealPlayers() }
        verify { dealer.dealFlop() }
        verify { dealer.dealRiver() }
        verify { dealer.dealTurn() }
    }

    @Test
    fun shouldCorrectlyIdentifyWinners() {
        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)
        val p3 = mockk<Player>(relaxed = true)

        every { p1.hand } returns Hand(HandType.ONE_PAIR, emptyList())
        every { p2.hand } returns Hand(HandType.THREE_OF_A_KIND, emptyList())
        every { p3.hand } returns Hand(HandType.HIGH_CARD, emptyList())

        every { dealer.community } returns community
        every { dealer.players } returns listOf(p1, p2, p3)

        val table = Table(dealer)
        table.play()

        val winners = table.findWinners()

        assertEquals(listOf(p2), winners)
    }

    @Test
    fun shouldCorrectlyIdentifyWinnersWhoAreTied() {
        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)
        val p3 = mockk<Player>(relaxed = true)

        every { p1.hand } returns Hand(HandType.TWO_PAIR, emptyList())
        every { p2.hand } returns Hand(HandType.TWO_PAIR, emptyList())
        every { p3.hand } returns Hand(HandType.HIGH_CARD, emptyList())

        every { dealer.community } returns community
        every { dealer.players } returns listOf(p1, p2, p3)

        val table = Table(dealer)
        table.play()

        val winners = table.findWinners()

        assertEquals(listOf(p1, p2), winners)
    }
}
