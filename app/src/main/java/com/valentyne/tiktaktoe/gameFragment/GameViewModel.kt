package com.valentyne.tiktaktoe.gameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val test: String = "You are in a game view model"
    val strategy = mapOf<Player, MutableList<Int>>(
        Player.PLAYER_1 to mutableListOf(),
        Player.PLAYER_2 to mutableListOf()
    )
    val score = mutableMapOf(Player.PLAYER_1 to 0, Player.PLAYER_2 to 0)
    var drawCount = 0

    private val _currentPlayerLiveData = MutableLiveData(Player.PLAYER_1)
    val currentPlayerLiveData: LiveData<Player>
        get() = _currentPlayerLiveData

    private val _winnerLiveData = MutableLiveData<Player>()
    val winnerLiveData: LiveData<Player>
        get() = _winnerLiveData

    private val _drawLiveData = MutableLiveData(false)
    val drawLiveData: LiveData<Boolean>
        get() = _drawLiveData

    private val _matchEndedLiveData = MutableLiveData(false)
    val matchEndedLiveData: LiveData<Boolean>
        get() = _matchEndedLiveData

    fun setEndMatch(value: Boolean) {
        _matchEndedLiveData.value = value
    }

    private fun setWinner(player: Player) {
        _winnerLiveData.value = player
    }

    fun newMatch() {
        _winnerLiveData.value = null
        strategy[Player.PLAYER_1]?.clear()
        strategy[Player.PLAYER_2]?.clear()
        setEndMatch(false)
    }

    private fun changeTurn() {
        _currentPlayerLiveData.value = if (currentPlayerLiveData.value == Player.PLAYER_1) {
            Player.PLAYER_2
        } else {
            Player.PLAYER_1
        }
    }

    fun makeMove(tileNumber: Int) {
        strategy[currentPlayerLiveData.value]?.add(tileNumber)
        changeTurn()
        checkDraw()
        checkWinner(Player.PLAYER_1)
        checkWinner(Player.PLAYER_2)
    }

    private fun checkDraw() {
        val tilesCovered = strategy[Player.PLAYER_1]!! + strategy[Player.PLAYER_2]!!

        if (tilesCovered.toSet() == setOf(1, 2, 3, 4, 5, 6, 7, 8, 9)) {
            drawCount++
            setEndMatch(true)
            _drawLiveData.value = true
        }
    }

    private fun checkWinner(player: Player) {
        val strategy = strategy[player]
        val winningStrategies = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(3, 6, 9),
            listOf(1, 5, 9),
            listOf(3, 5, 7)
        )

        for (winningStrategy in winningStrategies) {
            if (strategy!!.containsAll(winningStrategy)) {
                score[player] = score[player]!! + 1
                setWinner(player)
                setEndMatch(true)
                return
            }
        }
    }
}

enum class Player {
    PLAYER_1, PLAYER_2
}