package com.example.aprenderpalabras.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aprenderpalabras.data.LearnedWordsRepository

@Composable
fun LearnedWordsScreen() {
    val learnedWords = LearnedWordsRepository.getLearnedWords()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (learnedWords.isEmpty()) {
            item {
                Text("Aún no has aprendido ninguna palabra.")
            }
        } else {
            items(learnedWords) { word ->
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(word.word, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(word.definition)
                     Text("En español: ${word.spanishDefinition}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}