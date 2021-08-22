package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.network.firebase.AuthInteractor
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private var token: String = ""

    fun getAuthToken() = token

    suspend fun signIn(email: String, password: String): Boolean {
        val user = authInteractor.signIn(email = email, password = password)
        if (user != null) {
            return getAndSaveToken()
        }
        return false
    }

    suspend fun createAccount(email: String, password: String): Boolean {
        val user = authInteractor.createAccount(email = email, password = password)
        if (user != null) {
            return getAndSaveToken()
        }
        return false
    }

    private suspend fun getAndSaveToken(): Boolean {
        val token = authInteractor.getAndSaveToken()
        if (token != null) {
            this.token = token
        }
        return token != null
    }
}