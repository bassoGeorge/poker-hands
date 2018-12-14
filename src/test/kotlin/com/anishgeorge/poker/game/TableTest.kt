package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.toCard
import io.mockk.every
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
class TableTest {

    @MockK
    lateinit var deck: Deck

    @RelaxedMockK
    lateinit var community: Community

    @RelaxedMockK
    lateinit var burns: Burns

    @Test
    fun shouldSetupCorrectly() {

        val deck = spyk(
                Deck(listOf(
                        "AH", "5H", "AC", "3S",
                        "KD", "10C", "AD", "7D",
                        "10S", "9C",
                        "2H", "5D"
                ).map(String::toCard))
        )

        val table = Table(
                deck = deck,
                community = community,
                burns = burns
        )

        table.dealer = spyk(table.dealer)

        val p1 = mockk<Player>(relaxed = true)
        val p2 = mockk<Player>(relaxed = true)

        table.join(p1).join(p2)
        verify { table.dealer.addPlayer(p1) }
        verify { table.dealer.addPlayer(p2) }

        assertTrue(table.isOpen)

        assertEquals(listOf(p1, p2), table.players)

        table.play()
        assertFalse(table.isOpen)

        verify { table.dealer.dealPlayers() }
        verify { table.dealer.dealFlop() }
        verify { table.dealer.dealRiver() }
        verify { table.dealer.dealTurn() }
    }
}
