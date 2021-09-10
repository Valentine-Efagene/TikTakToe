package com.valentyne.tiktaktoe.gameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val test: String = "You are in a game view model"
    val strategy = mapOf(Player.PLAYER_1 to mutableListOf(), Player.PLAYER_2 to mutableListOf<Int>())
    val score = mutableMapOf(Player.PLAYER_1 to 0, Player.PLAYER_2 to 0)

    private val _currentPlayerLiveData = MutableLiveData(Player.PLAYER_1)
    val currentPlayerLiveData: LiveData<Player>
        get() = _currentPlayerLiveData

    private val _winnerLiveData = MutableLiveData<Player>()
    val winnerLiveData: LiveData<Player>
        get() = _winnerLiveData

    /*private fun setCurrentPlayer(player: Player) {
        _currentPlayerLiveData.value = player
    }*/

    private fun setWinner(player: Player) {
        _winnerLiveData.value = player
    }

    fun newMatch() {
        _winnerLiveData.value = null
        strategy[Player.PLAYER_1]?.clear()
        strategy[Player.PLAYER_2]?.clear()
    }

    fun changeTurn() {
        _currentPlayerLiveData.value = if (currentPlayerLiveData.value == Player.PLAYER_1) {
            Player.PLAYER_2
        } else {
            Player.PLAYER_1
        }
    }

    fun makeMove(tileNumber: Int) {
        strategy[currentPlayerLiveData.value]?.add(tileNumber)
        changeTurn()
        checkWinner(Player.PLAYER_1)
        checkWinner(Player.PLAYER_2)
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
                return
            }
        }
    }
}

enum class Player {
    PLAYER_1, PLAYER_2
}