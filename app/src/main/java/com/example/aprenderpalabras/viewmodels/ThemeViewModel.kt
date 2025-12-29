package com.example.aprenderpalabras.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    var isDarkMode = mutableStateOf(false)
        private set

    fun toggleTheme() {
        isDarkMode.value = !isDarkMode.value
    }
}