package com.anishgeorge.poker.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SuitTest {

    @Test
    fun shouldImplementAllTheSuites() {
        assertEquals(4, enumValues<Suit>().size)
        assertNotNull(Suit.SPADES)
        assertNotNull(Suit.CLUBS)
        assertNotNull(Suit.HEARTS)
        assertNotNull(Suit.DIAMONDS)
    }

    @Test
    fun shouldHaveCorrectShorts() {
        assertEquals("S", Suit.SPADES.short)
        assertEquals("C", Suit.CLUBS.short)
        assertEquals("H", Suit.HEARTS.short)
        assertEquals("D", Suit.DIAMONDS.short)
    }

    @Test
    fun shouldBeAbleToCreateFromShorts() {
        assertEquals(Suit.SPADES, Suit.fromShort("S"))
        assertEquals(Suit.CLUBS, Suit.fromShort("C"))
        assertEquals(Suit.HEARTS, Suit.fromShort("H"))
        assertEquals(Suit.DIAMONDS, Suit.fromShort("D"))
    }
}
