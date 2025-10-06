package com.example.mindfrase.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.fondoDegradado() = this.background(
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

fun Modifier.bordeDegradado() = this.border(
    width = 2.dp,
    brush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF7F00FF),
            Color(0XFFE100FF)
        )
    ),
    shape = RoundedCornerShape(8.dp)
)

@Composable
fun colorTextField() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    cursorColor = Color.White,
    focusedLabelColor = Color(0xFF7F00FF),
    unfocusedLabelColor = Color.Gray,
    disabledLabelColor = Color.DarkGray
)
