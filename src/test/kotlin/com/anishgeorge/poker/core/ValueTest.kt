package com.anishgeorge.poker.core

import com.anishgeorge.poker.core.Value
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ValueTest {
    @Test
    fun shouldHaveAllTheEnums() {
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

    @Test
    fun shouldHaveCorrectRanks() {
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

    @Test
    fun shouldHaveCorrectAceLowRanks() {
        assertEquals(13, Value.KING.aceLowRank)
        assertEquals(12, Value.QUEEN.aceLowRank)
        assertEquals(11, Value.JACK.aceLowRank)
        assertEquals(10, Value.TEN.aceLowRank)
        assertEquals(9, Value.NINE.aceLowRank)
        assertEquals(8, Value.EIGHT.aceLowRank)
        assertEquals(7, Value.SEVEN.aceLowRank)
        assertEquals(6, Value.SIX.aceLowRank)
        assertEquals(5, Value.FIVE.aceLowRank)
        assertEquals(4, Value.FOUR.aceLowRank)
        assertEquals(3, Value.THREE.aceLowRank)
        assertEquals(2, Value.TWO.aceLowRank)
        assertEquals(1, Value.ACE.aceLowRank)
    }

    @Test
    fun shouldBeAbleToCreateFromShortValue() {
        assertEquals(Value.ACE, Value.fromShort("A"))
        assertEquals(Value.KING, Value.fromShort("K"))
        assertEquals(Value.QUEEN, Value.fromShort("Q"))
        assertEquals(Value.JACK, Value.fromShort("J"))
        assertEquals(Value.TEN, Value.fromShort(10))
        assertEquals(Value.NINE, Value.fromShort(9))
        assertEquals(Value.EIGHT, Value.fromShort(8))
        assertEquals(Value.SEVEN, Value.fromShort(7))
        assertEquals(Value.SIX, Value.fromShort(6))
        assertEquals(Value.FIVE, Value.fromShort(5))
        assertEquals(Value.FOUR, Value.fromShort(4))
        assertEquals(Value.THREE, Value.fromShort(3))
        assertEquals(Value.TWO, Value.fromShort(2))
    }

    @Test
    fun shouldHaveCorrectShorts() {
        assertEquals("A", Value.ACE.short)
        assertEquals("K", Value.KING.short)
        assertEquals("Q", Value.QUEEN.short)
        assertEquals("J", Value.JACK.short)
        assertEquals("10", Value.TEN.short)
        assertEquals("9", Value.NINE.short)
        assertEquals("8", Value.EIGHT.short)
        assertEquals("7", Value.SEVEN.short)
        assertEquals("6", Value.SIX.short)
        assertEquals("5", Value.FIVE.short)
        assertEquals("4", Value.FOUR.short)
        assertEquals("3", Value.THREE.short)
        assertEquals("2", Value.TWO.short)
    }
}
