package com.example.androiddevchallenge.data.model

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val buttonEnabled: Boolean = false,
    val authenticated: Boolean = false
)

data class CreateAccountScreenState(
    val email: String = "",
    val password: String = "",
    val buttonEnabled: Boolean = false,
    val authenticated: Boolean = false
)

data class ContentScreenState(
    val products: List<Product> = emptyList(),
    val error: Boolean = false
)