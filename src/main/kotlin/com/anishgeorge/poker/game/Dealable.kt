package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Cards

abstract class Dealable(protected val maxCardsAllowed: Int) {
    private val _cards = mutableListOf<Card>()
    val cards: Cards
        get() = _cards


    fun deal(vararg cards: Card) {
        if (_cards.size + cards.size > maxCardsAllowed) throw IllegalStateException("Can only be dealt $maxCardsAllowed cards")
        _cards.addAll(cards)
    }
}
