package com.example.mindfrase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mindfrase.presentation.ListaFrases
import com.example.mindfrase.presentation.NuevaFrase

@Composable
fun FrasesNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.ListaFrases.name
    ) {
        composable(NavScreens.ListaFrases.name) {
            ListaFrases(
                modifier = modifier,
                navController = navController
            )
        }
        composable(NavScreens.NuevaFrase.name) {
            NuevaFrase(
                navController = navController
            )
        }
    }
}