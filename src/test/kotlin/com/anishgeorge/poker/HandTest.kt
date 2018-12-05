package com.anishgeorge.poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HandTest {

    @Test
    fun shouldGiveHighCard() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.JACK, Suit.SPADES),
                Card(Value.EIGHT, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )
        val hand = Hand.findBestHandOf(cardSet)
        assertEquals(HandType.HIGH_CARD, hand.type)
    }

    @Test
    fun shouldGiveOnePair() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )
        val hand = Hand.findBestHandOf(cardSet)
        assertEquals(HandType.ONE_PAIR, hand.type)
    }

    @Test
    fun shouldGiveTwoPair() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )
        val hand = Hand.findBestHandOf(cardSet)
        assertEquals(HandType.TWO_PAIR, hand.type)
    }

    @Test
    fun shouldGiveThreeOfAKind() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.SEVEN, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )
        val hand = Hand.findBestHandOf(cardSet)
        assertEquals(HandType.THREE_OF_A_KIND, hand.type)
    }

    @Test
    fun handsHaveCorrectOrder() {
        val twoPairs = Hand(HandType.TWO_PAIR)
        val straight = Hand(HandType.STRAIGHT)
        val highCard = Hand(HandType.HIGH_CARD)
        val flush = Hand(HandType.FLUSH)
        val threeOfAKind = Hand(HandType.THREE_OF_A_KIND)

        val hands = listOf(twoPairs, straight, highCard, flush, threeOfAKind)
        assertEquals(
                listOf(highCard, twoPairs, threeOfAKind, straight, flush),
                hands.sorted()
        )
    }
}
