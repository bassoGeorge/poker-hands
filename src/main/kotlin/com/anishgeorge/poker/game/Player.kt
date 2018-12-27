package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Hand

class Player(val name: String) : Dealable(2) {
    val isReady get() = cards.size == maxCardsAllowed

    private lateinit var _hand: Hand

    val hand: Hand
        get() = _hand

    fun figureBestHand(community: Community) {
        _hand = Hand.bestOf(cards + community.cards)
    }
}
