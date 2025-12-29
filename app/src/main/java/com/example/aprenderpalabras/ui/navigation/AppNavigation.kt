package com.example.aprenderpalabras.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aprenderpalabras.ui.screens.LoginScreen
import com.example.aprenderpalabras.ui.screens.MainScreen
import com.example.aprenderpalabras.ui.screens.RegistrationScreen
import com.example.aprenderpalabras.viewmodels.ThemeViewModel

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("registration") {
            RegistrationScreen(navController = navController)
        }
        composable("main") {
            MainScreen(themeViewModel = themeViewModel)
        }
    }
}