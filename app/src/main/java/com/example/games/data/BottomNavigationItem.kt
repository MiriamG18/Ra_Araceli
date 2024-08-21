package com.example.games.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Games: BottomNavigationItem("games_screen", Icons.Default.Home, "Peliculas")
    object About: BottomNavigationItem("about", Icons.Default.Info, "Acerca de")
}