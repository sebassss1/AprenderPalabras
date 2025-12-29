package com.example.aprenderpalabras.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprenderpalabras.data.StatisticsRepository
import com.example.aprenderpalabras.data.Word
import com.example.aprenderpalabras.data.WordRepository

private const val TOTAL_ROUNDS = 5
private const val MIN_SCORE_TO_WIN = 3

private enum class GameState {
    PLAYING,
    FINISHED
}

@Composable
fun GameScreen() {
    var score by remember { mutableStateOf(0) }
    var currentRound by remember { mutableStateOf(1) }
    var currentWord by remember { mutableStateOf<Word?>(null) }
    var options by remember { mutableStateOf<List<String>>(emptyList()) }
    var gameState by remember { mutableStateOf(GameState.PLAYING) }
    var feedback by remember { mutableStateOf<String?>(null) }

    fun setupNewRound() {
        val allWords = WordRepository.italianWords + WordRepository.portugueseWords + WordRepository.frenchWords
        val correctWord = allWords.random()
        val incorrectDefinitions = allWords.filter { it != correctWord }.shuffled().take(3).map { it.definition }
        val allOptions = (incorrectDefinitions + correctWord.definition).shuffled()

        currentWord = correctWord
        options = allOptions
        feedback = null
    }

    fun checkAnswer(selectedDefinition: String) {
        if (selectedDefinition == currentWord?.definition) {
            score++
            feedback = "¡Correcto!"
        } else {
            feedback = "Incorrecto. La respuesta era: ${currentWord?.definition}"
        }

        if (currentRound == TOTAL_ROUNDS) {
            if (score >= MIN_SCORE_TO_WIN) {
                StatisticsRepository.incrementGamesWon()
            }
            gameState = GameState.FINISHED
        } else {
            currentRound++
        }
    }

    fun restartGame() {
        score = 0
        currentRound = 1
        gameState = GameState.PLAYING
        setupNewRound()
    }

    LaunchedEffect(key1 = Unit) {
        setupNewRound()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (gameState) {
            GameState.PLAYING -> {
                if (currentWord != null) {
                    Text(text = "Puntuación: $score / Ronda: $currentRound", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Asocia la palabra con su definición:", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = currentWord!!.word, fontSize = 28.sp)
                    Spacer(modifier = Modifier.height(16.dp))

                    options.forEach { definition ->
                        Button(
                            onClick = { if (feedback == null) checkAnswer(definition) },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        ) {
                            Text(definition)
                        }
                    }

                    feedback?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(it, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { setupNewRound() }) {
                            Text("Siguiente")
                        }
                    }
                } else {
                    Text("Cargando juego...")
                }
            }
            GameState.FINISHED -> {
                Text("¡Juego terminado!", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Puntuación final: $score de $TOTAL_ROUNDS", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                if (score >= MIN_SCORE_TO_WIN) {
                    Text("¡Has ganado!", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { restartGame() }) {
                    Text("Jugar de nuevo")
                }
            }
        }
    }
}