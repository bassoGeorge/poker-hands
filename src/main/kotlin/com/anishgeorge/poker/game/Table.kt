package com.anishgeorge.poker.game

class Table(val deck: Deck, val community: Community, val burns: Burns) {
    internal var dealer = Dealer(deck, community, burns)

    private var _isOpen = true
    val isOpen: Boolean
        get() = _isOpen

    val players get() = dealer.players

    fun join(player: Player): Table {
        dealer.addPlayer(player)
        return this
    }

    fun play() {
        _isOpen = false
        dealer.dealPlayers()
        dealer.dealFlop()
        dealer.dealRiver()
        dealer.dealTurn()
    }
}
