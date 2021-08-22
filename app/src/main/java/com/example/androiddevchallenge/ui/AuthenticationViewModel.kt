package com.example.androiddevchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.network.firebase.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    val authenticated = MutableStateFlow(false)

    fun createAccount() {
        viewModelScope.launch {
            val user = authInteractor.createAccount(
                "email",
            "password"
            )
            getAndSaveToken()
            authenticated.value = user != null
        }
    }

    fun signIn() {
        viewModelScope.launch {
            val user = authInteractor.signIn(
                "email",
                "password"
            )
            val tokenSaved = getAndSaveToken()
            authenticated.value = tokenSaved
        }
    }

    private suspend fun getAndSaveToken() = authInteractor.getAndSaveToken()
}