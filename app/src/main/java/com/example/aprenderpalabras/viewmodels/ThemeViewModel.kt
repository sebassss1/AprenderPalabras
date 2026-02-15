package com.example.aprenderpalabras.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aprenderpalabras.data.ThemePreferenceManager
import kotlinx.coroutines.launch

class ThemeViewModel(private val themePreferenceManager: ThemePreferenceManager) : ViewModel() {
    var isDarkMode = mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            themePreferenceManager.isDarkMode.collect {
                isDarkMode.value = it
            }
        }
    }

    fun toggleTheme() {
        val newValue = !isDarkMode.value
        isDarkMode.value = newValue
        viewModelScope.launch {
            themePreferenceManager.saveTheme(newValue)
        }
    }
}