package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SuitTest {

    @Test fun shouldImplementAllTheSuites() {
        assertEquals(4, enumValues<Suit>().size)
        assertNotNull(Suit.SPADES)
        assertNotNull(Suit.CLUBS)
        assertNotNull(Suit.HEARTS)
        assertNotNull(Suit.DIAMONDS)
    }
}
