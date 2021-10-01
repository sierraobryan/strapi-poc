package com.example.androiddevchallenge.network.firebase

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val context: Context) {

    private val auth: FirebaseAuth = Firebase.auth

    private fun getCurrentUser() = auth.currentUser

    val googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("client id")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    suspend fun authWithGoogle(
        token: String
    ): FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(token, null)
        return auth.signInWithCredential(credential).await().user
    }

    suspend fun createAccount(
        email: String,
        password: String
    ) = auth.createUserWithEmailAndPassword(email, password).await().user

    suspend fun signIn(
        email: String,
        password: String
    ) = auth.signInWithEmailAndPassword(email, password).await().user

    suspend fun getAndSaveToken() = getCurrentUser()?.getIdToken(true)?.await()?.token
}