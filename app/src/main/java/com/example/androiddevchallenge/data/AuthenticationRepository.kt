package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.network.firebase.AuthInteractor
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authInteractor: AuthInteractor
) {

    private var token: String = ""

    fun getSignInClient() = authInteractor.googleSignInClient

    fun getAuthToken() = token

    suspend fun authenticateWithGoogle(token: String): Boolean {
        val user = authInteractor.authWithGoogle(token = token)
        return if (user != null) getAndSaveToken() else false
    }

    suspend fun signIn(email: String, password: String): Boolean {
        val user = authInteractor.signIn(email = email, password = password)
        return if (user != null) getAndSaveToken() else false
    }

    suspend fun createAccount(email: String, password: String): Boolean {
        val user = authInteractor.createAccount(email = email, password = password)
        return if (user != null) getAndSaveToken() else false
    }

    private suspend fun getAndSaveToken(): Boolean {
        val token = authInteractor.getAndSaveToken()
        if (token != null) {
            this.token = token
        }
        return token != null
    }
}