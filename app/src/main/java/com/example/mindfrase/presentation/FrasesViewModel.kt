package com.example.mindfrase.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.mindfrase.domain.model.Frase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FrasesViewModel : ViewModel() {
    private val _frases = MutableStateFlow(
        listOf(
            Frase(0, "El exito es la suma de pequeños esfuerzos", true, 5),
            Frase(1, "La vida es un 10% de lo que pasa y un 90%", false, 1)
        )
    )
    val frases: StateFlow<List<Frase>> = _frases

    fun toggleFavorito(index: Int) {
        _frases.value = _frases.value.toMutableList().apply {
            this[index] = this[index].copy(esFavorita = !this[index].esFavorita)
        }
    }

    fun darLike(index: Int) {
        _frases.value = _frases.value.toMutableList().apply {
            this[index] = this[index].copy(likes = this[index].likes + 1)
        }
    }

    fun compartirFrase(frase: String, context: Context) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, frase)
            type = "text/plain"
        }
        context.startActivity(Intent.createChooser(sendIntent, null))
    }
}