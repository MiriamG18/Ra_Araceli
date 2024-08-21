package com.example.games.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.games.components.BottomNavigationBar
import com.example.games.components.TopAppBar
import com.example.games.data.Student
import com.example.games.data.students

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = NavController(context = LocalContext.current))
}

@Composable
fun AboutScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopAppBar("Acerca de") },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .width(500.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                       horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Materia:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Desarrollo para dispositivos inteligentes",
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Profesor:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Dr. Armando Méndez Morales",
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Grado y grupo:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "9A",
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Año:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "2024",
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Integrantes",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            students.forEach { integrante ->
                                IntegranteItem(integrante)
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun IntegranteItem(student: Student) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(170.dp)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = student.foto),
                contentDescription = "Foto de ${student.nombre}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = student.nombre, textAlign = TextAlign.Center)
        }
    }
}