package com.example.androiddevchallenge.data

class AuthenticationRepository {

    var token: String = ""

    fun saveToken(token: String) {
        this.token = token
    }
}