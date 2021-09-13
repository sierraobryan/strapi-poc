package com.example.androiddevchallenge.ui.screens.authentication

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun LoginScreen(
    authenticationViewModel: AuthenticationViewModel,
    navController: NavController
) {
    val authenticated by authenticationViewModel.authenticated.collectAsState()

    if (authenticated) navController.navigate(Screen.ContentScreen.route)

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
        Spacer(modifier = Modifier.height(16.dp))
        StrapiButton(
            text = "Login",
            onClick = {
                authenticationViewModel.signIn()
            }
        )
        LogoFooter()
    }
}