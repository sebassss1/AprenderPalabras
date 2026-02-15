package com.example.aprenderpalabras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aprenderpalabras.data.ThemePreferenceManager
import com.example.aprenderpalabras.ui.navigation.AppNavigation
import com.example.aprenderpalabras.ui.theme.AprenderPalabrasTheme
import com.example.aprenderpalabras.viewmodels.ThemeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val themePreferenceManager = ThemePreferenceManager(this)
        
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ThemeViewModel(themePreferenceManager) as T
                    }
                }
            )
            
            val isDarkMode by themeViewModel.isDarkMode
            AprenderPalabrasTheme(darkTheme = isDarkMode) {
                AppNavigation(themeViewModel = themeViewModel)
            }
        }
    }
}
