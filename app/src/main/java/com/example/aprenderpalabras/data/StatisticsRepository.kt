package com.example.aprenderpalabras.data

import androidx.compose.runtime.mutableStateOf

object StatisticsRepository {
    var gamesWon = mutableStateOf(0)
        private set

    fun incrementGamesWon() {
        gamesWon.value++
    }
}