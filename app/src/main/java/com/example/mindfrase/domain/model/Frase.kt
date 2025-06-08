package com.example.mindfrase.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mindfrase.core.Constants.Companion.FRASE_TABLE

@Entity(tableName = FRASE_TABLE)
data class Frase(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val texto: String,
    val esFavorita: Boolean,
    val likes: Int,
)