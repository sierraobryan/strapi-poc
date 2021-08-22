package com.example.androiddevchallenge

import com.example.androiddevchallenge.data.AuthenticationRepository
import com.example.androiddevchallenge.network.strapi.StrapiService
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val strapiService: StrapiService,
    private val authenticationRepository: AuthenticationRepository
)  {

    suspend fun getContent() = strapiService.getContent(authenticationRepository.token)
}