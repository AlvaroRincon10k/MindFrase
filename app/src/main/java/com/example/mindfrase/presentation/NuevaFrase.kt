package com.example.mindfrase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mindfrase.domain.model.Frase
import com.example.mindfrase.presentation.utils.bordeDegradado
import com.example.mindfrase.presentation.utils.fondoDegradado

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaFrase(
    navController: NavController,
    viewModel: FrasesViewModel = hiltViewModel()
) {
    var textoFrase by remember {
        mutableStateOf("")
    }
    var categoria by remember { mutableStateOf("") }
    val categorias = listOf("Motivacion", "Resiliencia", "Amor", "Sabiduria", "General")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fondoDegradado()
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Ir atras"
                    )
                }
            }, title = { Text(text = "Nueva frase") })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = textoFrase,
                onValueChange = { textoFrase = it },
                label = {
                    Text("Escribe una frase")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .bordeDegradado()
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                TextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoria") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .bordeDegradado()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(text = cat) },
                            onClick = {
                                categoria = cat
                                expanded = false
                            }
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (textoFrase.isNotBlank()) {
                    val nuevaFrase = Frase(
                        id = 0,
                        texto = textoFrase,
                        esFavorita = false,
                        likes = 0,
                        categoria = categoria
                    )
                    viewModel.addFrase(nuevaFrase)
                    navController.navigateUp()
                }
            }) {
                Text("Guardar Frase")
            }
        }
    }
}