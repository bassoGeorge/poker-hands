package com.anishgeorge.poker.game

class Player(val name: String) : Dealable(2) {
    val isReady get() = cards.size == maxCardsAllowed
}
