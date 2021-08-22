package com.example.androiddevchallenge.data

class AuthenticationRepository {

    private var _token: String = ""
    val token = _token

    fun saveToken(token: String) {
        _token = token
    }
}