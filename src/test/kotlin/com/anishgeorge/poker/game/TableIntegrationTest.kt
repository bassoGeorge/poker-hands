package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Cards
import com.anishgeorge.poker.core.HandType
import com.anishgeorge.poker.core.cardListOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TableIntegrationTest {

    // We will test with three players,
    // so our deck has to be shuffled so that we split thrice and den burn and draw community

    private lateinit var p1: Player
    private lateinit var p2: Player
    private lateinit var p3: Player

    @BeforeEach
    internal fun setUp() {
        p1 = Player("p1")
        p2 = Player("p2")
        p3 = Player("p3")
    }

    private fun playATableWithCards(cards: Cards): Table {
        val deck = Deck(cards)
        val dealer = Dealer(deck, Community(), Burns())

        return Table(dealer)
                .join(p1)
                .join(p2)
                .join(p3)
                .play()
    }

    @Test
    fun essentialGameWithOneWinner() {
        // full house > straight > two pair
        val cards = cardListOf(

                // p1 | p2 | p3
                "4C", "7D", "6H",
                "5D", "8D", "KC",

                "2H",               // burn 1
                "4H", "5C", "6S",   // flop
                "3H",               // burn 2
                "6D",               // turn
                "2D",               // burn 3,
                "KS"                // river
        )

        val table = playATableWithCards(cards)

        // Code smell, unless we find winners, players do not compute their hands... weird
        val winners = table.findWinners()

        assertEquals(HandType.TWO_PAIR, p1.hand.type)
        assertEquals(HandType.STRAIGHT, p2.hand.type)
        assertEquals(HandType.FULL_HOUSE, p3.hand.type)

        assertEquals(listOf(p3), winners)

    }

    @Test
    fun oneGameWinnerUsingAceLowRanking() {
        // straight > 3 of a kind > 1 pair
        val cards = cardListOf(

                // p1 | p2 | p3
                "AC", "AD", "5H",
                "3D", "AS", "KC",

                "8H",               // burn 1
                "4H", "5C", "10S",  // flop
                "8D",               // burn 2
                "2D",               // turn
                "8S",               // burn 3,
                "AH"                // river
        )

        val table = playATableWithCards(cards)

        // Code smell, unless we find winners, players do not compute their hands... weird
        val winners = table.findWinners()

        assertEquals(HandType.STRAIGHT, p1.hand.type)
        assertEquals(HandType.THREE_OF_A_KIND, p2.hand.type)
        assertEquals(HandType.ONE_PAIR, p3.hand.type)

        println(p1.hand.rank)
        println(p3.hand.rank)
        assertEquals(listOf(p1), winners)

    }
}
