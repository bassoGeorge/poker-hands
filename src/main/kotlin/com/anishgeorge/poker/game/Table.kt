package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Utils

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
        dealer.dealTurn()
        dealer.dealRiver()
        return this
    }

    fun findWinners(): List<Player> {
        return players
                .groupBy { it.hand.rank }
                .toSortedMap(Utils.longRankComparator.reversed()) // Descending sort on keys (ranks)
                .values
                .first()
    }
}
