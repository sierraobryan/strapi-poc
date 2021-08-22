package com.example.androiddevchallenge.ui

sealed class Screen(val route: String) {
    object AuthenticationScreen : Screen("auth")
    object LoginScreen : Screen("login")
    object CreateAccountScreen : Screen("createAccount")
    object ContentScreen : Screen("content")
}
