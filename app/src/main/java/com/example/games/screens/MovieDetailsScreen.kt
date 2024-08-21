package com.example.games.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.games.components.BottomNavigationBar
import com.example.games.components.TopAppBarSecondary
import com.example.games.viewmodel.MoviesViewModel


@Preview(showBackground = true)
@Composable
fun GameDetailScreenPreview() {
    GameDetailsScreen(
        navController = NavController(context = LocalContext.current),
        id = 2,
        viewModel = MoviesViewModel()
    )
}

@Composable
fun GameDetailsScreen(navController: NavController, id: Int, viewModel: MoviesViewModel) {

    val result by viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNowPlayingMovies()
    }

    val movieList = result?.results

    val movie = movieList?.find { movie -> movie.id == id }

    Scaffold(
        topBar = { TopAppBarSecondary(title = movie?.original_title ?: "Error", navController) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(500.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie?.poster_path}"),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(180.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .width(500.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Información",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Popularidad: ${movie?.popularity ?: "Error"}",
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Lenguaje: ${movie?.original_language ?: "Error"}",
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Promedio: ${movie?.vote_average ?: "Error"}",
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Votos totales: ${movie?.vote_count ?: "Error"}",
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .width(500.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Descripción",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie?.overview ?: "Error",
                    )
                }
            }
        }
    }
}

