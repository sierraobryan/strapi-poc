package com.example.androiddevchallenge.ui.screens.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.StrapiTextField
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton
import com.example.androiddevchallenge.ui.theme.typography

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavController
) {
    val state by authenticationViewModel.screenState.collectAsState()

    val passwordFocusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    if (state.authenticated) navController.navigate(Screen.ContentScreen.route)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 28.dp, end = 28.dp)
    ) {
        Text(
            text = "Login",
            style = typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter your email address and password to get access to your account",
            style = typography.body1
        )
        Spacer(modifier = Modifier.height(20.dp))
        StrapiTextField(
            value = state.email,
            onValueChanged = {
                authenticationViewModel.updateAndValidateEmail(it)
            },
            placeholder = { Text(text = "Email Address") },
            icon = Icons.Outlined.Email,
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequest.requestFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        StrapiTextField(
            value = state.password,
            onValueChanged = {
                authenticationViewModel.updateAndValidatePassword(it)
            },
            placeholder = { Text(text = "Password") },
            icon = Icons.Outlined.Lock,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.focusRequester(passwordFocusRequest),
            keyboardActions = KeyboardActions(
                onNext = {
                    if (state.buttonEnabled) {
                        authenticationViewModel.signIn()
                    } else {
                        keyboardController?.hide()
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(36.dp))
        StrapiButton(
            text = "Login",
            enabled = state.buttonEnabled,
            onClick = {
                authenticationViewModel.signIn()
            }
        )
        LogoFooter()
    }
}