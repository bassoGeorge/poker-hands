package com.anishgeorge.poker.core

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
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.HIGH_CARD, hand.type)
        assertEquals(cardSet.cards, hand.participatingCards)
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
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.THREE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        ), hand.participatingCards)
    }

    @Test
    fun shouldGiveTwoPair() {
        val cardSet = CardSet(
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS)
        )
        val hand = Hand.bestOf(cardSet)
        assertEquals(HandType.TWO_PAIR, hand.type)
        assertEquals(listOf(
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.QUEEN, Suit.CLUBS),
                Card(Value.SEVEN, Suit.HEARTS),
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.FIVE, Suit.CLUBS)
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
                Card(Value.SEVEN, Suit.DIAMONDS),
                Card(Value.THREE, Suit.CLUBS)
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
                Card(Value.SEVEN, Suit.CLUBS),
                Card(Value.QUEEN, Suit.SPADES),
                Card(Value.TWO, Suit.CLUBS)

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
                Card(Value.JACK, Suit.DIAMONDS),
                Card(Value.QUEEN, Suit.SPADES)
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
                Card(Value.ACE, Suit.DIAMONDS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.NINE, Suit.SPADES),
                Card(Value.THREE, Suit.CLUBS)
        )

        assertEquals(
                listOf(
                        Card(Value.EIGHT, Suit.SPADES),
                        Card(Value.EIGHT, Suit.CLUBS),
                        Card(Value.ACE, Suit.DIAMONDS),
                        Card(Value.NINE, Suit.SPADES),
                        Card(Value.FIVE, Suit.CLUBS)
                ),
                pair.participatingCards
        )

        val twoPair = Hand.bestOf(
                Card(Value.EIGHT, Suit.SPADES),
                Card(Value.EIGHT, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.FIVE, Suit.CLUBS),
                Card(Value.TWO, Suit.CLUBS),
                Card(Value.ACE, Suit.SPADES),
                Card(Value.NINE, Suit.HEARTS)
        )

        assertEquals(
                listOf(
                        Card(Value.EIGHT, Suit.SPADES),
                        Card(Value.EIGHT, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS),
                        Card(Value.TWO, Suit.CLUBS),
                        Card(Value.ACE, Suit.SPADES)
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
        val aceHighStraight = Hand.bestOf(cardListOf("AC", "10S", "KD", "JC", "QH"))

        val expectedRank = 41413121110L

        assertEquals(expectedRank, aceHighStraight.rank)
    }

    @Test
    fun shouldComputeCorrectRankForFullAceLowHand() {
        val aceLowStraight = Hand.bestOf(cardListOf("AC", "2S", "3D", "4C", "5H"))
        val expectedRank = 40504030201L

        assertEquals(expectedRank, aceLowStraight.rank)
    }

    @Test
    fun shouldBreakTheTieForFullHouse() {
        val acesFullOfTwos = Hand.bestOf(cardListOf("AH", "AD", "AS", "2H", "2D"))
        val kingsFullOfJacks = Hand.bestOf(cardListOf("KH", "KD", "KS", "JH", "JD"))
        val kingsFullOfQueens = Hand.bestOf(cardListOf("KH", "KD", "KS", "QH", "QD"))

        assertTrue(acesFullOfTwos > kingsFullOfJacks)
        assertTrue(acesFullOfTwos > kingsFullOfQueens)
    }

    @Test
    fun shouldBreakTheTieForFourOfAKind() {
        val fourAces = Hand.bestOf(cardListOf("AH", "AD", "AS", "AC", "10D"))
        val fourKings = Hand.bestOf(cardListOf("KH", "KD", "KS", "KC", "AD"))
        val fourKingsSmallerKicker = Hand.bestOf(cardListOf("KH", "KD", "KS", "KC", "4D"))

        assertTrue(fourAces > fourKings)
        assertTrue(fourKings > fourKingsSmallerKicker)
    }

    @Test
    fun shouldBreakTheTieForFlushes() {
        val flush1 = Hand.bestOf(cardListOf("KH", "QH", "10H", "8H", "3H"))
        val flush2 = Hand.bestOf(cardListOf("KC", "QC", "10C", "8C", "3C"))
        val flush3 = Hand.bestOf(cardListOf("KD", "QD", "10D", "6D", "3D"))


        assertTrue(flush2 > flush3)
        assertEquals(flush1.rank, flush2.rank)

        val flush4 = Hand.bestOf(cardListOf("JD", "7D", "6D", "5D", "4D"))
        val flush5 = Hand.bestOf(cardListOf("JD", "10D", "5D", "4D", "3D"))

        assertTrue(flush5 > flush4)
    }

    @Test
    fun shouldCalculateTheCorrectRank() {
        val kHigh = Hand.bestOf(cardListOf("KH", "5C", "4D", "3C", "2H"))
        val expectedRank = 1305040302L
        assertEquals(expectedRank, kHigh.rank)
    }

    @Test
    fun shouldFigureOutCorrectRankingInTheBasicCaseOfTies() {
        val kHigh = Hand.bestOf(cardListOf("KH", "5C", "4D", "3C", "2H"))
        val jHigh = Hand.bestOf(cardListOf("JH", "7C", "4D", "3C", "2H"))
        val jSecondaryHigh = Hand.bestOf(cardListOf("JH", "6C", "5D", "3C", "2H"))

        assertTrue(jHigh > jSecondaryHigh)
        assertTrue(kHigh > jHigh)
    }

    @Test
    fun shouldBeAbleToCompareRanksCorrectlyWithLong() {
        val straight = Hand.bestOf(cardListOf("AC", "3D", "4H", "5C", "10S", "2D", "AH"))
        val onePair = Hand.bestOf(cardListOf("5H", "KC", "4H", "5C", "10S", "2D", "AH"))

        assertTrue(straight.rank > onePair.rank)
        assertTrue(straight > onePair)
    }

    @Test
    fun shouldBreakTheTieFor3OfAKind() {
        val trip1 = Hand.bestOf(cardListOf("KH", "KD", "KS", "8C", "3C"))
        val trip2 = Hand.bestOf(cardListOf("KH", "KD", "KS", "6D", "3D"))
        val trip3 = Hand.bestOf(cardListOf("KH", "KD", "KS", "8H", "2H"))

        assertTrue(trip1 > trip2)
        assertTrue(trip1 > trip3)
    }

    @Test
    fun shouldBreakTheTieFor2Pair() {
        val tp1 = Hand.bestOf(cardListOf("KH", "KD", "QS", "QC", "3C"))
        val tp2 = Hand.bestOf(cardListOf("KH", "KD", "JS", "JD", "3D"))
        val tp3 = Hand.bestOf(cardListOf("KH", "KD", "QS", "QH", "2H"))

        assertTrue(tp1 > tp2)
        assertTrue(tp1 > tp3)
    }


}
