package com.example.games.navigation

sealed class AppScreens(val route: String) {
    object GamesScreen: AppScreens("games_screen")
    object GameScreen: AppScreens("game_details_screen/{id}") {
        fun createRoute(id: Int) = "game_details_screen/$id"
    }
    object AboutScreen: AppScreens("about")


}