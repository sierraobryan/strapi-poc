package com.example.androiddevchallenge.ui.screens.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton

@Composable
fun CreateAccountScreen(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavController
) {
    val state by authenticationViewModel.createAccountLoginScreenState.collectAsState()

    if (state.authenticated) navController.navigate(Screen.ContentScreen.route)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Keep Connected")
        Text(text = "Enter your email address and password to get access to yur account")
        StrapiButton(
            text = "Create Account",
            onClick = { authenticationViewModel.createAccount() }
        )
        LogoFooter()
    }
}