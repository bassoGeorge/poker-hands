package com.anishgeorge.poker.game

import com.anishgeorge.poker.core.Utils.repeat
import com.anishgeorge.poker.exceptions.TooFewPlayersPlayingException

class Dealer(private val deck: Deck, val community: Community, val burns: Burns) {
    private val _players = mutableListOf<Player>()

    val players: List<Player> get() = _players.toList()

    fun addPlayer(player: Player): Dealer {
        _players.add(player)
        return this
    }

    fun dealPlayers() {
        if (players.size < 2) throw TooFewPlayersPlayingException()
        repeat(2) {
            players.forEach(::dealOutACard)
        }
    }

    fun dealFlop() = burnOneAndDraw(3)

    fun dealRiver() = burnOneAndDraw(1)

    fun dealTurn() = burnOneAndDraw(1)

    private fun burnOneAndDraw(count: Int) {
        dealOutACard(burns)
        repeat(count) { dealOutACard(community) }
    }

    private fun dealOutACard(to: Dealable) = to.deal(deck.drawOne())
}
