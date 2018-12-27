package com.anishgeorge.poker.game

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

    fun findWinners(): List<Player> {
        players.forEach { it.figureBestHand(community) }
        // NOTE: Players should not wait to figure best hand..., maybe during river and turn
        // NOTE*2: Why are players not aware of the community? Seems weird

        return players
                .groupBy { it.hand.rank }
                .toSortedMap(Comparator { rank1, rank2 -> rank2 - rank1 }) // Descending sort on keys (ranks)
                .values
                .first()
    }
}
