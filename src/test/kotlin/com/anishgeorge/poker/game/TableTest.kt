package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Hand
import com.anishgeorge.poker.core.HandType
import com.anishgeorge.poker.core.toCard
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TableTest {

    @MockK
    lateinit var deck: Deck

    @RelaxedMockK
    lateinit var community: Community

    @RelaxedMockK
    lateinit var burns: Burns

    @RelaxedMockK
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
    fun shouldCorrectlyIdentifyWinner() {
        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)
        val p3 = mockk<Player>(relaxed = true)

        every { community.cards } returns listOf("3H", "4C", "KS").map(String::toCard)
        every { p1.cards } returns listOf("KD", "3D").map(String::toCard)
        every { p2.cards } returns listOf("5D", "3C").map(String::toCard)
        every { p3.cards } returns listOf("5H", "AD").map(String::toCard)

        every { dealer.community } returns community
        every { dealer.players } returns listOf(p1, p2, p3)

        val table = Table(dealer)
        table.play()

        val winHandSlot = slot<Hand>()

        every { p1.setHand(capture(winHandSlot)) } returns Unit

        val winner = table.findWinner()

        assertEquals(p1, winner)
        assertEquals(HandType.TWO_PAIR, winHandSlot.captured.type)
    }
}
