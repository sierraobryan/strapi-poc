package com.example.androiddevchallenge.ui.screens.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.AuthenticationViewModel
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton

@Composable
fun LoginScreen(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavController
) {
    val authenticated by authenticationViewModel.authenticated.collectAsState()

    if (authenticated) navController.navigate(Screen.ContentScreen.route)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Keep Connected")
        Text(text = "Enter your email address and password to get access to your account")
        StrapiButton(
            text = "Login",
            onClick = {
                authenticationViewModel.signIn()
            }
        )
        LogoFooter()
    }
}