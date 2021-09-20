package com.example.androiddevchallenge.data.model

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val buttonEnabled: Boolean = false,
    val authenticated: Boolean = false
)