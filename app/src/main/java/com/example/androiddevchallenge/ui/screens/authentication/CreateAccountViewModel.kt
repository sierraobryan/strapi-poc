package com.example.androiddevchallenge.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.data.model.CreateAccountScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val screenState =
        MutableStateFlow(CreateAccountScreenState())

    val signInIntent = authenticationRepository.getSignInClient().signInIntent

    fun authenticateWithGoogle(token: String?) {
        token?.let {
            viewModelScope.launch {
                val success = authenticationRepository.authenticateWithGoogle(it)
                screenState.value = screenState.value.copy(
                    authenticated = success
                )
            }
        }
    }

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

    fun updateAndValidateEmail(email: String) {
        screenState.value = screenState.value.copy(
            email = email,
            buttonEnabled = validateEmailAndPassword(email = email)
        )
    }

    fun updateAndValidatePassword(password: String) {
        screenState.value = screenState.value.copy(
            password = password,
            buttonEnabled = validateEmailAndPassword(password = password)
        )
    }

    private fun validateEmailAndPassword(
        email: String = screenState.value.email,
        password: String = screenState.value.password
    ) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.isNotBlank()
}