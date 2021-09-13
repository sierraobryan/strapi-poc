package com.example.androiddevchallenge.ui.screens.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.LogoHeader
import com.example.androiddevchallenge.ui.components.StrapiButton
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun AuthenticationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 28.dp, end = 28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoHeader()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Strapi Meets Firebase",
            style = typography.h1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Enter your email address and password to get access to your account",
            style = typography.body1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        StrapiButton(
            text = "Login",
            onClick = { navController.navigate(Screen.LoginScreen.route) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        StrapiButton(
            text = "Create Account",
            inverseColors = true,
            onClick = { navController.navigate(Screen.CreateAccountScreen.route) }
        )
        LogoFooter()
    }

}