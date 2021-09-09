package com.valentyne.tiktaktoe.gameFragment

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val test: String = "You are in a game view model"

    var strategy: MutableList<Int> = mutableListOf()
    var player: Player = Player.PLAYER_1
}

enum class Player {
    PLAYER_1, PLAYER_2
}