package com.example.mindfrase.presentation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF010332),
                        Color(0xFF100328),
                        Color(0xFF29011C),
                        Color(0xFF43000D),
                        Color(0xFF530005),
                    )
                )
            )
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
        border = BorderStroke(
            4.dp,
            Brush.linearGradient(
                colors = listOf(Color.Cyan, Color.Magenta, Color.Blue)
            )
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
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
                FavoritoButton(esFavorita = frase.esFavorita) { onFavorito() }
                BadgedBox(badge = {
                    if (frase.likes > 0) {
                        Badge { Text(frase.likes.toString()) }
                    }
                }) {
                    LikeButton(likes = frase.likes) {
                        onLike()
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

@Composable
fun FavoritoButton(esFavorita: Boolean, onFavorito: () -> Unit) {
    val scale = remember { androidx.compose.animation.core.Animatable(1f) }
    LaunchedEffect(esFavorita) {
        scale.animateTo(
            targetValue = 1.8f,
            animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
        )
        scale.animateTo(1f, animationSpec = tween(durationMillis = 150))
    }
    IconButton(onClick = onFavorito) {
        Icon(
            if (esFavorita)
                Icons.Default.Favorite
            else Icons.Default.FavoriteBorder,
            contentDescription = "Favorito",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Composable
fun LikeButton(likes: Int, onLike: () -> Unit) {
    val rotation = remember { androidx.compose.animation.core.Animatable(360f) }
    LaunchedEffect(likes) {
        rotation.animateTo(0f, animationSpec = tween(700))
        rotation.snapTo(360f)
    }
    IconButton(onClick = onLike) {
        Icon(
            Icons.Default.ThumbUp,
            contentDescription = "Like",
            modifier = Modifier.rotate(rotation.value)
        )
    }
}