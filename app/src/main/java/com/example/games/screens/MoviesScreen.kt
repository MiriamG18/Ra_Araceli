package com.example.games.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.games.components.BottomNavigationBar
import com.example.games.components.TopAppBar
import com.example.games.navigation.AppScreens
import com.example.games.viewmodel.MoviesViewModel
import com.example.games.model.Result

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesScreen(NavController(context = LocalContext.current), MoviesViewModel())
}

@Composable
fun MoviesScreen(navController: NavController, viewModel: MoviesViewModel) {

    val movies by viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNowPlayingMovies()
    }

    Scaffold(
        topBar = { TopAppBar("Películas de estreno") },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(movies?.results ?: emptyList()) { movie ->
                GameCard(movie = movie, navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun GameCard(movie: Result, navController: NavController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable {
                navController.navigate(AppScreens.GameScreen.createRoute(movie.id))
            }
            .fillMaxSize()
            .size(330.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.original_title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
