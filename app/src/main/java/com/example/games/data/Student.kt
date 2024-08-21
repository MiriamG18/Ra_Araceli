package com.example.games.data

import com.example.games.R

data class Student(
    val nombre: String,
//    val grado: String,
//    val grupo: String,
    val foto: Int
)

// Información de los integrantes
val students = listOf(
    Student("Norma Araceli López Gómez", R.drawable.a),
    Student("Miriam Olayne Gómez López", R.drawable.c),
    Student("Iván Rubisel Gutiérrez Cruz", R.drawable.b)
)