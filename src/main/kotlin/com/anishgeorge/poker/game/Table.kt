package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Hand

class Table(private val dealer: Dealer) {

    private var _isOpen = true
    val isOpen: Boolean
        get() = _isOpen

    val players get() = dealer.players
    val community get() = dealer.community

    fun join(player: Player): Table {
        dealer.addPlayer(player)
        return this
    }

    fun play(): Table {
        _isOpen = false
        dealer.dealPlayers()
        dealer.dealFlop()
        dealer.dealRiver()
        dealer.dealTurn()
        return this
    }

    // TODO: model ties
    fun findWinner(): Player {
        players.forEach {
            it.setHand(Hand.bestOf(community.cards + it.cards))
        }

        return players.sortedByDescending { it.hand }.first()
    }
}
