package com.example.androiddevchallenge.ui.screens.authentication

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.components.StrapiTextField
import com.example.androiddevchallenge.ui.components.LogoFooter
import com.example.androiddevchallenge.ui.components.StrapiButton
import com.example.androiddevchallenge.ui.theme.typography
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val state by loginViewModel.screenState.collectAsState()

    val passwordFocusRequest = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val account = task.getResult(ApiException::class.java)!!
            loginViewModel.authenticateWithGoogle(account.idToken)
        } catch (e: ApiException) {
            Log.e("ApiException", e.message ?: "uh oh")
        }
    }

    if (state.authenticated) navController.navigate(Screen.ContentScreen.route)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 28.dp, end = 28.dp)
    ) {
        Text(
            text = "Login",
            style = typography.h1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter your email address and password to get access to your account",
            style = typography.body1
        )
        Spacer(modifier = Modifier.height(20.dp))
        StrapiTextField(
            value = state.email,
            onValueChanged = {
                loginViewModel.updateAndValidateEmail(it)
            },
            placeholder = { Text(text = "Email Address") },
            icon = Icons.Outlined.Email,
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequest.requestFocus()
                }
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        StrapiTextField(
            value = state.password,
            onValueChanged = {
                loginViewModel.updateAndValidatePassword(it)
            },
            placeholder = { Text(text = "Password") },
            icon = Icons.Outlined.Lock,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.focusRequester(passwordFocusRequest),
            keyboardActions = KeyboardActions(
                onNext = {
                    keyboardController?.hide()
                    if (state.buttonEnabled) {
                        loginViewModel.signIn()
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(36.dp))
        StrapiButton(
            text = "Login",
            enabled = state.buttonEnabled,
            onClick = {
                loginViewModel.signIn()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        StrapiButton(
            text = "Log in with Google",
            onClick = { launcher.launch(loginViewModel.signInIntent) }
        )
        LogoFooter()
    }
}