package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class HandUtilsTest {

    @Test
    fun returnsHighCardFromGivenHand3() {
        val hand = TreeSet<Card>()
        hand.add(Card(Value.TEN, Suit.HEARTS))
        hand.add(Card(Value.TWO, Suit.DIAMONDS))
        hand.add(Card(Value.ACE, Suit.SPADES))
        hand.add(Card(Value.TEN, Suit.CLUBS))
        hand.add(Card(Value.KING, Suit.HEARTS))

        assertEquals(Card(Value.ACE, Suit.SPADES), HandUtils.highCard(hand))
    }

}
