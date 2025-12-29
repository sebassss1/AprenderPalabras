package com.example.aprenderpalabras.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.aprenderpalabras.viewmodels.ThemeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    fun getTitleForRoute(route: String?): String {
        return when (route) {
            "word_of_the_day" -> "Palabra del Día"
            "learned_words" -> "Palabras Aprendidas"
            "statistics" -> "Estadísticas"
            "game" -> "Minijuego"
            "settings" -> "Ajustes"
            else -> "Aprender Palabras"
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Palabra del día") },
                    selected = currentRoute == "word_of_the_day",
                    onClick = { 
                        navController.navigate("word_of_the_day")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Palabras Aprendidas") },
                    selected = currentRoute == "learned_words",
                    onClick = { 
                        navController.navigate("learned_words")
                        scope.launch { drawerState.close() }
                    }
                )
                 NavigationDrawerItem(
                    label = { Text("Estadísticas") },
                    selected = currentRoute == "statistics",
                    onClick = { 
                        navController.navigate("statistics")
                        scope.launch { drawerState.close() }
                    }
                )
                 NavigationDrawerItem(
                    label = { Text("Minijuego") },
                    selected = currentRoute == "game",
                    onClick = { 
                        navController.navigate("game")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Ajustes") },
                    selected = currentRoute == "settings",
                    onClick = { 
                        navController.navigate("settings")
                        scope.launch { drawerState.close() }
                     }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(getTitleForRoute(currentRoute)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController, 
                startDestination = "word_of_the_day",
                modifier = Modifier.padding(padding)
            ) {
                composable("word_of_the_day") {
                    WordOfTheDayScreen(navController = navController)
                }
                composable("learned_words") {
                    LearnedWordsScreen()
                }
                 composable("statistics") {
                    StatisticsScreen()
                }
                 composable("game") {
                    GameScreen()
                }
                composable("settings") {
                    SettingsScreen(themeViewModel = themeViewModel)
                }
            }
        }
    }
}
