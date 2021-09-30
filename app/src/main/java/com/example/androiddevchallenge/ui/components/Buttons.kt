package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
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

@Composable
fun SkipButton(
    text: String = "Skip Login",
    onClick: () -> Unit,
) {
    Row(modifier = Modifier
        .clickable {
            onClick.invoke()
        }
        .semantics(mergeDescendants = true) {
            role = Role.Button
            contentDescription = text
    }) {
        Text(text = text)
        Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null)
    }
}