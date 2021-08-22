package com.example.androiddevchallenge.ui.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton

@Composable
fun AuthenticationScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Strapi Meets Firebase",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Enter your email address and password to get access to your account"
        )
        StrapiButton(
            text = "Login",
            onClick = { navController.navigate(Screen.LoginScreen.route) }
        )
        StrapiButton(
            text = "Create Account",
            onClick = { navController.navigate(Screen.CreateAccountScreen.route) }
        )
        LogoFooter()
    }

}