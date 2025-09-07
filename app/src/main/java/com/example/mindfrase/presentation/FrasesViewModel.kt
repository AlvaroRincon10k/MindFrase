package com.example.mindfrase.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindfrase.domain.model.Frase
import com.example.mindfrase.domain.repository.FraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FrasesViewModel @Inject constructor(
    private val fraseRepository: FraseRepository
) : ViewModel() {

    val frases = fraseRepository.getFrasesFromRoom()
    var frase by mutableStateOf(Frase(0, "", false, 0, "General"))

    fun toggleFavorito(frase: Frase) = viewModelScope.launch(Dispatchers.IO) {
        val nuevaFrase = frase.copy(esFavorita = !frase.esFavorita)
        fraseRepository.updateFraseInRoom(nuevaFrase)
    }

    fun darLike(frase: Frase) = viewModelScope.launch(Dispatchers.IO) {
        val nuevaFrase = frase.copy(likes = frase.likes + 1)
        fraseRepository.updateFraseInRoom(nuevaFrase)
    }

    fun addFrase(frase: Frase) = viewModelScope.launch(Dispatchers.IO) {
        fraseRepository.addFrasesToRoom(frase)
    }

    fun deleteFrase(frase: Frase) = viewModelScope.launch(Dispatchers.IO) {
        fraseRepository.deleteFraseFromRoom(frase)
    }

    fun updateFrase(frase: Frase) = viewModelScope.launch(Dispatchers.IO) {
        fraseRepository.updateFraseInRoom(frase)
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