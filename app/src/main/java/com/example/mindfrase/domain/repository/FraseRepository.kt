package com.example.mindfrase.domain.repository

import com.example.mindfrase.domain.model.Frase
import kotlinx.coroutines.flow.Flow

typealias Frases = List<Frase>

interface FraseRepository {
    fun getFrasesFromRoom(): Flow<Frases>
    fun addFrasesToRoom(frase: Frase)
    fun deleteFraseFromRoom(frase: Frase)
    fun updateFraseInRoom(frase: Frase)
    fun getFrasesFromRoom(id: Int): Frase
}