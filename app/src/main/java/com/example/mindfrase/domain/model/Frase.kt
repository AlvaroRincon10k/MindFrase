package com.example.mindfrase.domain.model

data class Frase(
    val id: Int,
    val texto: String,
    val esFavorita: Boolean,
    val likes: Int,
)