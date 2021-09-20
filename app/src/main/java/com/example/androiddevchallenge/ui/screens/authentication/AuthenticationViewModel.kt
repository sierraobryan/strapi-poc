package com.example.androiddevchallenge.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.data.model.LoginScreenState
import com.example.androiddevchallenge.network.firebase.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val screenState = MutableStateFlow(
        LoginScreenState()
    )

    fun createAccount() {
        viewModelScope.launch {
            val success = authenticationRepository.createAccount(
                screenState.value.email,
                screenState.value.password
            )
            screenState.value = screenState.value.copy(
                authenticated = success
            )
        }
    }

    fun signIn() {
        viewModelScope.launch {
            val success = authenticationRepository.signIn(
                screenState.value.email,
                screenState.value.password
            )
            screenState.value = screenState.value.copy(
                authenticated = success
            )
        }
    }

    fun updateAndValidateEmail(email: String) {
        screenState.value = screenState.value.copy(
            email = email,
            buttonEnabled = validateEmailAndPassword()
        )
    }

    fun updateAndValidatePassword(password: String) {
        screenState.value = screenState.value.copy(
            password = password,
            buttonEnabled = validateEmailAndPassword()
        )
    }

    private fun validateEmailAndPassword() =
        android.util.Patterns.EMAIL_ADDRESS.matcher(
            screenState.value.email
        ).matches() && screenState.value.password.isNotBlank()
}