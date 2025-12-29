package com.example.aprenderpalabras.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aprenderpalabras.data.LearnedWordsRepository
import com.example.aprenderpalabras.data.Word
import com.example.aprenderpalabras.data.WordRepository

@Composable
fun WordOfTheDayScreen(navController: NavController) {
    var currentWord by remember { mutableStateOf<Word?>(null) }
    var selectedLanguage by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("Italiano", "Português", "Français")

    fun getNewWord(language: String) {
        val wordList = when (language) {
            "Italiano" -> WordRepository.italianWords
            "Português" -> WordRepository.portugueseWords
            "Français" -> WordRepository.frenchWords
            else -> emptyList()
        }
        currentWord = wordList.randomOrNull()
        selectedLanguage = language
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentWord == null) {
            Text(text = "Elige un idioma para empezar", fontSize = 20.sp)
        } else {
            Text(text = "Palabra del Día", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            currentWord?.let {
                Text(text = it.word, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.definition, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "En español: ${it.spanishDefinition}", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box {
            TextButton(onClick = { expanded = true }) {
                Text(if (selectedLanguage.isEmpty()) "Seleccionar idioma" else selectedLanguage)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    DropdownMenuItem(text = {Text(language)}, onClick = { 
                        getNewWord(language)
                        expanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { if(selectedLanguage.isNotEmpty()) getNewWord(selectedLanguage) }) {
                Text("Siguiente palabra")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { currentWord?.let { LearnedWordsRepository.addLearnedWord(it) } }) {
                Text("Aprendida")
            }
        }
    }
}