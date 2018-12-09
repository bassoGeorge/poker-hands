package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Card
import com.anishgeorge.poker.core.Cards

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards: Cards
        get() = _cards

    val isReady get() = cards.size == 2

    fun deal(vararg cards: Card) {
        if (_cards.size + cards.size > 2) throw IllegalStateException("A player can only be dealt 2 cards")
        _cards.addAll(cards)
    }
}
