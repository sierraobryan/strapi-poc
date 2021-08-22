package com.example.androiddevchallenge.network.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.suspendCoroutine

class AuthInteractor {

    private val auth: FirebaseAuth = Firebase.auth

    private fun getCurrentUser() = auth.currentUser

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

    suspend fun getToken() = suspendCoroutine<String?> { continuation ->
        getCurrentUser()?.getIdToken(true)?.addOnSuccessListener {
            continuation.resumeWith(Result.success(it.token))
        }?.addOnFailureListener {
            continuation.resumeWith(Result.failure(it))
        }
    }
}