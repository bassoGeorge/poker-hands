package com.anishgeorge.poker.game

import com.anishgeorge.poker.exceptions.NoPlayersPlayingException

class Dealer(private val deck: Deck, val community: Community, val burns: Burns) {
    private val _players = mutableListOf<Player>()
    val players get() = _players

    fun dealPlayers() {
        if (players.size < 2) throw NoPlayersPlayingException()
        (0..1).forEach {
            players.forEach { it.deal(deck.drawOne()) }
        }
    }

    fun addPlayer(player: Player): Dealer {
        _players.add(player)
        return this
    }
}
