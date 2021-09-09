package com.valentyne.tiktaktoe.gameFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val test: String = "You are in a game view model"
    private var player1Strategy: MutableList<Int> = mutableListOf()
    private var player2Strategy: MutableList<Int> = mutableListOf()
    val strategy = mapOf(Player.PLAYER_1 to player1Strategy, Player.PLAYER_2 to player2Strategy)

    private val _currentPlayerLiveData = MutableLiveData(Player.PLAYER_1)
    val currentPlayerLiveData: MutableLiveData<Player>
        get() = _currentPlayerLiveData

    private val _winnerLiveData = MutableLiveData<Player>()
    val winnerLiveData: MutableLiveData<Player>
        get() = _winnerLiveData

    fun makeMove(tileNumber: Int) {
        currentPlayerLiveData.value = if (currentPlayerLiveData.value == Player.PLAYER_1) {
            player1Strategy.add(tileNumber)
            Player.PLAYER_2
        } else {
            player2Strategy.add(tileNumber)
            Player.PLAYER_1
        }

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
                winnerLiveData.value = player
                return
            }
        }
    }
}

enum class Player {
    PLAYER_1, PLAYER_2
}