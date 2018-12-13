package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Cards
import com.anishgeorge.poker.core.Suit
import com.anishgeorge.poker.core.Value
import java.util.*

class Deck (shuffledCards: Cards) : Collection<Card> {
    private val cards = Stack<Card>()

    init {
        cards.addAll(shuffledCards)
    }

    fun drawOne(): Card = cards.pop()

    fun draw(size: Int): Cards = (0 until size).map { cards.pop() }

    // Override collection methods
    override val size get() = cards.size

    override fun contains(element: Card) = cards.contains(element)

    override fun containsAll(elements: Collection<Card>) = cards.containsAll(elements)

    override fun isEmpty() = cards.isEmpty()

    override fun iterator() = cards.iterator()

    companion object {
        fun shuffled(): Deck {
            return Deck(getAllCards().values.flatten().shuffled())
        }

        internal fun getAllCards(): Map<Suit, Cards> {
            return Suit.values().map { suit ->
                Pair(suit, Value.values().map { Card(it, suit) })
            }.toMap()
        }
    }
}
