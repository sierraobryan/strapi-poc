package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StrapiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    inverseColors: Boolean = false,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = text
        )
    }
}