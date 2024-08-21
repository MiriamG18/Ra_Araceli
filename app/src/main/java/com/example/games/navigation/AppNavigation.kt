package com.example.games.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.games.screens.AboutScreen
import com.example.games.screens.MoviesScreen
import com.example.games.screens.GameDetailsScreen
import com.example.games.viewmodel.MoviesViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.GamesScreen.route) {

        val viewModel = MoviesViewModel()

        composable(route = AppScreens.GamesScreen.route) {
            MoviesScreen(navController, viewModel)
        }

        composable(
            route = AppScreens.GameScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            id?.let {
                GameDetailsScreen(navController, id, viewModel)
            }
        }

        composable(route = AppScreens.AboutScreen.route) {
            AboutScreen(navController)
        }
    }
}