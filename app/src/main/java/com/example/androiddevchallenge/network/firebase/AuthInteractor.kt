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
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

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
    ): FirebaseUser? = suspendCoroutine { continuation ->
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                continuation.resumeWith(Result.success(auth.currentUser))
            }.addOnFailureListener {
                continuation.resumeWith(Result.failure(it))
            }
    }

    suspend fun createAccount(
        email: String = "sierrarobryan+2@gmail.com",
        password: String = "Password1234!"
    ): FirebaseUser? = suspendCoroutine { continuation ->
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resumeWith(Result.success(auth.currentUser))
            }.addOnFailureListener {
                continuation.resumeWith(Result.failure(it))
            }
    }

    suspend fun signIn(
        email: String,
        password: String
    ): FirebaseUser? = suspendCoroutine { continuation ->
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                continuation.resumeWith(Result.success(auth.currentUser))
            }.addOnFailureListener {
                continuation.resumeWith(Result.failure(it))
            }
    }

    suspend fun sendEmailVerification() = suspendCoroutine<Unit> { continuation ->
        val user = auth.currentUser
        user?.let { firebaseUser ->
            firebaseUser.sendEmailVerification()
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(Unit))
                }.addOnFailureListener {
                    continuation.resumeWith(Result.failure(it))
                }
        }
    }

    suspend fun getAndSaveToken() = suspendCoroutine<String?> { continuation ->
        getCurrentUser()?.getIdToken(true)?.addOnSuccessListener {
            continuation.resumeWith(Result.success(it.token))
        }?.addOnFailureListener {
            continuation.resumeWith(Result.failure(it))
        }
    }
}