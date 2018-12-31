package com.anishgeorge.poker.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.HIGH_CARD, hand.type)
        assertEquals(listOf(Card(Value.JACK, Suit.SPADES)), hand.participatingCards)
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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.ONE_PAIR, hand.type)
        assertEquals(listOf(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.DIAMONDS)
        ), hand.participatingCards)
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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.TWO_PAIR, hand.type)
        assertEquals(listOf(
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.DIAMONDS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveTwoPairEvenWhenThereAreMoreThanTwo() {
        val hand = Hand.bestOf(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.THREE, Suit.HEARTS),
                Card(Value.TWO, Suit.CLUBS)
        )
        assertEquals(HandType.TWO_PAIR, hand.type)
        assertEquals(listOf(
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.DIAMONDS)
        ), hand.participatingCards)
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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.THREE_OF_A_KIND, hand.type)
        assertEquals(listOf(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.SEVEN, Suit.CLUBS)
        ), hand.participatingCards)
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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.STRAIGHT, hand.type)
        assertEquals(listOf(
                Card(Value.KING, Suit.CLUBS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.TEN, Suit.HEARTS),
                Card(Value.NINE, Suit.DIAMONDS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveStraightForAceLowStraight() {
        val cardSet = CardSet(
                Card(Value.FIVE, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.THREE, Suit.DIAMONDS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.NINE, Suit.DIAMONDS)
        )
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.STRAIGHT, hand.type)
        assertEquals(listOf(
                Card(Value.FIVE, Suit.HEARTS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.THREE, Suit.DIAMONDS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.ACE, Suit.HEARTS)
        ), hand.participatingCards)
        assertTrue(hand.isAceLow)
    }

    @Test
    fun shouldGiveFlush() {
        val hand = Hand.bestOf(
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.KING, Suit.DIAMONDS),
                Card(Value.THREE, Suit.HEARTS),
                Card(Value.NINE, Suit.DIAMONDS)
        )

        assertEquals(HandType.FLUSH, hand.type)
        assertEquals(listOf(
                Card(Value.KING, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.NINE, Suit.DIAMONDS),
                Card(Value.SEVEN, Suit.DIAMONDS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveFullHouse() {
        val hand = Hand.bestOf(
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.KING, Suit.DIAMONDS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.CLUBS)
        )

        assertEquals(HandType.FULL_HOUSE, hand.type)
        assertEquals(listOf(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveFourOfAKind() {
        val hand = Hand.bestOf(
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.DIAMONDS)
        )

        assertEquals(HandType.FOUR_OF_A_KIND, hand.type)
        assertEquals(listOf(
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.DIAMONDS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveStraightFlush() {
        val hand = Hand.bestOf(
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.SEVEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.SIX, Suit.SPADES),
                Card(Value.JACK, Suit.HEARTS)
        )

        assertEquals(HandType.STRAIGHT_FLUSH, hand.type)
        assertEquals(listOf(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.SEVEN, Suit.SPADES),
                Card(Value.SIX, Suit.SPADES),
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.FOUR, Suit.SPADES)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveStraightFlushForAceLow() {
        val hand = Hand.bestOf(
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.ACE, Suit.SPADES),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.THREE, Suit.SPADES),
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.NINE, Suit.SPADES),
                Card(Value.JACK, Suit.HEARTS)
        )

        assertEquals(HandType.STRAIGHT_FLUSH, hand.type)
        assertEquals(listOf(
                Card(Value.FIVE, Suit.SPADES),
                Card(Value.FOUR, Suit.SPADES),
                Card(Value.THREE, Suit.SPADES),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.ACE, Suit.SPADES)
        ), hand.participatingCards)
        assertTrue(hand.isAceLow)
    }

    @Test
    fun shouldGiveRoyalFlush() {
        val hand = Hand.bestOf(
                Card(Value.TEN, Suit.SPADES),
                Card(Value.KING, Suit.SPADES),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.ACE, Suit.SPADES),
                Card(Value.JACK, Suit.SPADES),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS)
        )

        assertEquals(HandType.ROYAL_FLUSH, hand.type)
        assertEquals(listOf(
                Card(Value.ACE, Suit.SPADES),
                Card(Value.KING, Suit.SPADES),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.JACK, Suit.SPADES),
                Card(Value.TEN, Suit.SPADES)
        ), hand.participatingCards)
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
    fun shouldGiveFullHouseEvenWhenThereAreTwoTriples() {
        val hand = Hand.bestOf(
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.JACK, Suit.CLUBS)
        )

        assertEquals(HandType.FULL_HOUSE, hand.type)
        assertEquals(listOf(
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.JACK, Suit.HEARTS)
        ), hand.participatingCards)
    }

    @Deprecated("This test was added per TDD. The functionality is now checked with each hand type check tests")
    @Test
    fun shouldGiveTheParticipatingCards() {
        val pair = Hand.bestOf(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )

        assertEquals(
                listOf(Card(Value.EIGHT, Suit.SPADES),
                        Card(Value.EIGHT, Suit.CLUBS)),
                pair.participatingCards
        )

        val twoPair = Hand.bestOf(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )

        assertEquals(
                listOf(Card(Value.EIGHT, Suit.SPADES),
                        Card(Value.EIGHT, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS)
                ),
                twoPair.participatingCards
        )
    }

    @Test
    fun shouldHaveCorrectOrderWithinSameTypeOfHand() {
        val highPair = Hand.bestOf(
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.JACK, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS),
                Card(Value.ACE, Suit.HEARTS)
        )
        val lowPair = Hand.bestOf(
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.THREE, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS),
                Card(Value.ACE, Suit.HEARTS)
        )

        assertTrue(highPair > lowPair)
    }

    @Test
    fun handsAreOrderedAsPerHandType() {
        val orderedHands = listOf(
                Hand(HandType.HIGH_CARD, emptyList()),
                Hand(HandType.ONE_PAIR, emptyList()),
                Hand(HandType.TWO_PAIR, emptyList()),
                Hand(HandType.THREE_OF_A_KIND, emptyList()),
                Hand(HandType.STRAIGHT, emptyList()),
                Hand(HandType.FLUSH, emptyList()),
                Hand(HandType.FULL_HOUSE, emptyList()),
                Hand(HandType.FOUR_OF_A_KIND, emptyList()),
                Hand(HandType.STRAIGHT_FLUSH, emptyList()),
                Hand(HandType.ROYAL_FLUSH, emptyList())
        )

        val shuffledHands = orderedHands.shuffled()

        assertEquals(orderedHands, shuffledHands.sorted())

    }

    @Test
    fun shouldDecideItselfWhetherItIsAceLowRanking() {
        val aceHighStraight = Hand(HandType.STRAIGHT, listOf(
                Card(Value.ACE, Suit.HEARTS),
                Card(Value.KING, Suit.HEARTS),
                Card(Value.QUEEN, Suit.HEARTS),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.TEN, Suit.CLUBS)
        ))
        assertFalse(aceHighStraight.isAceLow)

        val aceLowStraight = Hand(HandType.STRAIGHT, listOf(
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.THREE, Suit.HEARTS),
                Card(Value.TWO, Suit.HEARTS),
                Card(Value.ACE, Suit.HEARTS)
        ))

        assertTrue(aceLowStraight.isAceLow)
    }

    @Test
    fun shouldComputeCorrectRankForFullHands() {
        val aceHighStraight = Hand.bestOf(listOf(
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.TEN, Suit.SPADES),
                Card(Value.KING, Suit.DIAMONDS),
                Card(Value.JACK, Suit.CLUBS),
                Card(Value.QUEEN, Suit.HEARTS)
        ))

        val typeRank = 4 * 10000
        val participatingCardsNetRank = 60 * 100

        assertEquals(typeRank + participatingCardsNetRank, aceHighStraight.rank)
    }

    @Test
    fun shouldComputeCorrectRankForFullAceLowHand() {
        val aceHighStraight = Hand.bestOf(listOf(
                Card(Value.ACE, Suit.CLUBS),
                Card(Value.TWO, Suit.SPADES),
                Card(Value.THREE, Suit.DIAMONDS),
                Card(Value.FOUR, Suit.CLUBS),
                Card(Value.FIVE, Suit.HEARTS)
        ))

        val typeRank = 4 * 10000
        val participatingCardsNetRank = 15 * 100

        assertEquals(typeRank + participatingCardsNetRank, aceHighStraight.rank)
    }

    @Disabled("We have to do some work to get our implementation in place")
    @Test
    fun shouldFindCorrectHandRankInTrickySituations() {
        val acesFullOfTwos = Hand.bestOf(cardListOf("AH", "AD", "AS", "2H", "2D"))
        val kingsFullOfJacks = Hand.bestOf(cardListOf("KH", "KD", "KS", "JH", "JD"))

        assertTrue(acesFullOfTwos > kingsFullOfJacks)
    }
}
