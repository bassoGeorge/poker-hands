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
    fun shouldGiveStraight() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.KING, Suit.CLUBS),
                Card(Value.TEN, Suit.HEARTS),
                Card(Value.NINE, Suit.DIAMONDS)
        )
        val hand = Hand.findBestHandOf(cardSet)
        assertEquals(HandType.STRAIGHT, hand.type)
    }

    @Test
    fun shouldHaveCorrectOrder() {
        val twoPairs = Hand(HandType.TWO_PAIR, emptyList())
        val straight = Hand(HandType.STRAIGHT, emptyList())
        val highCard = Hand(HandType.HIGH_CARD, emptyList())
        val flush = Hand(HandType.FLUSH, emptyList())
        val threeOfAKind = Hand(HandType.THREE_OF_A_KIND, emptyList())

        val hands = listOf(twoPairs, straight, highCard, flush, threeOfAKind)
        assertEquals(
                listOf(highCard, twoPairs, threeOfAKind, straight, flush),
                hands.sorted()
        )
    }

    @Test
    fun shouldGiveTheParticipatingCards() {
        val pair = Hand.findBestHandOf(CardSet(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        ))

        assertEquals(
                listOf( Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS)),
                pair.participatingCards
        )

        val twoPair = Hand.findBestHandOf(CardSet(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        ))

        assertEquals(
                listOf( Card(Value.EIGHT, Suit.SPADES),
                        Card(Value.EIGHT, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS)
                        ),
                twoPair.participatingCards
        )
    }
}
