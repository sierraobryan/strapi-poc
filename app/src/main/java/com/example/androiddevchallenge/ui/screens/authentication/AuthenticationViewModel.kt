package com.example.androiddevchallenge.ui.screens.authentication

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
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val authenticated = MutableStateFlow(false)

    fun createAccount() {
        viewModelScope.launch {
            val success = authenticationRepository.createAccount(
                "email",
                "password"
            )
            authenticated.value = success
        }
    }

    fun signIn() {
        viewModelScope.launch {
            val success = authenticationRepository.signIn(
                "email",
                "password"
            )
            authenticated.value = success
        }
    }
}