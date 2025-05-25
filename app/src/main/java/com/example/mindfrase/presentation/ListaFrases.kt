package com.example.mindfrase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mindfrase.domain.model.Frase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaFrases(
    modifier: Modifier,
    viewModel: FrasesViewModel = viewModel()
) {
    val frases by viewModel.frases.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = "Mis frases") }
        )
        LazyColumn {
            itemsIndexed(frases) { index, frase ->
                FraseItem(
                    frase,
                    onFavorito = { viewModel.toggleFavorito(index) },
                    onLike = { viewModel.darLike(index) },
                    onCompartir = { viewModel.compartirFrase(frase.texto, context) }
                )
            }
        }
    }
}

@Composable
fun FraseItem(
    frase: Frase,
    onFavorito: () -> Unit,
    onLike: () -> Unit,
    onCompartir: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = frase.texto, fontSize = 18.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onFavorito) {
                    Icon(
                        if (frase.esFavorita)
                            Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorito"
                    )
                }
                BadgedBox(badge = {
                    if (frase.likes > 0) {
                        Badge { Text(frase.likes.toString()) }
                    }
                }) {
                    IconButton(onClick = onLike) {
                        Icon(
                            Icons.Default.ThumbUp,
                            contentDescription = "Favorito"
                        )
                    }
                }
                IconButton(onClick = onCompartir) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Favorito"
                    )
                }
            }
        }
    }
}
