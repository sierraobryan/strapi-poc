package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

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
    Row(
        modifier = Modifier
            .clickable { onClick.invoke() }
            .semantics(mergeDescendants = true) {
                role = Role.Button
            }
    ) {
        Text(text = text)
        Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null)
    }
}

@Composable
fun SignInWithGoogle(
    onClick: () -> Unit,
    text: String = "Sign in with Google",
    icon: Painter = painterResource(id = R.drawable.ic_google_logo),
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick
            ).semantics { role = Role.Button },
        shape = shapes.medium,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = icon,
                contentDescription = "SignInButton",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = TextStyle(fontSize = 20.sp))
        }
    }
}