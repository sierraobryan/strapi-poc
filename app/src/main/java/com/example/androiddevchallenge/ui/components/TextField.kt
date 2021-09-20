package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.backgroundEditTextGrey
import com.example.androiddevchallenge.ui.theme.blue200
import com.example.androiddevchallenge.ui.theme.placeholderGrey
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun StrapiTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeholder: @Composable () -> Unit,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var focused by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = shapes.medium)
            .border(
                width = 2.dp,
                color = blue200,
                shape = MaterialTheme.shapes.medium
            )
            .onFocusEvent {
                focused = it.isFocused
            },
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            placeholderColor = placeholderGrey,
            backgroundColor = if (focused) Color.White else backgroundEditTextGrey,
            errorLabelColor = Color.Red,
            cursorColor = placeholderGrey,
            focusedIndicatorColor = Color.White
        ),
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = getVisualTransformation(keyboardType),
        placeholder = placeholder,
        singleLine = singleLine,
        keyboardActions = keyboardActions,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) }
    )
}

fun getVisualTransformation(keyboardType: KeyboardType): VisualTransformation {
    return if (keyboardType == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
}