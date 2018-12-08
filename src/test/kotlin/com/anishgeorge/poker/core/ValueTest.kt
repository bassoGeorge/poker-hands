package com.anishgeorge.poker.core

import com.anishgeorge.poker.core.Value
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ValueTest {
    @Test fun shouldHaveAllTheEnums() {
        assertEquals(13, enumValues<Value>().size)

        assertNotNull(Value.ACE)
        assertNotNull(Value.KING)
        assertNotNull(Value.QUEEN)
        assertNotNull(Value.JACK)
        assertNotNull(Value.TEN)
        assertNotNull(Value.NINE)
        assertNotNull(Value.EIGHT)
        assertNotNull(Value.SEVEN)
        assertNotNull(Value.SIX)
        assertNotNull(Value.FIVE)
        assertNotNull(Value.FOUR)
        assertNotNull(Value.THREE)
        assertNotNull(Value.TWO)
    }

    @Test fun shouldAdequateRanks() {
        assertEquals(14, Value.ACE.rank)
        assertEquals(13, Value.KING.rank)
        assertEquals(12, Value.QUEEN.rank)
        assertEquals(11, Value.JACK.rank)
        assertEquals(10, Value.TEN.rank)
        assertEquals(9, Value.NINE.rank)
        assertEquals(8, Value.EIGHT.rank)
        assertEquals(7, Value.SEVEN.rank)
        assertEquals(6, Value.SIX.rank)
        assertEquals(5, Value.FIVE.rank)
        assertEquals(4, Value.FOUR.rank)
        assertEquals(3, Value.THREE.rank)
        assertEquals(2, Value.TWO.rank)
    }
}
