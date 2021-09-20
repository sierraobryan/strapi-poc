package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun StrapiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    inverseColors: Boolean = false,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = if (inverseColors)
            ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = MaterialTheme.colors.primary
            )
        else ButtonDefaults.buttonColors(),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }
}