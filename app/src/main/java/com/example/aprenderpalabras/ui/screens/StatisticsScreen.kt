package com.example.aprenderpalabras.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprenderpalabras.data.LearnedWordsRepository
import com.example.aprenderpalabras.data.StatisticsRepository

@Composable
fun StatisticsScreen() {
    val learnedWordsCount = LearnedWordsRepository.getLearnedWords().size
    val gamesWon by StatisticsRepository.gamesWon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Estadísticas de la aplicación", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Palabras aprendidas: $learnedWordsCount", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Partidas ganadas: $gamesWon", fontSize = 20.sp)
    }
}